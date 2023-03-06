package tn.esprit.biol.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
     Integer nbrDaysOffPerYears=22;
     Integer  SommeDaysOff;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore // Ignorer la sérialisation de cette propriété

    private  User user;


}
