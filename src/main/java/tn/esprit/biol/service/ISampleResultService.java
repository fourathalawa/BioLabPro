package tn.esprit.biol.service;

import com.google.zxing.WriterException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.biol.entity.Sample;
import tn.esprit.biol.entity.SampleResult;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface ISampleResultService {

     SampleResult save(SampleResult sampleResult);

     void delete(int ResultID);

     SampleResult update(SampleResult sampleResult);

     List<SampleResult> getAll();

     SampleResult getById(int ResultID);
     SampleResult assign (Integer resultid, Integer sampleid);

     void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException;


}
