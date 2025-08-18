package td.msk.eazyschool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import td.msk.eazyschool.model.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person readByEmail(String email);

    Optional<Person> findByEmail(String email);

    //List<Person> findAllByEazyClassId(int classId);
}
