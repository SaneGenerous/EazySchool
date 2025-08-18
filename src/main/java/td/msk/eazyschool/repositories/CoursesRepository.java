package td.msk.eazyschool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import td.msk.eazyschool.model.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
}
