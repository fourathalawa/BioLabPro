package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.DaysOff;
import tn.esprit.biol.entity.Etat;
import tn.esprit.biol.entity.Invoice;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Repository
public interface DaysOffDao extends JpaRepository<DaysOff,Integer> {
    @Query("SELECT COUNT(c) FROM DaysOff c WHERE c.etat= 'EnCour' AND c.startDate >= :startDate AND c.endDate <= :endDate")
    int countPersonnesEnCongeEntreDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<DaysOff> findByEndDate(LocalDate endDate);
    @Query("SELECT COUNT(d) FROM DaysOff d")
    int getTotalDaysOff();


    @Query("SELECT COUNT(d) FROM DaysOff d WHERE d.etat = :etat AND :date BETWEEN d.startDate AND d.endDate")
    long countByEtatAndDateBetween(@Param("etat") Etat etat, @Param("date") Date date);

    public DaysOff findDaysOffById(Integer id);

}
