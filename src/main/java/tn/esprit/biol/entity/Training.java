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
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int TrainigId;
    String TrainingName;
    String TrainingSubject;
    @Enumerated(EnumType.STRING)
    TrainingMethod trainingMethod;
    @Temporal(TemporalType.DATE)
    Date Training_startdate;
    @Temporal(TemporalType.DATE)
    Date Training_enddate;

}
