package tn.esprit.biol.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @OneToOne(mappedBy="training")
    private Certifcate certifcate;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "trainings")
    @JsonIgnoreProperties("Training")
    private Set<User> trainees;

}
