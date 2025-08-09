package td.msk.eazyschool.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Person {
    @Id
    private int id;

    private String name;

    private String mobileNumber;

    private String email;

    private String confirmEmail;

    private String pwd;

    private String confirmPwd;
}
