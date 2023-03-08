package tn.esprit.biol.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import tn.esprit.biol.entity.Etat;
import tn.esprit.biol.service.DaysOffService;
import tn.esprit.biol.service.EmailService;
import tn.esprit.biol.service.ImageUtils;
import tn.esprit.biol.service.StorageService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public ResponseEntity<String> sendRequest(@RequestParam("justif") String justification,
                                              @RequestParam("debut")String startDate,
                                              @RequestParam("fin") String endDate,
                                              @RequestParam(value = "image") MultipartFile file) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate sdate = LocalDate.parse(startDate, formatter);
        LocalDate edate = LocalDate.parse(endDate, formatter);

        String jus = "Le biologiste ayant l'identifiant 07998550 demande les codes des résultats de la période allant du " + startDate + " jusqu'au " + endDate+" avec la justification ci -dessus "+justification;

        // Envoyer la demande de congé
        boolean success = daysOffService.sendLeaveRequest("houda.koubaa@esprit.tn", "07998550", sdate, edate, jus, file);

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
/*
    @GetMapping("/all-DaysOff")
    public List<DaysOff> getAllDaysOff() {
        List<DaysOff> daysOffList = daysOffDao.findAll();
        for (DaysOff daysOff : daysOffList) {
            byte[] imageData = storageService.downloadImage(daysOff.getId());
          daysOff.setImageData(imageData);
        }

        return daysOffList;
    }
       // return daysOffList;
    //}
    */
    /*
@GetMapping("/image/{id}")
    public ResponseEntity<?> getImageWithDates(@PathVariable Integer id) {
        Optional<DaysOff> daysOffData = daysOffDao.findById(id);
        if (daysOffData.isPresent()) {
            // Récupérer la réponse multipartie
            ResponseEntity<byte[]> response = getImage(id);
            byte[] imageBytes = response.getBody();

            // Extraire les informations de date de la réponse multipartie
            MultiValueMap<String, String> parts = new LinkedMultiValueMap<>();
            String contentType = response.getHeaders().getContentType().toString();
            String boundary = contentType.substring(contentType.lastIndexOf("boundary=") + "boundary=".length());
            String[] bodyParts = new String(response.getBody()).split("--" + boundary);
            for (String part : bodyParts) {
                String[] headersAndBody = part.split("\r\n\r\n");
                if (headersAndBody.length > 1 && headersAndBody[0].contains("name=\"startDate\"")) {
                    parts.add("startDate", headersAndBody[1].trim());
                } else if (headersAndBody.length > 1 && headersAndBody[0].contains("name=\"endDate\"")) {
                    parts.add("endDate", headersAndBody[1].trim());
                }
            }

            // Créer un objet contenant les informations de date et les données de l'image
            Map<String, Object> data = new HashMap<>();
            data.put("startDate", parts.getFirst("startDate"));
            data.put("endDate", parts.getFirst("endDate"));
            data.put("imageData", imageBytes);

            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
*/
    //@Scheduled(cron = "0 0 10 * * ?") // à 10h du matin tous les jours
    @Scheduled(cron = "0 0 0 * * ?") // à minuit tous les jours
    public void verifierDateFinConge() {
        LocalDate now = LocalDate.now();
        List<DaysOff> daysOffList = daysOffDao.findByEndDate(now);
        for (DaysOff daysOff : daysOffList) {
            daysOff.setEtat(Etat.terminé);
            daysOffDao.save(daysOff);
            if (LocalTime.now().isAfter(LocalTime.of(8, 0))) {
                emailService.sendLeaveRequestEmail(daysOff.getUser().getEmail(),"Fin De Congé","Votre Congé est terminé merci d'etre present");
            }
        }
    }



}
