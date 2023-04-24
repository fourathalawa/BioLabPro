package tn.esprit.biol.service;

import tn.esprit.biol.entity.Certificate;

import java.util.List;

public interface ICertificateService {
    Certificate addCertification(Certificate certificate, String iduser);
    Certificate getCertificate(int id);
    List<Certificate> getAllCertificate();
    void deleteCertificate( int id);
    Certificate updateCertificate(Certificate certificate);
}
