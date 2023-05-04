package tn.esprit.biol.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.biol.dao.DaysOffDao;
import tn.esprit.biol.entity.DaysOff;
import tn.esprit.biol.entity.Equipment;
import tn.esprit.biol.entity.Etat;
import tn.esprit.biol.entity.Staff_Details;
import tn.esprit.biol.service.DaysOffService;
import tn.esprit.biol.service.EmailService;
import tn.esprit.biol.service.ImageUtils;
import tn.esprit.biol.service.StorageService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController

@RequestMapping("/DaysOff")
public class DaysOffController {
    private MappingJackson2HttpMessageConverter jacksonConverter;

    @Autowired
    DaysOffService daysOffService;
    @Autowired
    private DaysOffDao daysOffDao;
    @Autowired
    EmailService emailService;
    @Autowired
    StorageService storageService;


    @PostMapping("/RequestDaysOff")
        public ResponseEntity<String> sendRequest(@RequestBody DaysOff daysOff) throws Exception {


        String jus = "Le biologiste ayant l'identifiant 07998550 demande les codes des résultats de la période allant du " + daysOff.getStartDate() + " jusqu'au " + daysOff.getEndDate() + " avec la justification ci -dessus " + daysOff.getJustification();


        // Envoyer la demande de congé
        boolean success = daysOffService.sendLeaveRequest("houda.koubaa@esprit.tn", "07998550", daysOff.getStartDate(), daysOff.getEndDate(), jus);

        if (!success) {
            return ResponseEntity.badRequest().body("La demande n'est pas valide, vérifiez vos dates.");
        } else {
            return ResponseEntity.ok("Votre demande a été envoyée avec succès.");
        }
    }


    @PostMapping("/demandeConge/{idConge}")
    public ResponseEntity<String> traiterDemandeCongeTest(@PathVariable Integer idConge) throws Exception {
        String result = daysOffService.traiterDemandeConge(idConge);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all-DaysOff")
    public List<DaysOff> getAllDaysOff() {
        List<DaysOff> daysOffList = daysOffDao.findAll();


        return daysOffList;
    }


    //@Scheduled(cron = "0 0 10 * * ?") // à 10h du matin tous les jours
    @Scheduled(cron = "0 0 0 * * ?") // à minuit tous les jours
    public void verifierDateFinConge() {
        LocalDate now = LocalDate.now();
        List<DaysOff> daysOffList = daysOffDao.findByEndDate(now);
        for (DaysOff daysOff : daysOffList) {
            daysOff.setEtat(Etat.terminé);
            daysOffDao.save(daysOff);
            if (LocalTime.now().isAfter(LocalTime.of(8, 0))) {
                emailService.sendLeaveRequestEmail(daysOff.getUser().getEmail(), "Fin De Congé", "Votre Congé est terminé merci d'etre present");
            }
        }
    }

    @PostMapping("/percentageByStateAndDate")
    public ResponseEntity<Map<String, Double>> getPercentageByStateAndDate(@RequestParam Etat etat, @RequestParam String date) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dd = formatter.parse(date);
        Double percentage = daysOffService.getPercentageOfStateAtDate(etat, dd);
        Map<String, Double> result = new HashMap<>();
        result.put("percentage", percentage);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteDaysOff/{Id}")
    public void deleteEquipment(@PathVariable("Id") String IdDayOff) {

        daysOffDao.deleteById(Integer.valueOf(IdDayOff));
    }

    @GetMapping("/getDaysOff/{id}")
    public DaysOff retrieveDaysOff(@PathVariable("id") String Id_eq) {
        return daysOffDao.findDaysOffById(Integer.valueOf(Id_eq));
    }

    @PutMapping("/updateDaysOff/{id}")
    public ResponseEntity<Object> updateDaysOff(@PathVariable(value = "id") String id,@RequestBody DaysOff s) {

        return daysOffService.updateDaysOff(id,s);
    }


    @PutMapping("/archive/{id}")
    public void archiveEntity(@PathVariable Integer id) {
        DaysOff entity = daysOffDao.findDaysOffById(id);
        entity.setArchive(1);
        daysOffDao.save(entity);
    }

    @PostMapping("/test/")
    public void genererPdf(@RequestBody DaysOff s) throws Exception {
        daysOffService.genererPdf(s);

    }
}
