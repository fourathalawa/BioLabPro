package tn.esprit.biol.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.Rating;
import tn.esprit.biol.entity.Training;

import java.util.List;

@Repository
public interface TrainingDao extends JpaRepository<Training,Integer> {

    @Query(value = " SELECT * FROM training   where training_name like %:word% or training_subject like %:word%",nativeQuery = true)
    List<Training> getTrainingBySearch( String word);
    @Query(value = " SELECT * FROM training   group by training_subject",nativeQuery = true)
    List<Training> suggestionTraining();
    @Query(value = " SELECT * FROM user_training   where user_id like :iduser",nativeQuery = true)
    List<Training> getTrainingByIDUSER(String iduser);

    @Query(value = " SELECT * FROM user_training as us, training as tr where us.user_id like :iduser and us.training_id = tr.training_id group by tr.training_name",nativeQuery = true)
    List<Training> getTrainingByIDUSERG(String iduser);

    @Query(value = " SELECT * FROM training   where training_subject like :word",nativeQuery = true)
    List<Training> getTrainingByWord(String word);
}
