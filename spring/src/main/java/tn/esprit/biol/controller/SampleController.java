package tn.esprit.biol.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.biol.entity.Sample;
import tn.esprit.biol.service.SampleService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @GetMapping("/")
    public List<Sample> getAllSamples(){
        return sampleService.getAllSamples();
    }

    @GetMapping("/{id}")
    public Sample getSampleById(@PathVariable int id){
        return sampleService.getSampleById(id);
    }

    @PostMapping("/add")
    public Sample saveSample(@RequestBody Sample sample){
        return sampleService.saveSample(sample);
    }

    @PutMapping("/{id}")
    public Sample updateSample(@PathVariable int id, @RequestBody Sample sample){
        return sampleService.updateSample(id,sample);
    }

    @DeleteMapping("/{id}")
    public void deleteSample(@PathVariable int id){
        sampleService.deleteSample(id);
    }

     //{startDate}/{endDate}
    @GetMapping("nburgency/{startDate}/{endDate}")
    public Integer nburgency(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate)
    { return  sampleService.nburgency(startDate,endDate);}


    @GetMapping("/getSamplesInDateRange/{startDate}/{endDate}")
    public List<Sample> getSamplesInDateRange(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return sampleService.getSamplesInDateRange(startDate, endDate);
    }
    @GetMapping("/withResult")
    public List<Sample> getSamplesWithResult(){
        return sampleService.getSamplesWithResult();
    }
    @GetMapping("/withoutresult")
    public List<Sample> getSampleswithoutResult(){
        return sampleService.getSamplesWithoutResult();
    }


}