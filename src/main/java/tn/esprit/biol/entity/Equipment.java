package tn.esprit.biol.entity;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Equipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id_eq;
    @NotNull
    @Column(nullable = false)
    String type;
    @NotNull
    @Column(nullable = false)
    LocalDate Sterilization_Date;
    @NotNull
    @Column(nullable = false)
    LocalDate Expiration_Date;
    @NotNull
    @Column(nullable = false)
    Integer quantity;

}
