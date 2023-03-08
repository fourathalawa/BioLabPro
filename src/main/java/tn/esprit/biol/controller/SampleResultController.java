package tn.esprit.biol.controller;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.biol.dao.SampleResultRepository;
import tn.esprit.biol.entity.SampleResult;
import tn.esprit.biol.entity.User;
import tn.esprit.biol.service.ISampleResultService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import tn.esprit.biol.service.SampleResultService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Arrays;
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


    @GetMapping("/countparid/{Sampleid}")
    public int countperid(@PathVariable("Sampleid")Integer Sampleid) {
        return sampleResultsRepository.countperid(Sampleid);
    }

    /*
        @GetMapping("/countnumber/{dateResult}")
        public int countSampleResultByDateResult(@PathVariable("dateResult") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateResult) {
             return sampleResultsRepository.countSampleResultByDateResult(dateResult);
        }*/
    @GetMapping("/tel/{ResultID}")
    public String getUserTelBySampleId(@PathVariable("ResultID") Integer Sampleid){
        return sampleResultsRepository.getUserTelBySampleId(Sampleid);

    }


    @PostMapping(value="/sendSMS/{ResultID}" )
    public String sendSMS(@PathVariable("ResultID") Integer ResultID){
        String x = sampleResultsRepository.getUserTelBySampleId(ResultID);
        System.out.println(x);
        try {
            Twilio.init("AC19f1eec1d681257ac2ed943bf6a59243", "c7c671780fd48a57010b8b01dc94e321");

            Message message = Message.creator(
                            new PhoneNumber(x),
                            new PhoneNumber("+12706755516"),
                            "ntasti yé lowléd chiheb chiheb")
                    .create();

            return "message sent successfully";

        } catch (Exception e) {
            e.printStackTrace();
            return "message not sent";
        }
    }

    //http://localhost:8089/bio/sampleResult/generateQRCode/2
    @GetMapping("/generateQRCode/{ResultID}")
    public void generateQRCodeImage(@PathVariable("ResultID") int ResultID, HttpServletResponse response)
            throws WriterException, IOException {
        String filePath = "C:/pitest/" + ResultID + ".png";

        sampleResultService.generateQRCodeImage(String.valueOf(ResultID), 350, 350, filePath);

        OutputStream outputStream = response.getOutputStream();
        outputStream.write(filePath.getBytes());
        outputStream.flush();
    }
    /*
        @GetMapping("/searchByDateResult/{DateResult}")
        public List<SampleResult> findByDateResult(@RequestParam("DateResult") String DateResult) {
            List<SampleResult> sampleResult = sampleResultsRepository.findByDateResult(DateResult);
            if(sampleResult.isEmpty()){
                System.out.println("empty list of date");
                                        }
            return sampleResult;
        }*/
//http://localhost:8089/bio/sampleResult/searchBySampleID/2
    @GetMapping("/searchBySampleID/{sampleID}")
    public List<SampleResult> findBySampleID(@PathVariable("sampleID") Integer sampleID) {
        List<SampleResult> sampleResult = sampleResultsRepository.findBySampleID(sampleID);
        if(sampleResult.isEmpty()){
            System.out.println("empty list");
        }
        return sampleResult;
    }


    @GetMapping("/searchByUserID/{userID}")
    public List<SampleResult> findByUserID(@PathVariable("userID") User userID) {
        List<SampleResult> sampleResult = sampleResultsRepository.findByUserID(userID);
        if(sampleResult.isEmpty()){
            System.out.println("empty list");
        }
        return sampleResult;
    }


    @PostMapping("/save/{sampleid}")
    public SampleResult save(@RequestBody SampleResult sampleResult,@PathVariable("sampleid")Integer sampleid)
    {
        SampleResult savedSampleResult=sampleResultService.save(sampleResult);
        return sampleResultService.assign(savedSampleResult.getResultID(),sampleid);
    }
    @GetMapping("/generateQRCodeForSampleResult/{ResultID}")
    public void generateQRCodeForSampleResult(@PathVariable("ResultID") int ResultID) throws IOException, WriterException {
        SampleResult sampleResult = sampleResultService.getById(ResultID);
        String qrContent = String.format("Result Description: %s, Date Result: %s, Note: %s",
                sampleResult.getRDescription(), sampleResult.getDateResult().toString(), sampleResult.getNote());
        String filePath = String.format("C:\\QRCodes\\%s.png", sampleResult.getResultID());
        System.out.println(filePath);
        sampleResultService.generateQRCodeImage(qrContent, 300, 300, filePath);
    }




    @GetMapping("/pdf/{userID}")
    public void savePDF(@PathVariable("userID") User userID)  {
        Document document = new Document();
        List<SampleResult> sampleResult = sampleResultsRepository.findByUserID(userID);
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\pitest\\SampleResultList.pdf"));
            document.open();
            document.add(new Paragraph("Sample Result List"));
            document.add(new Paragraph(new Date().toString()));
            document.add(new Paragraph("-------------------------------"));
            PdfPTable table = new PdfPTable(3);
            table.addCell("Result ID");
            table.addCell("Description");
            table.addCell("Date Result");
            for (SampleResult c : sampleResult) {
                table.addCell(String.valueOf(c.getResultID()));
                table.addCell(c.getRDescription());
                table.addCell(String.valueOf(c.getDateResult()));
            }
            document.add(table);
            document.close();
            writer.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }




}






