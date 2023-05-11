package tn.esprit.biol.service;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.biol.dao.DaysOffDao;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.pdf.PdfWriter;

@Service
public class DaysOffService implements IDaysOffService {
    @Autowired
    EmailService emailService;
    @Autowired
    SmsService smsService;
    @Autowired
    UserDao userDao;
    @Autowired
    DaysOffDao daysOffDao;

    public Boolean sendLeaveRequest(String to, String id, LocalDate startDate, LocalDate endDate, String justification) throws IOException {


        // Envoi de l'email de demande de congé

        if (validateLeaveRequest(startDate, endDate)) {
           // emailService.sendLeaveRequestEmail(to, "Demande Congé", justification);
            // Sauvegarde de la demande de congé
            User user = userDao.findById(id).get();
            DaysOff daysOff = new DaysOff();
            daysOff.setRequest(false);
            daysOff.setStartDate(startDate);
            daysOff.setEndDate(endDate);
            daysOff.setJustification(justification);
            daysOff.setArchive(0);


            daysOff.setUser(user);
            daysOffDao.save(daysOff);

            return true;
        } else {
            return false;
        }
    }

    public boolean validateLeaveRequest(LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = LocalDate.now();
        if (startDate.isAfter(endDate)) {
            return false;

        } else {
            return true;
        }
    }
/*


    @Override
    public List<DaysOff> getAllDaysOff() {
        return daysOffDao.findAll();

    }
*/
    public String traiterDemandeConge(Integer idConge) throws Exception {

        DaysOff daysOff = daysOffDao.findById(idConge).get();
        Integer NB_MAX_DEMANDES_PAR_PERIODE = 3;
        daysOff.setRequest(true);
        int demandesEnCours = daysOffDao.countPersonnesEnCongeEntreDates(daysOff.getStartDate(), daysOff.getStartDate());
        if (daysOff.getImageData() == null) {
            // Vérifier si l'utilisateur a dépassé le nombre maximum de jours de congé par année

            long nbJoursCongeDemande = daysOff.getStartDate().until(daysOff.getEndDate(), ChronoUnit.DAYS) + 1;
            int nbJoursCongeUtilisateur = 0;

            if ((nbJoursCongeUtilisateur + nbJoursCongeDemande) > daysOff.getUser().getStaff_details().getNbrDaysOffPerYears()) {
                daysOff.setEtat(Etat.Refused);
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
        sendSMS();
        genererPdf(daysOff);
        return "Demande de congé acceptée.";
    }

    public ResponseEntity<String> sendSMS() {

        smsService.sendSms("+21694533488", "Hello votre demande de congé a ete accepté ");
        System.out.println("sms");


        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }

    public void genererPdf(DaysOff daysOff) throws Exception {

        // Chargement du logo du laboratoire
        Image logo = Image.getInstance("src/main/java/tn/esprit/biol/Images/logo.png");
        logo.scaleToFit(180, 180); // Redimensionnement du logo

        // Chargement de la signature
        Image signature = Image.getInstance("src/main/java/tn/esprit/biol/Images/cache.png");
        signature.scaleToFit(250, 250); // Redimensionnement de la signature

        // Création d'un nouveau document
        Document document = new Document();

        // Ajout des métadonnées du document
        document.addTitle("Demande de congé");
        document.addAuthor("Nom de l'auteur");
        document.addSubject("Demande de congé pour " + daysOff.getUser().getUserFirstName());
        document.addKeywords("congé, demande");

        // Ouverture du document
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/asus/Desktop/PdfConge/" + daysOff.getUser().getId() + ".pdf"));
        document.open();

        // Ajout du logo en haut de la page
        Paragraph logoParagraphe = new Paragraph();
        logo.setAlignment(Element.ALIGN_LEFT);
        logoParagraphe.add(logo);
        document.add(logoParagraphe);

        // Ajout de la signature en bas à droite
        signature.setAbsolutePosition(document.right() - 100, document.bottom() + 10);
        signature.scaleAbsolute(50, 50);
        document.add(signature);

        // Ajout des informations sur la demande de congé
        Paragraph titre = new Paragraph("Demande de congé", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD));
        titre.setAlignment(Element.ALIGN_CENTER);

        // Création d'un cadre autour des informations sur la demande de congé
        Rectangle rect = new Rectangle(36, 36, 559, 806);
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(1);
        document.add(rect);

        document.add(titre);
        document.add(new Paragraph("ID du médecin : " + daysOff.getUser().getId()));
        document.add(new Paragraph("Date de début : " + daysOff.getStartDate()));
        document.add(new Paragraph("Date de fin : " + daysOff.getEndDate()));

        // Fermeture du document
        document.close();
    }

    public double getPercentageOfStateAtDate(Etat etat, Date date) {
        Integer total = daysOffDao.getTotalDaysOff();
        long count = daysOffDao.countByEtatAndDateBetween(etat, date);
        return (double) count / total * 100;
    }
/*


    @Override
    public void deleteDaysOff(Integer Id) {
        daysOffDao.deleteById(Id);

    }
    @Override
    public DaysOff retrieveDaysOff(String Id_eq) {
        return daysOffDao.findById(Integer.valueOf(Id_eq)).get();
    }
    */
public ResponseEntity<Object> updateDaysOff(String id, DaysOff s) {
    DaysOff days =daysOffDao.findById(Integer.valueOf(id)).get();
    if (days != null ) {
        daysOffDao.save(s);
        return ResponseEntity.ok(s);
    }
    return ResponseEntity.notFound().build();
}

    public List<DaysOff> findDaysOffByUserId(String id) {
        return daysOffDao.findByUserId(id);
    }



}








