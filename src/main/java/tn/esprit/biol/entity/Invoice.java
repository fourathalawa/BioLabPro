package tn.esprit.biol.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Invoice")
@Setter
@Getter
@ToString
public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;

    /*
    -systeme de calcul de totalammount de facture en fonction des type de test choisi et en fonction de plage de total
     un taux de tva sera ajouté
    -systéme de remise pour les patient ou le nombre de rendez vous validez > 3
    -notifaction par sms si le patient a passer la date de facture de 2 jours et que son status de paiement = 0
    -evoyer automatiquement un email contient la facture en pdf lors d'ajout d'une facture
    - Statistique (table facture )- chiffre affaire  etc ...
    (table TestType) - les pourcentages de type de Test
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idInvoice")
    private Integer idInvoice;


    //@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateInvoice;
    private String idPatient;

    private Float totalAmount;
    private String descreptionInvoice;
    //0 if not payed - 1 if its payed
    private Integer statusPayment;




    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "invoice_test_type",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "test_type_id"))
    private List<TestType> TestList = new ArrayList<>();



    public Invoice() {
    }

    public Invoice(Integer idInvoice, LocalDateTime dateInvoice, String idPatient, Float totalAmount, String descreptionInvoice, Integer statusPayment, List<TestType> testList) {

        this.idInvoice = idInvoice;
        this.dateInvoice = dateInvoice;
        this.idPatient = idPatient;
        this.totalAmount = totalAmount;
        this.descreptionInvoice = descreptionInvoice;
        this.statusPayment = statusPayment;
        this.TestList = testList;
    }
}

