package tn.esprit.biol.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@ToString
public class DaysOff  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    LocalDate startDate;
    LocalDate endDate;
    Boolean request;
    String justification;
    @Lob /* LOB signifie "Large Object" et fait référence à des données binaires ou textuelles de grande taille */
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;

    @Enumerated(EnumType.STRING)
    Etat etat;


    @ManyToOne
    User user;



    public DaysOff( String justification,LocalDate startDate, LocalDate endDate, byte[] imageBytes) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.justification = justification;
        this.imageData = imageBytes;
    }


    public DaysOff(LocalDate startDate, LocalDate endDate, byte[] imageBytes, String justification, Etat etat) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.justification = justification;
        this.imageData = imageBytes;
        this.etat=etat;
    }
}
