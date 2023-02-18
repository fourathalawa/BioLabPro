package tn.esprit.biol.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Staff_Details  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer id;
     Integer workingHPerWeek;
     Integer workingHPerDay;
     Integer workingHPerNight;
     Float salary;
     @Enumerated(EnumType.STRING)
     Contrat contrat;

    @OneToOne(mappedBy = "staff_details")
    User user;



}
