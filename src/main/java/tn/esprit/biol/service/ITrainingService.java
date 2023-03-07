package tn.esprit.biol.service;

import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.biol.entity.Training;

import java.util.List;

public interface ITrainingService {
    Training addTraining(Training training);
      Training updateTraining(Training t);
    void deleteTraining(@PathVariable int id);
    List<Training> getAllTraining();
    Training getTraining(int id);
    void affectTraineeToTraining(String iduser, int idtraining);
    void affectTrainerToTraining(String iduser, int idtraining);
     List<Training> RecommandedTraining(String iduser);

}
