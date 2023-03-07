package tn.esprit.biol.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.biol.dao.CertificateDao;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.Certificate;
import tn.esprit.biol.entity.Training;
import tn.esprit.biol.entity.User;

import java.util.List;

@Service
public class CertificateService implements ICertificateService {

    @Autowired
    CertificateDao certificateDao;
    @Autowired
    UserDao userDao;
    @Override
    public Certificate addCertification(Certificate certificate, String iduser)
    {
        User user= userDao.findById(iduser).get();
        certificate.setTrainee(user);
        certificateDao.save(certificate);
        return certificate;
    }

    @Override
    public Certificate updateCertificate(Certificate certificate) {
        Certificate cert = certificateDao.save(certificate);
        return cert;
    }

    @Override
    public void deleteCertificate( int id) {
        certificateDao.deleteById(id);
    }


    @Override
    public List<Certificate> getAllCertificate() {
        return (List<Certificate>) certificateDao.findAll();
    }


    @Override
    public Certificate getCertificate(int id) {
        Certificate certificate = certificateDao.findById(id).get();
        return certificate;
    }
}
