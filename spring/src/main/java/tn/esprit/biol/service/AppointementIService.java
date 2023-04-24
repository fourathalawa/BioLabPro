package tn.esprit.biol.service;

import tn.esprit.biol.entity.Appointement;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface AppointementIService {

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
    //Stat
    public Integer FindAppointementsNumberPerDateandHour(LocalDateTime dateApp);
    public Integer NumberOfAppointementsByMonth(Integer month);
    public Integer NumberOfAppointementsByDayAndMonth(Integer day,Integer month);
    public Integer NumberOfAppointementsByYear(Integer year);
    public Integer NumberOfAppointementsByPatient(String Patientid);
    public Integer NumberOfAppointementsByPatientByYearMonth(String Patientid,Integer month , Integer year);
    public Integer NumberOfAppointementsNotYetReachedThisMonth();
    public Integer NumberOfAppointementsValidatedThisMonth();
    public Integer NumberOfAppointementsNotValidatedThisMonth();
    public Integer NumlberofAppointmentsValidatedByPatient(String idPatient);
    public Integer NumberOfAppointementsNotValidatedByPatient(String idPatient);
    public List<String> PatientBanned();
    //Stat
    public void envoyersms();
}
