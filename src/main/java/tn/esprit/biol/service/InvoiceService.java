package tn.esprit.biol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.biol.dao.AppointementDao;
import tn.esprit.biol.dao.InvoiceDao;

@Service
public class InvoiceService implements InvoiceIService{
    @Autowired
    InvoiceDao invoiceDao;
}
