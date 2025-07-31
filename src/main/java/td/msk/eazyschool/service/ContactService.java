package td.msk.eazyschool.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import td.msk.eazyschool.model.Contact;

@Slf4j
@Service
public class ContactService {

    /**
     * Save contact details into DB
     * @param contact
     * @return boolean
     */

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = true;
        //TODO - Need to persist data inth the DB table
        log.info(contact.toString());
        return isSaved;
    }
}
