package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.biol.entity.SampleResult;
import tn.esprit.biol.entity.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface SampleResultRepository extends JpaRepository<SampleResult, Integer> {
    @Query("SELECT COUNT(*) FROM SampleResult s WHERE s.sampleID =Sampleid")
    int countperid(@Param("Sampleid") Integer Sampleid);
/*
    @Query("SELECT s FROM SampleResult s WHERE s.DateResult =:dateResult")
    int countSampleResultByDateResult(@Param("dateResult") Date dateResult);*/

    @Query("SELECT u.tel FROM SampleResult sr INNER JOIN Sample s ON sr.sampleID = s.SampleID INNER JOIN User u ON s.userID = u.id WHERE sr.ResultID =:ResultID")
    String getUserTelBySampleId(@Param("ResultID") Integer ResultID);
    /*

        @Query("SELECT s FROM SampleResult s WHERE s.DateResult =dateResult")
         List<SampleResult> findByDateResult(@Param("dateResult") String dateResult);
    */
    @Query("Select s from SampleResult s where s.sampleID=sampleID")
    List<SampleResult> findBySampleID(@Param("sampleID") Integer sampleID);

    @Query("SELECT s FROM SampleResult s INNER JOIN Sample samp ON s.sampleID = samp.SampleID WHERE samp.userID = :userID")
    List<SampleResult> findByUserID(@Param("userID") User userID);
}


