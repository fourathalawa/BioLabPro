package tn.esprit.biol.controller;


import com.sun.mail.util.QEncoderStream;
import lombok.AllArgsConstructor;
import org.checkerframework.framework.qual.PolymorphicQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.biol.entity.Certificate;
import tn.esprit.biol.service.CertificateService;
import tn.esprit.biol.service.QrCodeGenerator;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Certificate")
public class CertificateController {

    @Autowired
    CertificateService certificateService;

    @Autowired
    QrCodeGenerator qrCodeGenerator;
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QrCode/QRCode.png";

    @PostMapping("/add-Certificate/{id-training}")
    public Certificate addCertificate(@PathVariable("id-training") int id_training, @RequestBody Certificate certificate)
    {
        return certificateService.addCertification(id_training,certificate);
    }


    @GetMapping("/getCertificate/{idcertificate}")
public  Certificate getCertificateById(@PathVariable("idcertificate") int id_certificate)
    {
        return certificateService.getCertificate(id_certificate);
    }

    @GetMapping("/getAllCertificate")

    public List<Certificate> getAllCrtificate()
    {
        return certificateService.getAllCertificate();
    }
    @PutMapping("/updateCertficate/{idCertificate}")
    public Certificate updateCertificate(@PathVariable("idCertificate") int id_certificate, @RequestBody Certificate certificate)
    {
        return certificateService.updateCertificate(certificate,id_certificate);
    }
    @GetMapping(value = "/genrateAndDownloadQRCode/{code}")
    public void download(
            @PathVariable("code") String codeText)
            throws Exception {
       qrCodeGenerator.generateQRCodeImageCertificate(codeText,  QR_CODE_IMAGE_PATH);
    }
}
