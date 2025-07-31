package td.msk.eazyschool.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import td.msk.eazyschool.model.Contact;
import td.msk.eazyschool.service.ContactService;

@Slf4j
@Controller
public class ContactController {


    private final ContactService contactService;



    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @RequestMapping("/contact")
    public String displayContactPage() {
        return "contact.html";
    }

    @PostMapping(value = "/saveMsg")
    public ModelAndView saveMessage(Contact contact) {
        log.info("Name: {}", contact.getName());
        contactService.saveMessageDetails(contact);
        return new ModelAndView("redirect:/contact");
    }
}
