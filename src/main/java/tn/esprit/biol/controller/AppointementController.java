package tn.esprit.biol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.biol.entity.Appointement;
import tn.esprit.biol.service.AppointementService;
import tn.esprit.biol.service.EmailService;
import tn.esprit.biol.service.SmsService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Appointement")
public class AppointementController {
    @Autowired
    AppointementService appointementService;
    @Autowired
    EmailService emailService;
    @Autowired
    SmsService smsService;


    //Format de Date 08-7-2019 08:51:58
    @GetMapping("/NumberAppointementsByDate/{date}")
    public Integer getNumberAppointementsByDate(@PathVariable String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
        Instant instant = d.toInstant();
        LocalDateTime df = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return appointementService.FindAppointementsNumberPerDateandHour(df);
    }
    @GetMapping("/retrieveAppointements")
    public List<Appointement> getAppointements() {
        return appointementService.retreiveAllAppointment();
    }
    @GetMapping("/retrieveAppointement/{id}")
    public Appointement getAppointementByid(@PathVariable Integer id) {
        return appointementService.getAppById(id);
    }
    @PostMapping("/addAppointement")
    @ResponseBody
    public Appointement addAppointement(@RequestBody Appointement appointement) {

        // 3 rendez vous pas valider ( aaml rendez vous 3 marrat w majech ) => BAN
        if(appointementService.NumberOfAppointementsNotValidatedByPatient(appointement.getIdPatient())>=3){
            //integration appointment.patientid | patientid getEmailPatient
            String to = "assyl.kriaa@gmail.com";
            String subject = "You have Been Banned " ;
            //integration patient name
            String text = "Hello\t"+appointement.getIdPatient()+", \n By this email we tell you that you have been banned \n " +
                    "Contact us on +216 12 345 543 to settle your status .\n thank you \n ";
            emailService.sendSimpleMessage(to, subject, text);
            return null;
        }
        else{
        //partie front controle de saisie seul accepte 01:00 or 01:30 repartie charge nombre de salle exemple nbr salle (2)
        if(appointementService.FindAppointementsNumberPerDateandHour(appointement.getDateAppointement())<2 )
        {
            //integration appointment.patientid | patientid getEmailPatient
            String to = "assyl.kriaa@gmail.com";
            String subject = "Appointment registered" ;
            //integration patient name
            String text = "Hello\t"+appointement.getIdPatient()+", \n By this email we tell you that your appointment'"+appointement.getTypeAppointement()+"' has been well registered \n " +
                    "for this date"+appointement.getDateAppointement()+", \t an SMS will be sent before 30 minutes of the appointment time to remind you .\n thank you \n ";
            emailService.sendSimpleMessage(to, subject, text);

            return appointementService.AddAppointement(appointement);}
        else return null;

       }
    }
    @DeleteMapping("/deleteAppointement/{id}")
    @ResponseBody
    public void deleteAppointement(@PathVariable Integer id) {
        appointementService.DeleteAppointement(id);

    }
    @PutMapping("/updateAppointement/{id}")
    @ResponseBody
    public Appointement updateAppointement(@PathVariable Integer id ,@RequestBody Appointement a) {
       return appointementService.updateAppointement(id,a);
    }
    @PutMapping("/not_yet_reached/{id}")
    @ResponseBody
    public Appointement NotYetReached(@PathVariable Integer id ) {
        return appointementService.StatusAppointment_not_yet_reached(id);
    }
    @PutMapping("/validated/{id}")
    @ResponseBody
    public Appointement validated(@PathVariable Integer id ) {
        return appointementService.StatusAppointment_validated(id);
    }
    @PutMapping("/not_validated/{id}")
    @ResponseBody
    public Appointement not_validated(@PathVariable Integer id ) {
        return appointementService.StatusAppointment_not_validated(id);
    }

    @GetMapping("/NumberOfAppointementsByMonth/{month}")
    @ResponseBody
    public Integer NumberOfAppointementsByMonth(@PathVariable Integer month ) {
        return appointementService.NumberOfAppointementsByMonth(month);
    }
    @GetMapping("/NumberOfAppointementsByDayAndMonth/{day}/{month}")
    @ResponseBody
    public Integer NumberOfAppointementsByDayAndMonth(@PathVariable Integer day,@PathVariable Integer month) {
        return appointementService.NumberOfAppointementsByDayAndMonth(day,month);
    }
    @GetMapping("/NumberOfAppointementsByYear/{year}")
    @ResponseBody
    public Integer NumberOfAppointementsByYear(@PathVariable Integer year) {
        return appointementService.NumberOfAppointementsByYear(year);
    }
    @GetMapping("/NumberOfAppointementsByPatient/{idPatient}")
    @ResponseBody
    public Integer NumberOfAppointementsByPatient(@PathVariable String idPatient) {
        return appointementService.NumberOfAppointementsByPatient(idPatient);
    }
    @GetMapping("/NumberOfAppointementsByPatientByYearMonth/{idPatient}/{month}/{year}")
    @ResponseBody
    public Integer NumberOfAppointementsByPatientByYearMonth(@PathVariable String idPatient,@PathVariable Integer month,@PathVariable Integer year) {
        return appointementService.NumberOfAppointementsByPatientByYearMonth(idPatient,month,year);
    }

    @GetMapping("/NumberOfAppointementsNotYetReachedThisMonth")
    @ResponseBody
    public Integer NumberOfAppointementsNotYetReachedThisMonth() {
        return appointementService.NumberOfAppointementsNotYetReachedThisMonth();
    }
    @GetMapping("/NumberOfAppointementsValidatedThisMonth")
    @ResponseBody
    public Integer NumberOfAppointementsValidatedThisMonth() {
        return appointementService.NumberOfAppointementsValidatedThisMonth();
    }
    @GetMapping("/NumberOfAppointementsNotValidatedThisMonth")
    @ResponseBody
    public Integer NumberOfAppointementsNotValidatedThisMonth() {
        return appointementService.NumberOfAppointementsNotValidatedThisMonth();
    }
    @GetMapping("/NumlberofAppointmentsValidatedByPatient/{idPatient}")
    @ResponseBody
    public Integer NumlberofAppointmentsValidatedByPatient(@PathVariable String idPatient) {
        return appointementService.NumlberofAppointmentsValidatedByPatient(idPatient);
    }
    @GetMapping("/NumberOfAppointementsNotValidatedByPatient/{idPatient}")
    @ResponseBody
    public Integer NumberOfAppointementsNotValidatedByPatient(@PathVariable String idPatient) {
        return appointementService.NumberOfAppointementsNotValidatedByPatient(idPatient);
    }

    @GetMapping("/PatientBanned")
    @ResponseBody
    public List<String> PatientBanned() {
        return appointementService.PatientBanned();
    }


    @GetMapping("/sms/{toPhoneNum}/{msg}")
    @ResponseBody
    public String NotifierPatientManuelemenetParSms(@PathVariable String toPhoneNum,@PathVariable String msg) {
         smsService.sendSms(toPhoneNum,msg);
        return "message envoyé";
    }

}
