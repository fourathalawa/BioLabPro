package tn.esprit.biol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.biol.dao.DaysOffDao;
import tn.esprit.biol.entity.DaysOff;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private DaysOffDao repository;

    public String uploadImage(MultipartFile file) throws IOException {

        DaysOff imageData = repository.save(DaysOff.builder()
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage( Integer id){

        Optional<DaysOff> dbImageData = repository.findById(id);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
