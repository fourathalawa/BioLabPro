package tn.esprit.biol.entity;

import lombok.ToString;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy="trainee")
    private Set<Certifcate> certifcates;

    @ManyToMany(mappedBy="trainees", cascade = CascadeType.ALL)
    private Set<Training> trainings;
}
