package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.DaysOff;
import tn.esprit.biol.entity.Etat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Repository
public interface DaysOffDao extends JpaRepository<DaysOff,Integer> {
    @Query("SELECT COUNT(c) FROM DaysOff c WHERE c.etat= 'EnCour' AND c.startDate >= :startDate AND c.endDate <= :endDate")
    int countPersonnesEnCongeEntreDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    List<DaysOff> findByEndDate(LocalDate endDate);

}
