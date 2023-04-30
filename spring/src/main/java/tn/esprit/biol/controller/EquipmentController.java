package tn.esprit.biol.controller;

import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.biol.PDFEquipment.EquipmentPDF;
import tn.esprit.biol.QrCode.CodeGenerator;
import tn.esprit.biol.entity.Equipment;
import tn.esprit.biol.service.IequipmentService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/equipment")
@AllArgsConstructor
public class EquipmentController {
    @Autowired
    IequipmentService equipmentService;
    @Autowired
    JavaMailSender mailSender;

    // http://localhost:8089/kaddem/equipe/retrieve-all-equipments
    @GetMapping("/retrieve-all-equipments")
    public List<Equipment> getAllEquipments() {
        List<Equipment> listEquipment = equipmentService.getAllEquipments();
        return listEquipment;
    }
   /* // http://localhost:8089/kaddem/equipe/retrieve-equipment/8
    @GetMapping("/retrieve-equipement/{Id_eq}")
    public Equipment retrieveEquipment(@PathVariable("Id_eq") Integer Id_eq) {
        return equipmentService.retrieveEquipment(Id_eq);
    }*/
    @PostMapping({"/addNewEq"})
    public ResponseEntity<?> addNewEq(@RequestBody Equipment equipment)
    {
        return equipmentService.AddEq(equipment);
    }
   // @PostMapping("/AddEquipment")
    /*public Equipment AddEquipment(@RequestBody Equipment e) {
        Equipment equipement = equipmentService.AddEquipment(e);
        return equipement;
    }*/
   // http://localhost:8089/kaddem/equipe/remove-equipe/1
   /* @DeleteMapping("/deleteEquipment/{Id_eq}")
    public ResponseEntity<?> deleteEquipment(@PathVariable("Id_eq") Integer Id_eq) {

        return equipmentService.deleteEq(Id_eq);
    }*/

    @DeleteMapping({"/delete/{Id_eq}"})
    public ResponseEntity<?> DeleteEq(@PathVariable("Id_eq") Integer Id_eq)
    {
        return equipmentService.deleteEq(Id_eq);
    }

    @DeleteMapping({"/delet/{id}"})
    public void Delete(@PathVariable("id") Integer id)
    {
         equipmentService.Deletep(id);
    }

    // http://localhost:8089/kaddem/equipe/update-equipe
    @PutMapping("/updateEquipment/{id}")
    public Equipment updateEquipment(@RequestBody Equipment e,@PathVariable("id") Integer id) {
        Equipment equipment= equipmentService.updateEquipment(e,id);
        return equipment;
    }

//    @PostMapping({"/Sterilization"})
//    @Scheduled(cron ="* * * 30 * *")
//    public void SendRequest()
//    {
//        equipmentService.sendSterilizationRequest("hannachifedi12@gmail.com","ALERT STERILIZATION","STERILIZATION DATE IS HERE !!!!!!");
//
//    }

    @PostMapping({"/Storage"})
    @Scheduled(cron ="* * * * 12 *")
    public void SendStorageRequest()
    {
            equipmentService.sendLowStorageRequest("hannachifedi12@gmail.com","LOW STORAGE ALERT","CHECK QUANTITY !!!!!!");

    }

    @GetMapping("/A/{field}")
    public List<Equipment> getEquipmentswithASCSorting(@PathVariable String field) {
        List<Equipment> listEquipmentAS = equipmentService.getAllEquipmentswithASCSorting(field);
        return listEquipmentAS;
    }

    @GetMapping("/D/{field}")
    public List<Equipment> getEquipmentswithDESCSorting(@PathVariable String field) {
        List<Equipment> listEquipmentDESC = equipmentService.getAllEquipmentswithDESCSorting(field);
        return listEquipmentDESC;
    }

    @GetMapping("/pagination/{offset}/{pagesize}")
    public Page<Equipment> getEquipmentswithPag(@PathVariable Integer offset, @PathVariable Integer pagesize) {
        Page<Equipment> listEquipmentPG = equipmentService.getAllEquipmentswithPagination(offset, pagesize);
        return listEquipmentPG;
    }
    @GetMapping("/stat")
    public int  stateq(){
        return equipmentService.ExpirationThisMonth();
    }

    @GetMapping("/sum")
    public int  sumeq(){
        return equipmentService.totalQuantexpThisMonth();
    }

    @GetMapping("/getEq/{Id_eq}")
    Equipment getEq(@PathVariable Integer Id_eq)
    {
        return equipmentService.getEquipment(Id_eq);
    }


    @GetMapping("/export/pdf/{Id_eq}")
    public void exportToPDF(HttpServletResponse response, @PathVariable int Id_eq) throws DocumentException, IOException, MessagingException, com.lowagie.text.DocumentException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);


        Equipment equipment = equipmentService.getEquipment(Id_eq);

        EquipmentPDF exporter = new EquipmentPDF(equipment);
        exporter.export(response);

        // Generate a download link to the PDF file

        String pdfDownloadLink = "http://localhost:8089/bio/equipment/export/pdf/" + Id_eq;

        // Generate QR code for the download link
        byte[] qrcode = null;
        try {
            qrcode = CodeGenerator.getQRCodeImage(pdfDownloadLink, 150, 150);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        // Convert QR code byte array into Base64 encoded string
        String qrcodeData = Base64.getEncoder().encodeToString(qrcode);

        // Send the email with the QR Code image

        String subject = "QR Code";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("biolab9900@gmail.com");
        helper.setTo("hannachifedi12@gmail.com");
        helper.setSubject(subject);

        // Create a new ByteArrayDataSource with the Base64 encoded QR code
        ByteArrayDataSource qrcodeDataSource = new ByteArrayDataSource(Base64.getDecoder().decode(qrcodeData), "image/png");

        // Add the QR code as an attachment
        helper.addAttachment("qrcode.png", qrcodeDataSource);
        // Add the PDF file download link to the email body
        String emailBody = "<html><body><p>Please find attached the PDF file.</p>" +
                "<p>You can also download the PDF file by scanning the QR code below:</p>" +
                "<img src='data:image/png;base64," + qrcodeData + "' alt='QR Code' width='150' height='150'>" +
                "</body></html>";
        helper.setText(emailBody, true);
        mailSender.send(message);
    }









}
