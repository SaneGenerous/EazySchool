package td.msk.eazyschool.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import td.msk.eazyschool.model.Holiday;

import java.util.List;

@Repository
public interface HolidaysRepository extends CrudRepository<Holiday, Integer> {

}
