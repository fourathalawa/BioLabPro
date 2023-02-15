package tn.esprit.biol.service;

import tn.esprit.biol.entity.Appointement;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface AppointementIService {
    public Integer FindAppointementsNumberPerDateandHour(LocalDateTime dateApp);
    public Appointement AddAppointement(Appointement a);
    public void DeleteAppointement(Integer id);
    public Appointement updateAppointement(Integer id , Appointement ap);
    //mazel majech wakt l'rendez vous
    public Appointement StatusAppointment_not_yet_reached(Integer id);
    //rendez vous ja waktou w patient taada
    public Appointement StatusAppointment_validated(Integer id);
    //rendez vous ja waktou w patient majech
    public Appointement StatusAppointment_not_validated(Integer id);
    public List<Appointement> retreiveAllAppointment();
    public Appointement getAppById(Integer id );
}
