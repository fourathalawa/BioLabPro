package tn.esprit.biol.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.biol.dao.RatingDao;
import tn.esprit.biol.dao.SearchDao;
import tn.esprit.biol.dao.TrainingDao;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.*;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TrainingService  implements  ITrainingService{

    @Autowired
    TrainingDao trainingDao;
    @Autowired
    UserDao userDao;
    @Autowired
    SearchDao searchDao;
    @Autowired
    EmailService emailService;
    @Autowired
    RatingDao ratingDao;
//EmailService emailService;

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
public List<Training> getTrainingByWord(String word)
{
    return  trainingDao.getTrainingByWord(word);
}
@Override
public List<Training> RecommandedTraining(String iduser)
{
    List<Rating> ratingList= ratingDao.getRatingByIDUSER(iduser);
    List<Search> searchList= searchDao.getSearchByIDUSER(iduser);
    List<Training> trainingParList = trainingDao.getTrainingByIDUSER(iduser);
    List<Training> trainingList = trainingDao.findAll();
    HashMap<String,Double> recommanded= new HashMap<String, Double>();
    for(Training training : trainingList)
    { double score=0;
        for(Training tr: trainingParList)
        {
            if(training.getTrainingSubject().equals(tr.getTrainingSubject()))
            {
                score+=5;
              //  recommanded.put(training.getTrainingSubject(),score);
            }
        }
        for(Search sr: searchList)
        {
            if(training.getTrainingSubject().contains(sr.getExpression()))
            {
                score+=1.5;
                //  recommanded.put(training.getTrainingSubject(),score);
            }
        }

        for(Rating rs:ratingList)
        {
            if(training.getTrainingId()==rs.getTraining().getTrainingId())
            {
                score+=3.5*rs.getRating();
                //  recommanded.put(training.getTrainingSubject(),score);
            }
        }
        if (!recommanded.isEmpty())
        {
            Iterator it = recommanded.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry mapentry = (Map.Entry) it.next();
                if(training.getTrainingSubject().equals(mapentry.getKey()))
                {
                    recommanded.put(training.getTrainingSubject(),score);
                }
            }
        }
        else
            recommanded.put(training.getTrainingSubject(),score);

    }
    List<Training> trainingList1=new ArrayList<>();
    recommanded.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).findFirst();
    Iterator it = recommanded.entrySet().iterator();
    while(it.hasNext())
    {
        Map.Entry mapentry = (Map.Entry) it.next();
       for(Training tr : getTrainingByWord(mapentry.getKey().toString()))
       {
           trainingList1.add(tr);
       }
    }
return trainingList1;
}
@Override
    public void affectTrainerToTraining(String iduser, int idtraining)
    {
        User trainer = userDao.findById(iduser).get();

        Training training = trainingDao.findById(idtraining).get();
        training.setTrainer(trainer);
        trainer.setTraining(training);
        userDao.save(trainer);
        trainingDao.save(training);
    }


    @Override
    @Scheduled(fixedDelay = 40000) //chaque 40 sec
    public void SendMailReminder() {
        List<Training> liste = trainingDao.findAll();
        for (Training a : liste) {

            if (a.getTraining_startdate().minusHours(1).withSecond(0).withNano(0).compareTo(LocalDateTime.now().withSecond(0).withNano(0).plusMinutes(30)) == 0) {
                //intgeration phone number a.getPatientId | getPhoneNumberById(a.getPatientId)

                Set<User> user = a.getTrainees();
                for (User b : user) {
                    emailService.sendSimpleMessage(b.getEmail(), "Training Reminder", "Hello ,\n This is a reminder for your training. \n Thank you for being present on time.");
                }
            }
        }
    }
    }

