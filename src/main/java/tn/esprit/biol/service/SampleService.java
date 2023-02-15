package tn.esprit.biol.service;


 import org.springframework.stereotype.Service;
 import tn.esprit.biol.Repository.SampleRepository;
 import tn.esprit.biol.entity.Sample;

 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
 import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SampleService implements ISampleService {

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
}
