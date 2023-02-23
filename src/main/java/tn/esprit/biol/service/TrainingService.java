package tn.esprit.biol.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.biol.dao.TrainingDao;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.Training;
import tn.esprit.biol.entity.TrainingMethod;
import tn.esprit.biol.entity.User;

import java.util.List;

@Service
public class TrainingService  implements  ITrainingService{

    @Autowired
    TrainingDao trainingDao;
    @Autowired
    UserDao userDao;


    @Override
    public Training addTraining(Training t)
    {
        Training training =trainingDao.save(t);
        return training;
    }

    @Override
    public Training updateTraining(Training t) {
        Training training = trainingDao.save(t);
        return training;
    }

    @Override
    public void deleteTraining(@PathVariable int id) {
        trainingDao.deleteById(id);
    }


    @Override
    public List<Training> getAllTraining() {
        return (List<Training>) trainingDao.findAll();
    }


    @Override
    public Training getTraining(int id) {
        Training training = trainingDao.findById(id).get();
        return training;
    }

    @Override
  public void affectTraineeToTraining(String iduser, int idtraining)
    {
     User trainee = userDao.findById(iduser).get();

     Training training = trainingDao.findById(idtraining).get();
        training.getTrainees().add(trainee);
        trainee.getTrainings().add(training);
        userDao.save(trainee);
        trainingDao.save(training);
    }
}
