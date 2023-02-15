package tn.esprit.biol.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Certifcate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int CertifiacteId;
    @Temporal(TemporalType.DATE)
    Date CertificateDate;
    @OneToOne
    @JoinColumn(name = "trainee_id")
    User Trainee;
    @OneToOne
    @JoinColumn(name = "trainer_id")
    User Trainer;
    @OneToOne
    @JoinColumn(name = "training_id")
    Training training;
}
