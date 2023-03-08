package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.biol.entity.Equipment;

public interface EquipmentDao  extends JpaRepository<Equipment,String> {
}
