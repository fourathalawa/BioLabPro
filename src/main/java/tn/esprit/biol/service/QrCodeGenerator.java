package tn.esprit.biol.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.biol.dao.CertificateDao;
import tn.esprit.biol.entity.Certificate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public class QrCodeGenerator {

    @Autowired
    CertificateDao certificateDao;

    public  void generateQRCodeImageCertificate(String text, String filePath)
            throws WriterException, IOException {
        int width=120;
        int height=120;
        Certificate certificate = certificateDao.findById(Integer.parseInt(text)).orElse(null);
        if(certificate!=null) {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        }
    }




}