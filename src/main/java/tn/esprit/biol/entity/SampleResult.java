package tn.esprit.biol.entity;

import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@ToString
public class SampleResult implements Serializable {

    @Id
    private int ResultID;
     private String RDescription;
    private Date DateResult;
    private String Note ;


    @OneToOne
    @JoinColumn(name = "sampleID")
    private User sampleID;

    @OneToOne
    @JoinColumn(name = "PatientID")
    private User PatientID;

    @ManyToOne
    private User user;
}
