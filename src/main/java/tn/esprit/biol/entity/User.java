package tn.esprit.biol.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

    @Id
    private String id;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String tel;
    private String email;
    private String adress;
    private Boolean isBanned=Boolean.FALSE;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;

    @OneToMany(mappedBy = "user")
    private Set<Sample> samples;
    /**
     *  Chiheb
     * */
    @OneToMany(mappedBy = "user")
    private Set<SampleResult> samplesResults;
    /**
     *  Fourat
     * */

    @OneToMany(cascade = CascadeType.ALL, mappedBy="trainee")
    private Set<Certifcate> certifcates;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_training",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "training_id") })
    @JsonIgnoreProperties("trainees")
    private Set<Training> trainings;
 /*   @OneToOne(mappedBy = "user")
    private Search search;*/
    /**
     *  Houde
     * */
    @OneToOne
    Staff_Details staff_details;
    /**
     *  Houde
     * */
    @OneToMany
    Set<DaysOff> daysOff;
    /**
     *  Houde
     * */
    @ManyToMany
    Set<NightShift> nightsShift;




}
