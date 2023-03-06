package tn.esprit.biol.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import tn.esprit.biol.dao.DaysOffDao;
import tn.esprit.biol.dao.StaffRepository;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.DaysOff;
import tn.esprit.biol.entity.Etat;
import tn.esprit.biol.entity.Staff_Details;
import tn.esprit.biol.entity.User;

import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.validation.ConstraintValidatorContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@Service
public class DaysOffService implements IDaysOffService {
    @Autowired
    EmailService emailService;

    @Autowired
    UserDao userDao;
    @Autowired
    DaysOffDao daysOffDao;



    public Boolean sendLeaveRequest(String to, String id,LocalDate startDate, LocalDate endDate, String justification, MultipartFile file) throws IOException {


        // Envoi de l'email de demande de congé

        if (validateLeaveRequest(startDate, endDate)) {
            emailService.sendLeaveRequestEmail(to, "Demande Congé", justification);
            // Sauvegarde de la demande de congé
            User user = userDao.findById(id).get();
            DaysOff daysOff = new DaysOff();
            daysOff.setRequest(false);
            daysOff.setStartDate(startDate);
            daysOff.setEndDate(endDate);
            daysOff.setJustification(justification);
            System.out.println(file);
            //daysOff.setImageData(imaImageUtils.compressImage(file.getBytes()));
/*
            DaysOff imageData = daysOffDao.save(DaysOff.builder()
                    .imageData(ImageUtils.compressImage(file.getBytes())).build());
            if (imageData != null) {
                System.out.println( "file uploaded successfully : " + file.getOriginalFilename());
            }
           */

            daysOff.setUser(user);
            daysOffDao.save(daysOff);

            return true;
        } else {
            return false;
        }
    }

    public boolean validateLeaveRequest(LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = LocalDate.now();
        long daysNotice = ChronoUnit.DAYS.between(currentDate,startDate);
        if (startDate.isAfter(endDate)) {
            return false;
        } else if (daysNotice <= 5) //si demande de congé n'est pas envoyé avant 5 jrs de date debut de demande
        {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<DaysOff> getAllDaysOff() {
        return   daysOffDao.findAll();

    }

    public String traiterDemandeConge(Integer idConge) {

        DaysOff daysOff = daysOffDao.findById(idConge).get();
        Integer NB_MAX_DEMANDES_PAR_PERIODE = 3;
        daysOff.setRequest(true);
        int demandesEnCours = daysOffDao.countPersonnesEnCongeEntreDates( daysOff.getStartDate(), daysOff.getStartDate());
if(daysOff.getImageData()==null) {
    // Vérifier si l'utilisateur a dépassé le nombre maximum de jours de congé par année

    long nbJoursCongeDemande = daysOff.getStartDate().until(daysOff.getEndDate(), ChronoUnit.DAYS) + 1;
    int nbJoursCongeUtilisateur = daysOff.getUser().getStaff_details().getSommeDaysOff();

    if ((nbJoursCongeUtilisateur + nbJoursCongeDemande) > daysOff.getUser().getStaff_details().getNbrDaysOffPerYears()) {
        daysOff.setEtat(Etat.Refuse);
        return "Demande de congé refusée : l'utilisateur a dépassé le nombre maximum de jours de congé par année.";
    }

    // Vérifier si d'autres utilisateurs ont déjà demandé des congés pendant la même période

    if (demandesEnCours > NB_MAX_DEMANDES_PAR_PERIODE) {

        daysOff.setEtat(Etat.EnCour);
        return "Demande de congé refusée : le nombre maximal de demandes de congé pour cette période a été atteint.";
    }

}
        // Enregistrer la demande de congé dans la base de données
        daysOff.setEtat(Etat.EnCour);
        daysOffDao.save(daysOff);
        //sendSMS();
        return "Demande de congé acceptée.";
    }

    public ResponseEntity<String> sendSMS() {

        String accountSid = "ACc7590419e3a8dc6a9773214b2835a2cf";
        String authToken = "679c05522fa99ede0a8b682c58a4df6a";
        Twilio.init(accountSid, authToken);
        Message.creator(new PhoneNumber("+21694533488"),
                new PhoneNumber("+15746266388" ), "Hello votre demande de congé a ete accepté   📞").create();

        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }
}
