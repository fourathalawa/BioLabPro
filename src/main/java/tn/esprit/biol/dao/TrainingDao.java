package tn.esprit.biol.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.Training;

@Repository
public interface TrainingDao extends JpaRepository<Training,Integer> {
}
