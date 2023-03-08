package tn.esprit.biol.service;


import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import tn.esprit.biol.dao.SampleRepository;
import tn.esprit.biol.dao.SampleResultRepository;
import tn.esprit.biol.entity.Sample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.biol.entity.SampleResult;

import javax.management.Query;
import javax.persistence.TypedQuery;

@Slf4j

@Service
public class SampleResultService implements ISampleResultService{
    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private SampleResultRepository SampleResultDao;

    public SampleResult save(SampleResult sampleResult) {
        return SampleResultDao.save(sampleResult);
    }

    public void delete(int ResultID) {
        SampleResultDao.deleteById(ResultID);
    }

    public SampleResult update(SampleResult sampleResult) {
        return SampleResultDao.save(sampleResult);
    }

    public List<SampleResult> getAll() {
        return SampleResultDao.findAll();
    }

    public SampleResult getById(int ResultID) {
        return SampleResultDao.findById(ResultID).get();

    }
    @Override
    public SampleResult assign (Integer resultid, Integer sampleid){

        SampleResult sampleResult = SampleResultDao.findById(resultid).orElse(null);
        Sample sample = sampleRepository.findById(sampleid).orElse(null);
        sampleResult.setSampleID(sample);
        return  SampleResultDao.save(sampleResult);

    }
    public void generateQRCodeImage(String text, int width, int height, String filePath)

            throws WriterException, IOException {
//        filePath="C:\\pitest";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hintMap);
        Path path = FileSystems.getDefault().getPath(filePath);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ImageIO.write(bufferedImage, "png", path.toFile());
    }




}

