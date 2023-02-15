package tn.esprit.biol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.biol.service.InvoiceService;

@RestController
@RequestMapping("/Invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
}
