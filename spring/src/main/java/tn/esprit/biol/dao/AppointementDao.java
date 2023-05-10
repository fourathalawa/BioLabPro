package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.Appointement;

@Repository
public interface AppointementDao extends JpaRepository<Appointement,Integer> {

    public Appointement getAppointementByIdAppointement(Integer idAppointement);
}