package tn.esprit.biol.entity;

import java.time.LocalDateTime;

public class PdfFacture {
    private String companyName;
    private LocalDateTime Date;
    private String address;
    private String email;
    private Integer PatientId;
    private Integer PaymentStatus;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPatientId() {
        return PatientId;
    }

    public void setPatientId(Integer patientId) {
        PatientId = patientId;
    }

    public Integer getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        PaymentStatus = paymentStatus;
    }
}
