package td.msk.eazyschool.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import td.msk.eazyschool.Constants.EazySchoolConstants;
import td.msk.eazyschool.model.Contact;
import td.msk.eazyschool.repositories.ContactRepository;

import java.time.LocalDateTime;

@Slf4j
@Service

public class ContactService {

    private ContactRepository contactRepository;

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
        int result = contactRepository.saveContactMsg(contact);
        if (result > 0) {
            isSaved = true;
        }
        return isSaved;
    }

}
