package tn.esprit.biol.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.biol.dao.TrainingDao;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.*;
import tn.esprit.biol.service.EmailService;
import tn.esprit.biol.service.RatingService;
import tn.esprit.biol.service.SearchService;
import tn.esprit.biol.service.TrainingService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Training")
public class TrainingController {
    @Autowired
    TrainingService trainingService;

    @Autowired
    EmailService emailService;
    @Autowired
    RatingService ratingService;
    @Autowired
    SearchService searchService;
    TrainingDao trainingDao;
    UserDao userDao;

    @PostMapping("/add-training")
        public Training addTraining(@RequestBody Training t) {
        Training training = trainingService.addTraining(t);
        return training;
    }


    @DeleteMapping("/delete-training/{id-training}")
    public void deleteTraining(@PathVariable("id-training") int id)
    {
         trainingService.deleteTraining(id);
    }


    @GetMapping("/getAllTraining")
    public List<Training> getAllTraining()
    {
        return trainingService.getAllTraining();
    }
    @GetMapping("/getTraining/{id-training}")
    public Training getTrainingById(@PathVariable("id-training") int id)
    {
        return trainingService.getTraining(id);
    }

    @GetMapping("/getTraining/search/{word}")
    public List<Training> SearchTraining(@PathVariable("word") String word)
    {
        String id="07998550";
        List<Training> training = trainingDao.getTrainingBySearch(word);
        if(!training.isEmpty()) {
            Search search = searchService.addSearch(word, id);
        }
        return training;
    }

    @PostMapping("/affect-trainee-training/{id-user}/{id-training}")
    public void affectTraineeToTraining(@PathVariable("id-user") String iduser , @PathVariable("id-training") int idtraining)
    {
        trainingService.affectTraineeToTraining(iduser,idtraining);
        Training training = trainingDao.findById(idtraining).get();
        User user = userDao.findById(iduser).get();
        String message="Hello Mr/Ms"+user.getUserFirstName()+",\n"+
                "your registration for the training  "+training.getTrainingSubject()+" is well registered.";

        //emailService.sendLeaveRequestEmail(training.getTrainingSubject(),user.getEmail(),message);
    }
    @GetMapping("/getTraining/suggestion")
    public List<Training> SuggestionTraining()
    {
        String id="07998550";

        return trainingService.RecommandedTraining(id);
    }


    @PostMapping("/rating/addrating/{id-training}/{id-user}/{rate}")
    public Rating AddRating(@PathVariable("id-training") int idtraining, @PathVariable("id-user") String iduser, @PathVariable("rate") int rate)
    {

        Rating rating = ratingService.addRating(rate,idtraining,iduser);
        return rating;
    }

    @GetMapping("/rating/Allrating")
    public List<Rating> getAllRating ()
    {
        return ratingService.getAllRating();
    }
    @GetMapping("/getTraining/search/Allsearch")
    public List<Search> getAllSearch ()
    {
        return searchService.getAllSearch();
    }
    @GetMapping("/rating/ratingbyiduser/{iduser}")
    public List<Rating> getRatingByIDuser (@PathVariable("iduser") String iduser)
    {
        return ratingService.getRatingByUserID(iduser);
    }

//    @PostMapping("/sendMail")
//    public String
//    sendMail(@RequestBody Email details)
//    {
//        String status
//                = emailService.(details);
//
//        return status;
//    }
}
