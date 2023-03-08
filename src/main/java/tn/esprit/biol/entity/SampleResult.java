package tn.esprit.biol.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class SampleResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ResultID;
     private String RDescription;
    @Temporal(TemporalType.DATE)
    private Date DateResult;
    private String Note ;


    @OneToOne
    @JoinColumn(name = "sampleID")
    private Sample sampleID;

}
