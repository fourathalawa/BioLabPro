package tn.esprit.biol.service;

import tn.esprit.biol.entity.Rating;

import java.util.List;

public interface IRatingService {

     Rating addRating(int rate, int idtraining , String iduser);

     List<Rating> getAllRating();
     List<Rating> getRatingByUserID(String iduser);
}
