package tn.esprit.biol.controller;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.biol.dao.SampleResultRepository;
import tn.esprit.biol.entity.SampleResult;
import tn.esprit.biol.service.ISampleResultService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import tn.esprit.biol.service.SampleResultService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sampleResult")

public class SampleResultController {


    @Autowired
    private SampleResultRepository sampleResultsRepository;

    @Autowired
    private ISampleResultService sampleResultService;

    @PostMapping("/save")
    public SampleResult save(@RequestBody SampleResult sampleResult){
        return sampleResultService.save(sampleResult);
    }

    @DeleteMapping("/delete/{ResultID}")
    public void delete(@PathVariable int ResultID){
        sampleResultService.delete(ResultID);
    }

    @PutMapping("/update")
    public SampleResult Update(@RequestBody SampleResult sampleResult){
        return sampleResultService.update(sampleResult);
    }

    @GetMapping("/get")
    public List<SampleResult> getAll(){
        return sampleResultService.getAll();
    }

    @GetMapping("/{ResultID}")
    public SampleResult getById(@PathVariable int ResultID){
        return sampleResultService.getById(ResultID);
    }

@PutMapping("/assign/{resultid}/{sampleid}")
    public SampleResult assign(@PathVariable("resultid")Integer resultid,@PathVariable("sampleid")Integer sampleid)
{
    return sampleResultService.assign(resultid,sampleid);
}


    @GetMapping("/sample/result/count/{Sampleid}")
    public int countperid(@PathVariable("Sampleid")Integer Sampleid) {
         return sampleResultsRepository.countperid(Sampleid);
    }


    @GetMapping("/countnumber/{dateResult}")
    public int countSampleResultByDateResult(@PathVariable("dateResult")Date dateResult) {
         return sampleResultsRepository.countSampleResultByDateResult(dateResult.toString());
    }
    @GetMapping("/tel/{ResultID}")
    public String getUserTelBySampleId(@PathVariable("ResultID") Integer Sampleid){
        return sampleResultsRepository.getUserTelBySampleId(Sampleid);

    }


    @PostMapping(value="/sendSMS" )
    public String sendSMS(){

        try {
            Twilio.init("AC19f1eec1d681257ac2ed943bf6a59243", "330df1fbf90ee82dd505ca3a9fd2c717");

            Message message = Message.creator(
                            new PhoneNumber("+21653940997"),
                            new PhoneNumber("+12706755516"),
                            "test")
                    .create();

            return "message sent successfully";

        } catch (Exception e) {
            e.printStackTrace();
            return "message not sent";
        }
}


     @GetMapping("/generateQRCode/{ResultID}")
    public void generateQRCodeImage(@PathVariable("ResultID") int ResultID, HttpServletResponse response)

            throws WriterException, IOException {
        String filePath = "C:/pitest/" + ResultID + ".png";

         sampleResultService.generateQRCodeImage(String.valueOf(ResultID), 350, 350, filePath);

        OutputStream outputStream = response.getOutputStream();
        outputStream.write(filePath.getBytes());
        outputStream.flush();
    }
}

