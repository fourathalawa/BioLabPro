package tn.esprit.biol.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TestType")
@Setter
@Getter
@ToString
public class TestType {
   /* BloodTest,
    Urinalysis,
    MicrobialCultureTest,
    EnzymeTest,
    HormoneTest,
    TissueTest,
    MolecuralTest*/

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="idTestType")
   private Integer idTestType;
   private String testName;



    @ManyToMany(mappedBy = "TestList" )
    @JsonIgnore
    private List<Invoice> invoices = new ArrayList<>();


    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public TestType(String testName) {
        this.testName = testName;
    }

    public TestType() {
    }

    public TestType(Integer idTestType, String testName, List<Invoice> invoices) {
        this.idTestType = idTestType;
        this.testName = testName;
        this.invoices = invoices;
    }

    public TestType(Integer idTestType, String testName) {
        this.idTestType = idTestType;
        this.testName = testName;
    }
}
