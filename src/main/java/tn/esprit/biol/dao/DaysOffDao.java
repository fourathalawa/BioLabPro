package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.biol.entity.DaysOff;

public interface DaysOffDao extends JpaRepository<DaysOff,Integer> {
}
