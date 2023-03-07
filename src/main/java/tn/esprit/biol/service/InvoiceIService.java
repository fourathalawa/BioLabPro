package tn.esprit.biol.service;
import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.biol.entity.Invoice;
import tn.esprit.biol.entity.TestType;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceIService {
    public Invoice AddInvoice(Invoice a) ;
    public void DeleteInvoice(Integer id);
    public Invoice updateInvoice(Integer id , Invoice ap);
    public List<Invoice> retreiveAllInvoices();
    public Invoice findInvoiceById(Integer id);
    public void AddTestTypetoInvoice(Integer id , String testType);
    public Invoice changePaymentStatus(Integer id);

    public Float revenuesPerDate(LocalDateTime date);
    public Float revenuesPerMont(Integer Month,Integer year);
    public Float revenuesPerYear(Integer year);
    public Float AmmountNotPayedPerMont(Integer Month,Integer year);
    public Float AmmountNotPayedPerYear(Integer year);
    @Scheduled(fixedDelay = 40000) //chaque 40 sec
    void envoyersmsInvoice();
}
