package tn.esprit.biol.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.biol.dao.TrainingDao;
import tn.esprit.biol.entity.Training;
import tn.esprit.biol.service.TrainingService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Training")
public class TrainingController {
    @Autowired
    TrainingService trainingService;


    TrainingDao trainingDao;

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
        return trainingDao.getTrainingBySearch(word);
    }

    @PutMapping("/affect-trainee-training/{id-user}/{id-training}")
    public void affectTraineeToTraining(@PathVariable("id-user") String iduser , @PathVariable("id-training") int idtraining)
    {
        trainingService.affectTraineeToTraining(iduser,idtraining);
    }
}
