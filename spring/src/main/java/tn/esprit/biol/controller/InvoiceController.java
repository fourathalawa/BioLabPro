package tn.esprit.biol.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import tn.esprit.biol.entity.Invoice;
import tn.esprit.biol.entity.TestType;
import tn.esprit.biol.service.EmailService;
import tn.esprit.biol.service.InvoiceService;
import tn.esprit.biol.service.PdfGenerateService;
import tn.esprit.biol.service.TestTypeService;

import javax.mail.MessagingException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Invoice")
@Slf4j
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    TestTypeService testTypeService;
    @Autowired
    PdfGenerateService pdfGenerateService;

    @Autowired
    EmailService emailService;
    @GetMapping("/retreiveAllInvoices")
    public List<Invoice> retreiveAllInvoices(){
        return invoiceService.retreiveAllInvoices();
    }

    @GetMapping("/retreiveInvoice/{id}")
    public Invoice getInvoiceByid(@PathVariable Integer id){
        return invoiceService.findInvoiceById(id);
    }


    @PostMapping("/sendMailReclamation/{to}/{subject}/{text}")
    public String SendMail(@PathVariable String to,@PathVariable String subject,@PathVariable String text) throws MessagingException {
        emailService.sendSimpleMessage(to,subject,text);
        return "mail sended";
    }

    @PostMapping("/addInvoice")
    @ResponseBody
    public Invoice addInvoice(@RequestBody Invoice invoice)throws Exception{
        Invoice a =invoiceService.AddInvoice(invoice);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:\\xampp\\htdocs\\PdfInvoices\\"+a.getIdInvoice()+".pdf"));
        // Open the document
        document.open();
        Image image = Image.getInstance("C:\\PIDEV\\BioLabPro\\spring\\src\\main\\resources\\pdf-templates\\logo.png");
        document.add(image);
        // Add some content to the document
        Paragraph paragraph = new Paragraph("Please find below the details for the file number :"+a.getIdInvoice()+" concerning \n the test(s) carried out on :"+a.getDateInvoice()+" by = "+a.getIdPatient()+" \n\n\n ");
        document.add(paragraph);
        Paragraph paragraph2 = new Paragraph("Analyses performed :\n\n ");
        document.add(paragraph2);
        List<ListItem> list = a.getTestList().stream().map(t -> new ListItem(" - " + t.getTestName())).collect(Collectors.toList());
        list.stream().map(Objects::toString).map(Paragraph::new).forEach(p -> {
            try {
                document.add(p);
            } catch (DocumentException e) {
                log.error(e.getMessage());
            }
        });
        Paragraph paragraph0 = new Paragraph("Your Total Ammount (tva included)  : "+a.getTotalAmount()+"\n\n ");
        document.add(paragraph0);
        Paragraph paragraph4 = new Paragraph("Descreption  : "+a.getDescreptionInvoice()+"\n\n ");
        document.add(paragraph4);

        // Close the document
        document.close();

        //integration
        String to = "assyl.kriaa@gmail.com";
        String subject = "Your Invoice ";
        //integration patient name
        String text = "Hello\t"+a.getIdPatient()+", \n This is your invoice an SMS will be sent after 48 minutes of this date  time to remind you about the payment in case you didnt pay .\n thank you \n ";
        emailService.sendMessageWithAttachment(to,subject,text,"C:\\xampp\\htdocs\\PdfInvoices\\"+a.getIdInvoice()+".pdf");
        return a;

    }


    @DeleteMapping("/deleteInvoice/{id}")
    @ResponseBody
    public void deleteInvoice(@PathVariable Integer id) {
        invoiceService.DeleteInvoice(id);

    }

    @PutMapping("/updateInvoice/{id}")
    @ResponseBody
    public Invoice updateInvoice(@PathVariable Integer id ,@RequestBody Invoice a) throws IOException, DocumentException, MessagingException {
        Invoice c=  invoiceService.updateInvoice(id,a);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:\\xampp\\htdocs\\PdfInvoices\\"+c.getIdInvoice()+".pdf"));
        // Open the document
        document.open();

        Image image = Image.getInstance("D:\\BioLabPro\\src\\main\\resources\\pdf-templates\\logo.png");
        document.add(image);
        // Add some content to the document
        Paragraph paragraph = new Paragraph("Invoice Updated Please find below the details for the file number :"+c.getIdInvoice()+" concerning \n the test(s) carried out on :"+c.getDateInvoice()+" by = "+c.getIdPatient()+" \n\n\n ");
        document.add(paragraph);
        Paragraph paragraph2 = new Paragraph("Analyses performed :\n\n ");
        document.add(paragraph2);
        List list = new ArrayList();
        for(TestType t :c.getTestList()) {
            list.add(new ListItem(" - "+t.getTestName()));

        }
        for (Object item : list) {
            Paragraph paragraph3 = new Paragraph(item.toString());
            document.add(paragraph3);
        }
        Paragraph paragraph0 = new Paragraph("Your Total Ammount (tva included)  : "+c.getTotalAmount()+"\n\n ");
        document.add(paragraph0);
        Paragraph paragraph4 = new Paragraph("Descreption  : "+c.getDescreptionInvoice()+"\n\n ");
        document.add(paragraph4);



        // Close the document
        document.close();

        //integration
        String to = "assyl.kriaa@gmail.com";
        String subject = "Your Invoice (Updated)";
        //integration patient name
        String text = "Hello\t"+c.getIdPatient()+", \n This is your invoice an SMS will be sent after 48 hours of this date  time to remind you about the payment in case you didnt pay .\n thank you \n ";
        emailService.sendMessageWithAttachment(to,subject,text,"C:\\PdfInvoices\\"+c.getIdInvoice()+".pdf");
        return c;
    }
    @PostMapping("/addTestType")
    @ResponseBody
    public TestType addTestType( @RequestBody TestType a) {
        return testTypeService.addTest(a);
    }

    @PostMapping("/AddTestTypetoInvoice/{id}/{idtestType}")
    @ResponseBody
    public void AddTestTypetoInvoice( @PathVariable Integer id, @PathVariable  Integer idtestType) {
         invoiceService.AddTestTypetoInvoice(id,idtestType);
    }

    @GetMapping("/PourcentageTest/{testName}")
    @ResponseBody
    public Float PourcentageTestType( @PathVariable  String testName) {
       return testTypeService.PourcentageTestTypes(testName);
    }

    @GetMapping("/AmountnotPayedperPatient/{idP}")
    @ResponseBody
    public Float AmountnotPayedperPatient( @PathVariable  String idP) {
        return invoiceService.AmmountNotPayedPerPatient(idP);
    }

    @GetMapping("/AmountPayedperPatient/{idP}")
    @ResponseBody
    public Float AmountPayedperPatient( @PathVariable  String idP) {
        return invoiceService.AmmountPayedPerPatient(idP);
    }

    @GetMapping("/AmounttotalperPatient/{idP}")
    @ResponseBody
    public Float AmounttotalperPatient( @PathVariable  String idP) {
        return invoiceService.totalammountbypatient(idP);
    }


    @GetMapping("/revenuesPerDate/{date}")
    @ResponseBody
    public Float revenuesPerDate( @PathVariable  String date ) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Instant instant = d.toInstant();
        LocalDateTime df = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return invoiceService.revenuesPerDate(df);
    }
    @GetMapping("/revenuesPerMonthYear/{Year}/{Month}")
    @ResponseBody
    public Float revenuesPerMonthYear( @PathVariable  Integer Year,@PathVariable  Integer Month ){
        return invoiceService.revenuesPerMont(Month,Year);
    }
    @GetMapping("/revenuesPerYear/{Year}")
    @ResponseBody
    public Float revenuesPerYear( @PathVariable  Integer Year )  {
        return invoiceService.revenuesPerYear(Year);
    }

    @GetMapping("/Unpayed_AmmountPerMonthYear/{Year}/{Month}")
    @ResponseBody
    public Float Unpayed_AmmountPerMonthYear( @PathVariable  Integer Year,@PathVariable  Integer Month ){
        return invoiceService.AmmountNotPayedPerMont(Month,Year);
    }
    @GetMapping("/Unpayed_AmmountPerYear/{Year}")
    @ResponseBody
    public Float Unpayed_AmmountPerYear( @PathVariable  Integer Year )  {
        return invoiceService.AmmountNotPayedPerYear(Year);
    }

    @PutMapping("/changePaymentStatus/{id}")
    @ResponseBody
    public Invoice changePaymentStatus(@PathVariable Integer id){
        return invoiceService.changePaymentStatus(id);
    }



@GetMapping("/getInvoicesByidPatien/{idPatient}")
@ResponseBody
public List<Invoice> getInvoicesByidPatien(@PathVariable String idPatient){
        return invoiceService.getInvoicesByidPatien(idPatient);
        }
        }