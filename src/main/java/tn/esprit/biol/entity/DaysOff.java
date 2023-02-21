package tn.esprit.biol.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DaysOff  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Date startDate;
    Date endDate;
    Boolean request;
    String Justification;

    @ManyToOne
    User user;


}
