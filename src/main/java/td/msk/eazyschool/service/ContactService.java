package td.msk.eazyschool.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import td.msk.eazyschool.Constants.EazySchoolConstants;
import td.msk.eazyschool.model.Contact;
import td.msk.eazyschool.repositories.ContactRepository;

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
        Contact savedContact = contactRepository.save(contact);
        if (savedContact.getContactId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatus(EazySchoolConstants.OPEN, pageable);
        return msgPage;
    }

    public boolean updateMsgStatus(int contactId) {
        boolean isUpdated = false;
//        Optional<Contact> contact = contactRepository.findById(contactId);
//        contact.ifPresent(contact1 -> {
//            contact1.setStatus(EazySchoolConstants.CLOSE);
//
//        });
//        Contact updatedContact = contactRepository.save(contact.get());
//        if (updatedContact.getUpdatedBy() != null) {
//            isUpdated = true;
//        }
        int i = contactRepository.updateStatusById(EazySchoolConstants.CLOSE, contactId);
        if (i > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
