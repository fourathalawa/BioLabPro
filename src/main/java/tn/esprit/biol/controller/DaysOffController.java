package tn.esprit.biol.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.biol.dao.DaysOffDao;
import tn.esprit.biol.entity.DaysOff;
import tn.esprit.biol.entity.Staff_Details;
import tn.esprit.biol.entity.User;
import tn.esprit.biol.service.DaysOffService;
import tn.esprit.biol.service.EmailService;
import tn.esprit.biol.service.ImageUtils;
import tn.esprit.biol.service.StorageService;

import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/DaysOff")
public class DaysOffController {

    @Autowired
    DaysOffService daysOffService;
    @Autowired
    private DaysOffDao daysOffDao;

/*
    @PostMapping("/RequestDaysOff")

    public ResponseEntity<?> sendRequest(@RequestParam("image") MultipartFile file,
                                              @RequestParam("jus") String justification,
                                              @RequestParam("st")LocalDate startDate,
                                              @RequestParam("end")LocalDate endDate) throws Exception {

        // Créer un objet DaysOff à partir des paramètres reçus
        //  DaysOff daysOff = new DaysOff(justification, startDate, endDate, imageData.getBytes());
        // MultipartFile image = new MockMultipartFile("image.jpg", imageData.getBytes());
        String jus = "Le biologiste ayant l'identifiant 07998550 demande les codes des résultats de la période allant du " + startDate + " jusqu'au " + endDate+" avec la justification ci -dessus "+justification;
        // Envoyer la demande de congé
        boolean success = daysOffService.sendLeaveRequest("houda.koubaa@esprit.tn", "07998550", startDate,
                endDate, jus, file);

        if (!success) {
            return ResponseEntity.badRequest().body("La demande n'est pas valide, vérifiez vos dates.");
        } else {
            return ResponseEntity.ok("Votre demande a été envoyée avec succès.");
        }
    }

*/
    @PostMapping("/demandeConge/{idConge}")
    public ResponseEntity<String> traiterDemandeCongeTest(@PathVariable Integer idConge) {
        String result = null;
        try {
            result = daysOffService.traiterDemandeConge(idConge);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all-DaysOff")
    public List<DaysOff> getStaff() {
        List<DaysOff> daysOffList = daysOffService.getAllDaysOff();
        for (DaysOff daysOff : daysOffList) {
            byte[] images = ImageUtils.decompressImage(daysOff.getImageData());
            daysOff.setImageData(images);

        }
        return daysOffList;
    }



    @PostMapping("/RequestDaysOff")
    public String sendLeaveRequest(@RequestParam("image") MultipartFile file,
                                   @RequestParam("jus") String justification,
                                   @RequestParam("st")LocalDate startDate,
                                   @RequestParam("end") LocalDate endDate) throws IOException {
        return daysOffService.uploadImage(file, justification,startDate);
    }

}
