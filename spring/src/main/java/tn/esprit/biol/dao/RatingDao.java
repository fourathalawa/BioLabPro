package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.Rating;
import tn.esprit.biol.entity.Training;

import java.util.List;

@Repository
public interface RatingDao extends JpaRepository<Rating,Integer> {

    @Query(value = " SELECT * FROM rating   where user_id like :iduser",nativeQuery = true)
    List<Rating> getRatingByIDUSER(String iduser);
    @Query(value = " SELECT * FROM rating   where user_id like :iduser group by training_id",nativeQuery = true)
    List<Rating> getRatingByIDUSERG(String iduser);

}
