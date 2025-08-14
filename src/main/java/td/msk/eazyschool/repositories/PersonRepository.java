package td.msk.eazyschool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import td.msk.eazyschool.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person readByEmail(String email);
}
