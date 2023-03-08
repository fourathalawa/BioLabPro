package tn.esprit.biol.entity;

<<<<<<< Updated upstream
import lombok.*;
=======
import lombok.ToString;
>>>>>>> Stashed changes

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
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

<<<<<<< Updated upstream
    @OneToMany(mappedBy = "user")
    private Set<Sample> samples;
    /**
     *  Chiheb
     * */
    @OneToMany(mappedBy = "user")
    private Set<SampleResult> samplesResults;


    @OneToMany(cascade = CascadeType.ALL, mappedBy="trainee")
    private Set<Certifcate> certifcates;
    /**
     *  Fourat
     * */
    @ManyToMany(mappedBy="trainees", cascade = CascadeType.ALL)
    private Set<Training> trainings;
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
=======

>>>>>>> Stashed changes
}
