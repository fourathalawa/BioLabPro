package tn.esprit.biol.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.biol.dao.ImageRepository;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.ImageModel;
import tn.esprit.biol.entity.User;
import tn.esprit.biol.service.ImageService;


@RestController


public class ImageUploadController {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    UserDao userDao;


   @Autowired
   ImageService imageService;

    @PostMapping("/image/upload/{id}")
    public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file,@PathVariable("id") String id) throws IOException {

      ImageModel img = imageService.compressedImage(file);
        imageRepository.save(img);
        User user = userDao.findById(id).get();
        user.setImage(img);
        userDao.save(user);
        return ResponseEntity.status(HttpStatus.OK);
    }

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            // Récupérez le chemin absolu du dossier images
            String uploadDir = "/chemin/vers/votre/projet/images";
            // Récupérez le nom du fichier
            String fileName = file.getOriginalFilename();
            // Créez le chemin complet pour le fichier
            Path filePath = Paths.get(uploadDir, fileName);
            // Enregistrez le fichier
            Files.write(filePath, file.getBytes());
            // Retournez le chemin du fichier pour la récupération
            return ResponseEntity.ok().body(filePath.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de l'image.");
        }
    }





}
