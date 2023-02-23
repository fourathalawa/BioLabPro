package tn.esprit.biol.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.Training;

import java.util.List;

@Repository
public interface TrainingDao extends JpaRepository<Training,Integer> {

    @Query(value = " SELECT * FROM training   where training_name like %:word% or training_subject like %:word%",nativeQuery = true)
    List<Training> getTrainingBySearch( String word);
}
