package tn.esprit.biol.service;

import tn.esprit.biol.entity.Sample;

import java.util.Date;
import java.util.List;

public interface ISampleService {

    List<Sample> getAllSamples();
    Sample getSampleById(int id);
    Sample saveSample(Sample sample);
    Integer nburgency(Date startDate, Date endDate);
    List<Sample> getSamplesInDateRange(Date startDate, Date endDate);
    List<Sample> getSamplesWithResult();
    List<Sample> getSamplesWithoutResult();
}
