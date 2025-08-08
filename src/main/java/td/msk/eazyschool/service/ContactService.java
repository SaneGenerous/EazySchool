package td.msk.eazyschool.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import td.msk.eazyschool.Constants.EazySchoolConstants;
import td.msk.eazyschool.model.Contact;
import td.msk.eazyschool.repositories.ContactRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    private ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactService() {
        System.out.println("Contact Service bean initialized");
    }

    /**
     * Save contact details into DB
     * @param contact
     * @return boolean
     */

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(EazySchoolConstants.OPEN);
        contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        Contact savedContact = contactRepository.save(contact);
        if (savedContact.getContactId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus() {
        return contactRepository.findByStatus(EazySchoolConstants.OPEN);
    }

    public boolean updateMsgStatus(int contactId, String updatedBy) {
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(EazySchoolConstants.CLOSE);
            contact1.setUpdatedBy(updatedBy);
            contact1.setUpdatedAt(LocalDateTime.now());
        });
        Contact updatedContact = contactRepository.save(contact.get());
        if (updatedContact.getUpdatedBy() != null) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
