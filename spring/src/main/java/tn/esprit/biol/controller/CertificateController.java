package tn.esprit.biol.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.biol.service.CertificateService;

@RestController
@AllArgsConstructor
@RequestMapping("/Certificate")
public class CertificateController {

    @Autowired
    CertificateService certificateService;


}
