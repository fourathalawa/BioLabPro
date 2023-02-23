package tn.esprit.biol.service;
import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.biol.entity.Invoice;
import tn.esprit.biol.entity.TestType;

import java.text.ParseException;
import java.util.List;

public interface InvoiceIService {
    public Invoice AddInvoice(Invoice a) ;
    public void DeleteInvoice(Integer id);
    public Invoice updateInvoice(Integer id , Invoice ap);
    public List<Invoice> retreiveAllInvoices();
    public Invoice findInvoiceById(Integer id);
    public void AddTestTypetoInvoice(Integer id , String testType);

    @Scheduled(fixedDelay = 40000) //chaque 40 sec
    void envoyersmsInvoice();
}
