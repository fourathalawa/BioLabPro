package tn.esprit.biol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.biol.entity.Appointement;
import tn.esprit.biol.service.AppointementService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Appointement")
public class AppointementController {
    @Autowired
    AppointementService appointementService;


    //Format de Date 08-7-2019 08:51:58
    @GetMapping("/NumberAppointementsByDate/{date}")
    public Integer getNumberAppointementsByDate(@PathVariable String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyy-M-d hh:mm:ss").parse(date);
        Instant instant = d.toInstant();
        LocalDateTime df = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return appointementService.FindAppointementsNumberPerDateandHour(df);
    }
    @GetMapping("/retrieveAppointements")
    public List<Appointement> getAppointements() {
        return appointementService.retreiveAllAppointment();
    }
    @GetMapping("/retrieveAppointement/{id}")
    public Appointement getAppointementByid(@PathVariable Integer id) {
        return appointementService.getAppById(id);
    }
    @PostMapping("/addAppointement")
    @ResponseBody
    public Appointement addAppointement(@RequestBody Appointement appointement) {
        return appointementService.AddAppointement(appointement);

    }
    @DeleteMapping("/deleteAppointement/{id}")
    @ResponseBody
    public void deleteAppointement(@PathVariable Integer id) {
        appointementService.DeleteAppointement(id);

    }
    @PutMapping("/updateAppointement/{id}")
    @ResponseBody
    public Appointement updateAppointement(@PathVariable Integer id ,@RequestBody Appointement a) {
       return appointementService.updateAppointement(id,a);
    }
}
