package tn.esprit.biol.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idInvoice")
    private Integer idInvoice;
    @Temporal(TemporalType.DATE)
    private Date dateInvoice;
    private Integer idPatient;
    private Float totalAmount;
    private String descreptionInvoice;
    //0 if not payed - 1 if its payed
    private Integer statusPayment;

    @ElementCollection
    private List<TestType> testTypeList = new ArrayList<>();

    public Invoice() {
    }
}

