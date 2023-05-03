package tn.esprit.biol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableScheduling
@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4200")
public class PiSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiSpringApplication.class, args);
        System.out.println("version: " + SpringVersion.getVersion());

    }


}
