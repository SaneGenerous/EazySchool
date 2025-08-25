package td.msk.eazyschool.rest;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import td.msk.eazyschool.Constants.EazySchoolConstants;
import td.msk.eazyschool.model.Contact;
import td.msk.eazyschool.model.Response;
import td.msk.eazyschool.repositories.ContactRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/api/contact", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@CrossOrigin(origins = "*")
public class ContactRestController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("getMessagesByStatus")
    public List<Contact> getMessagesByStatus(@RequestParam("status") String status) {
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getAllMessagesByStatus")
    public List<Contact> getAllMessagesByStatus(@RequestBody Contact contact) {
        if(contact != null && contact.getStatus() != null) {
            return contactRepository.findByStatus(contact.getStatus());
        } else {
            return List.of();
        }
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(@RequestHeader("invocationFrom") String invocationForm,
                                            @Valid @RequestBody Contact contact) {
        log.info("Header invocationFrom = {}", invocationForm);
        contactRepository.save(contact);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMsgSaved", "true")
                .body(response);
    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((key, value) -> {
            log.info("Header {} = {}", key, value.stream().collect(Collectors.joining("|")));
        });
        Contact contact = requestEntity.getBody();
        contactRepository.deleteById(contact.getContactId());
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message deleted successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contactReq) {
        Response response = new Response();
        Optional<Contact> optionalContact = contactRepository.findById(contactReq.getContactId());
        if(optionalContact.isPresent()) {
            optionalContact.get().setStatus(EazySchoolConstants.CLOSE);
            contactRepository.save(optionalContact.get());
        } else {
            response.setStatusCode("400");
            response.setStatusMsg("Invalid Contact ID received");
            return  ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
        response.setStatusCode("200");
        response.setStatusMsg("Message closed successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
