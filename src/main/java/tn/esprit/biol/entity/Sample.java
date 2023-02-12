package tn.esprit.biol.entity;

import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@ToString
public class Sample implements Serializable {

    @Id
    private int SampleID;
    private Date Dateofwithdrawl;
    private boolean Urgency;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "SampleID",
            joinColumns = {
                    @JoinColumn(name = "SampleID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "idsampleR")
            }
    )
    private Sample Sample;


}
