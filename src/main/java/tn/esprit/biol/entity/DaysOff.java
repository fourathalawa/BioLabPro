package tn.esprit.biol.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class DaysOff  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;
    Date startDate;
    Date endDate;
    Boolean request;

    @ManyToOne
    User user;


}
