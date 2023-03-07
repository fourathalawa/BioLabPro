package tn.esprit.biol.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="Appointement")
@Setter
@Getter
@ToString
public class Appointement implements Serializable {
    private static final long serialVersionUID = 1L;

    /*
    -systeme de repartition de charge (salle)/(heure) exemple 2 salle pour chaque heure de de rendez vous
    - email envoyé pour notifier le patient lors l'ajout d'un rendez
    - sms envoyé avant de 30 mn de rendez vous
    - stats
    -systeme de ban avec notif si le patient a effectuer 3 appointments no valider ne peut pas effectuer un  autre rendez-vous
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idAppointement")
    private Integer idAppointement;
   // @Temporal(TemporalType.DATE)
    // check if in the same date and hour
    // if >= 2 appoints exist error
    // if <2 accept appointment
    private LocalDateTime dateAppointement;
    private String typeAppointement;
    // 0 (if the appoint time didnt came ) 1(if appoint time came and patient came) 2(if appoint time came and patient didnt came)
    //par defaut status = 0
    private Integer statusAppointement=0;
    //intgeration

    private String idPatient="0";


    public Appointement() {
    }

    public Appointement(Integer idAppointement, LocalDateTime dateAppointement, String typeAppointement) {
        this.idAppointement = idAppointement;
        this.dateAppointement = dateAppointement;
        this.typeAppointement = typeAppointement;
        //par défaut le status de rendez vous est 0
        this.statusAppointement = 0;
        // phase integration this.idPatient = Session.idPatient

        this.idPatient = "0";
    }
    public Appointement(Integer idAppointement, LocalDateTime dateAppointement, String typeAppointement , Integer statusAppointement , Integer Idpatient) {
        this.idAppointement = idAppointement;
        this.dateAppointement = dateAppointement;
        this.typeAppointement = typeAppointement;
        //par défaut le status de rendez vous est 0
        this.statusAppointement = 0;
        // phase integration this.idPatient = Session.idPatient

        this.idPatient = "0";

    }
}
