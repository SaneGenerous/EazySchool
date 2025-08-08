package td.msk.eazyschool.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import td.msk.eazyschool.model.Contact;
import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    List<Contact> findByStatus(String status);

}
