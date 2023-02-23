package tn.esprit.biol.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idSearch;
   /* @OneToOne(mappedBy = "search",cascade = CascadeType.ALL)
    User user;*/
    String expression;
}
