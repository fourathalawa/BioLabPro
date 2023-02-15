package tn.esprit.biol.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@FieldDefaults(level= AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idEtudiant;
    String prenomE;
    String nomE;
}