package tn.esprit.biol.service;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.biol.dao.CertificateDao;
import tn.esprit.biol.dao.TrainingDao;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.Certificate;
import tn.esprit.biol.entity.Training;
import tn.esprit.biol.entity.User;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

@Service
public class CertificateService implements ICertificateService {

    @Autowired
    CertificateDao certificateDao;
    @Autowired
    UserDao userDao;
    @Autowired
    TrainingDao trainingDao;
    @Override
    public Certificate addCertification(int id_training,Certificate certificate)
    {
         String iduser="07998550";
        User user= userDao.findById(iduser).get();
        Training training=trainingDao.findById(id_training).get();
        certificate.setTrainee(user);
        certificate.setTraining(training);
        certificateDao.save(certificate);
        return certificate;
    }

    @Override
    public Certificate updateCertificate(Certificate certificate , int idcertifcate) {
        Certificate cert = certificateDao.findById(idcertifcate).get();
        cert=certificate;
        certificateDao.save(cert);
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
