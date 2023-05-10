package tn.esprit.biol.service;


 import org.springframework.stereotype.Service;
 import tn.esprit.biol.dao.SampleRepository;
 import tn.esprit.biol.dao.UserDao;
 import tn.esprit.biol.entity.Sample;

 import java.util.Date;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
 import lombok.extern.slf4j.Slf4j;
 import tn.esprit.biol.entity.User;

@Slf4j
@Service
public class SampleService implements ISampleService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private SampleRepository sampleRepository;

    @Override
    public List<Sample> getAllSamples() {
        return sampleRepository.findAll();
    }

     public Sample getSampleById(int id) {
        return sampleRepository.findById(id).get();
    }

     public Sample saveSample(Sample sample) {
        return sampleRepository.save(sample);
    }

     public Sample updateSample(int id, Sample sample) {
        sample.setSampleID(id);
        return sampleRepository.save(sample);
    }

     public void deleteSample(int id) {
        sampleRepository.deleteById(id);
    }


     public Integer nburgency(Date startDate, Date endDate) {
        List<Sample> Samples=sampleRepository.findAll();
        int i=0;
        for (Sample c:Samples) {
            if ((c.getDateofwithdrawl().after(startDate) && c.getDateofwithdrawl().before(endDate)) )
            {
                if (!c.isUrgency()) {
                    i++;
                }
            }
        }
        return i;
    }
    public List<Sample> getSamplesInDateRange(Date startDate, Date endDate) {
        return sampleRepository.findByDateofwithdrawlBetween(startDate, endDate);
    }
    public List<Sample> getSamplesWithResult(){
        return sampleRepository.findSamplesWithResult();
    }

    public List<Sample> getSamplesWithoutResult() {
        return sampleRepository.findSamplesWithoutResult();
    }




}
