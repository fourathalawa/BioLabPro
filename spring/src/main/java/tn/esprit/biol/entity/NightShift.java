package tn.esprit.biol.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;
import java.util.Set;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class NightShift {
    @Id
    String id;
    Date startDate;
    Date endDate;
    Integer numberOfEffectivByNight;

    @ManyToMany(mappedBy = "nightsShift")
    @JsonIgnore
    Set<User> users;
}
