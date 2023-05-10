package tn.esprit.biol.dao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
 import tn.esprit.biol.entity.Sample;

import java.util.Date;
import java.util.List;

@Repository
 public interface SampleRepository extends JpaRepository<Sample,Integer> {

 @Query("SELECT s FROM Sample s WHERE s.Dateofwithdrawl BETWEEN :startDate AND :endDate")
 public List<Sample> findByDateofwithdrawlBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
 @Query("select s from Sample s where s.SampleID in (select r.sampleID from SampleResult r )")
 List<Sample> findSamplesWithResult();

 @Query("select s from Sample s where s.SampleID not in (select r.sampleID from SampleResult r )")
 List<Sample> findSamplesWithoutResult();


}