package tn.esprit.biol.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class Staff_Details  implements Serializable {

    @Id
     String id;
     Integer workingHPerWeek;
     Integer workingHPerDay;
     Integer workingHPerNight;
     Float salary;
     @Enumerated(EnumType.STRING)
     Contrat contrat;

    @OneToOne(mappedBy = "staff_details")
    User user;




}
