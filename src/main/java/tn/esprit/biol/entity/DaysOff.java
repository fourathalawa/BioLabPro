package tn.esprit.biol.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class DaysOff  implements Serializable {

    @Id
    String id;

    Date startDate;
    Date endDate;
    Boolean request;

    @ManyToOne
    User user;


}
