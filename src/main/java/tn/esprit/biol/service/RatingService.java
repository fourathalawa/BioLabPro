package tn.esprit.biol.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.biol.dao.RatingDao;
import tn.esprit.biol.dao.TrainingDao;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.Rating;
import tn.esprit.biol.entity.Search;

import java.util.List;

@Service
public class RatingService implements IRatingService{

@Autowired
    TrainingDao trainingDao;
    @Autowired
    RatingDao ratingDao;
    @Autowired
    UserDao userDao;


    @Override
    public Rating addRating(int rate, int idtraining ,String iduser)
    {

        Rating rating= new Rating();
        rating.setUser(userDao.findById(iduser).get());
        rating.setRating(rate);
        rating.setTraining(trainingDao.findById(idtraining).get());
        ratingDao.save(rating);
        return rating;
    }
@Override
    public List<Rating> getAllRating()
{
    return ratingDao.findAll();
}
//
public List<Rating> getRatingByUserID(String iduser)
{
    return ratingDao.getRatingByIDUSER(iduser);
}
 }
