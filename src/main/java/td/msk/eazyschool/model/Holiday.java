package td.msk.eazyschool.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "holidays")
public class Holiday extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int holidayId;

    private String day;

    private String reason;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }
}
