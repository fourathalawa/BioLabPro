package tn.esprit.biol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.biol.dao.AppointementDao;
import tn.esprit.biol.dao.InvoiceDao;
import tn.esprit.biol.dao.TestTypeDao;
import tn.esprit.biol.entity.Appointement;
import tn.esprit.biol.entity.Invoice;
import tn.esprit.biol.entity.TestType;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class InvoiceService implements InvoiceIService{
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    TestTypeDao testTypeDao;
    @Autowired
    AppointementDao appointementDao;
    @Autowired
    AppointementService appointementService;

    @Autowired
    SmsService smsService;
    @Override
    public Invoice AddInvoice(Invoice a) {
        Float total=(float)0;
        Float tvaRate=(float)0;
        List<TestType> liste = a.getTestList();
        for(TestType t :liste){
            if(t.getTestName().equals("BloodTest")){total = total + 20;}
            if (t.getTestName().equals("EnzymeTest")) {total = total + 15;}
            if (t.getTestName().equals("HormoneTest")) {total = total + (float)30.5;}
            if (t.getTestName().equals("MolecuralTest")) {total = total + 17;}
            if (t.getTestName().equals("TissueTest")) {total = total + (float)10.5;}
            if(t.getTestName().equals("MicrobialCultureTest")){total = total + (float)27.8;}
            if(t.getTestName().equals("Urinalysis")){total = total + (float)9.2;}
        }
       if(total<10){tvaRate=(float)0;}
        if((10<total)&&(total<30)){tvaRate=(float)0.1;}
        if((30<total)){tvaRate=(float)0.2;}
        //les remises de 10 % sur les patient qui ont deja fait plus que 3 rendez vous valider
        if(appointementService.NumlberofAppointmentsValidatedByPatient(a.getIdPatient())>3)
        {
            total = total - ((total*10)/100);
        }
        total = total + tvaRate * total;
       /* LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(zoneId));
        calendar.set(localDateTime.getYear(), localDateTime.getMonthValue()-1, localDateTime.getDayOfMonth(),
                localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());
        calendar.set(Calendar.MILLISECOND, localDateTime.getNano() / 1000000);
        Date date = calendar.getTime();*/
        a.setDateInvoice(LocalDateTime.now());
        //System.out.println(total);
        a.setTotalAmount(total);
        return invoiceDao.save(a);
    }

    @Override
    public void DeleteInvoice(Integer id) {
        Invoice ap  = invoiceDao.findInvoiceByIdInvoice(id);
        invoiceDao.delete(ap);
    }

    @Override
    public Invoice updateInvoice(Integer id, Invoice ap) {
        Invoice a  = invoiceDao.findInvoiceByIdInvoice(id);
        Float total=(float)0;
        Float tvaRate=(float)0;
        List<TestType> liste = a.getTestList();
        List<TestType> listenew = ap.getTestList();
        // reading the old list of a
      /*  for(TestType t :liste){
            if(t.getTestName().equals("BloodTest")){total = total + 20;}
            if (t.getTestName().equals("EnzymeTest")) {total = total + 15;}
            if (t.getTestName().equals("HormoneTest")) {total = total + (float)30.5;}
            if (t.getTestName().equals("MolecuralTest")) {total = total + 17;}
            if (t.getTestName().equals("TissueTest")) {total = total + (float)10.5;}
            if(t.getTestName().equals("MicrobialCultureTest")){total = total + (float)27.8;}
            if(t.getTestName().equals("Urinalysis")){total = total + (float)9.2;}
        }*/
        //reading the new list of ap
        for(TestType t :listenew){
            if(t.getTestName().equals("BloodTest")){total = total + 20;}
            if (t.getTestName().equals("EnzymeTest")) {total = total + 15;}
            if (t.getTestName().equals("HormoneTest")) {total = total + (float)30.5;}
            if (t.getTestName().equals("MolecuralTest")) {total = total + 17;}
            if (t.getTestName().equals("TissueTest")) {total = total + (float)10.5;}
            if(t.getTestName().equals("MicrobialCultureTest")){total = total + (float)27.8;}
            if(t.getTestName().equals("Urinalysis")){total = total + (float)9.2;}
        }
        if(total<10){tvaRate=(float)0;}
        if((10<total)&&(total<30)){tvaRate=(float)0.1;}
        if((30<total)){tvaRate=(float)0.2;}
        if(appointementService.NumlberofAppointmentsValidatedByPatient(ap.getIdPatient())>3)
        {
            total = total - ((total*10)/100);
        }
        total = total + tvaRate * total;

        /*LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(zoneId));
        calendar.set(localDateTime.getYear(), localDateTime.getMonthValue()-1, localDateTime.getDayOfMonth(),
                localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());
        calendar.set(Calendar.MILLISECOND, localDateTime.getNano() / 1000000);
        Date date = calendar.getTime();*/

        a.setDateInvoice(LocalDateTime.now());
        a.setDescreptionInvoice(ap.getDescreptionInvoice());
        a.setTestList(ap.getTestList());
        a.setIdPatient(ap.getIdPatient());
        a.setStatusPayment(ap.getStatusPayment());
        a.setTotalAmount(total);
        return invoiceDao.save(a);

    }

    @Override
    public List<Invoice> retreiveAllInvoices() {
        return invoiceDao.findAll();
    }

    @Override
    public Invoice findInvoiceById(Integer id) {
        return invoiceDao.findInvoiceByIdInvoice(id);
    }

    @Override
    public void AddTestTypetoInvoice(Integer id, String testType) {
        Invoice invoice = invoiceDao.findInvoiceByIdInvoice(id);
        invoice.getTestList().add(testTypeDao.findFirstByTestName(testType));
    }

    @Override
    @Scheduled(fixedDelay = 40000) //chaque 40 sec
    public void envoyersmsInvoice() {
        List<Invoice> liste = invoiceDao.findAll();
        for(Invoice a : liste){

            if((a.getDateInvoice().minusHours(1).withSecond(0).withNano(0).compareTo(LocalDateTime.now().withSecond(0).withNano(0).minusDays(2))==0)&&(a.getStatusPayment()==0)){
                //intgeration phone number a.getPatientId | getPhoneNumberById(a.getPatientId)
                smsService.sendSms("+21658413462","You have spent 2 days without paying your bill"+a.getIdInvoice()+" of a total = "+a.getTotalAmount()+" tnd . \n please pay before this date : "+a.getDateInvoice().plusDays(15)+" otherwise, We will rely on the judiciary to recover our dues ");
                System.out.println("sms");
            }
        }

    }
}
