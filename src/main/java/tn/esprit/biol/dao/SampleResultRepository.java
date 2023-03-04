package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.biol.entity.SampleResult;

import java.time.LocalDate;
import java.util.Date;


public interface SampleResultRepository extends JpaRepository<SampleResult, Integer> {
     @Query("SELECT COUNT(*) FROM SampleResult s WHERE s.sampleID =Sampleid")
    int countperid(@Param("Sampleid") Integer Sampleid);

    @Query("SELECT COUNT(*) FROM SampleResult s WHERE s.DateResult = dateResult")
    int countSampleResultByDateResult(@Param("dateResult") String dateResult);

    @Query("SELECT u.tel FROM SampleResult sr INNER JOIN Sample s ON sr.sampleID = s.SampleID INNER JOIN User u ON s.userID = u.id WHERE sr.ResultID =:ResultID")
    String getUserTelBySampleId(@Param("ResultID") Integer ResultID);

}