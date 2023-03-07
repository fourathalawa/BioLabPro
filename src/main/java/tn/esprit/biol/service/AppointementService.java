package tn.esprit.biol.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.lookups.v1.PhoneNumber;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.biol.dao.AppointementDao;
import tn.esprit.biol.entity.Appointement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointementService implements AppointementIService{
    @Autowired
    AppointementDao appointementDao;
    @Autowired
    AppointementService appointementService;
    @Autowired
    SmsService smsService;

    @Override
    public Integer FindAppointementsNumberPerDateandHour(LocalDateTime dateApp){
        Integer i = 0;
        List<Appointement> liste = appointementDao.findAll();
        for(Appointement a : liste){
            if(a.getDateAppointement().withSecond(0).equals(dateApp.withHour(a.getDateAppointement().getHour()).withMinute(a.getDateAppointement().getMinute()).withSecond(0)/*.plusHours(1)*/)){
               /* System.out.println("ma date :"+a.getDateAppointement().withSecond(0));
                System.out.println("sys date :"+dateApp.withSecond(0).plusHours(1));*/
                i = i +1 ;
            }
        }
        return i;
    }

    @Override
    public Appointement AddAppointement(Appointement a){
        // kan fi date w fil heure heki fama akal min 2 rendez vous yet3ada
        if(FindAppointementsNumberPerDateandHour(a.getDateAppointement().plusHours(1))<2)
        {
            if(a.getDateAppointement().plusHours(1).toLocalDate().isAfter(LocalDate.now()))
            {a.setDateAppointement(a.getDateAppointement().plusHours(1));
        return appointementDao.save(a);}
            else
                return null;


        }
        else
            return null;
    }

    @Override
    public void DeleteAppointement(Integer id){
        Appointement ap  = appointementDao.getAppointementByIdAppointement(id);
        appointementDao.delete(ap);
    }

    @Override
    public Appointement updateAppointement(Integer id, Appointement ap) {
        // kan fi date w fil heure heki fama akal min 2 rendez vous yet3ada
        if(FindAppointementsNumberPerDateandHour(ap.getDateAppointement())<2){
            if(ap.getDateAppointement().plusHours(1).toLocalDate().isAfter(LocalDate.now())){
        Appointement a  = appointementDao.getAppointementByIdAppointement(id);
        a.setDateAppointement(ap.getDateAppointement().plusHours(1));
        a.setTypeAppointement(ap.getTypeAppointement());
       return appointementDao.save(a);}
        else return null ;}
        else
            return null;
    }

    @Override
    public Appointement StatusAppointment_not_yet_reached(Integer id) {
        Appointement a  = appointementDao.getAppointementByIdAppointement(id);
        a.setStatusAppointement(0);
        return appointementDao.save(a);
    }

    @Override
    public Appointement StatusAppointment_validated(Integer id) {
        Appointement a  = appointementDao.getAppointementByIdAppointement(id);
        a.setStatusAppointement(1);
        return appointementDao.save(a);
    }

    @Override
    public Appointement StatusAppointment_not_validated(Integer id) {
        Appointement a  = appointementDao.getAppointementByIdAppointement(id);
        a.setStatusAppointement(2);
        return appointementDao.save(a);
    }

    @Override
    public List<Appointement> retreiveAllAppointment() {
       return appointementDao.findAll();
    }

    @Override
    public Appointement getAppById(Integer id) {
        return appointementDao.getAppointementByIdAppointement(id);
    }

    @Override
    public Integer NumberOfAppointementsByMonth(Integer month) {
        Integer i=0;
        List<Appointement> liste = appointementDao.findAll();
        for(Appointement a : liste){
            if(a.getDateAppointement().getMonthValue()==month){
                i=i+1;
            }
        }
        return i;
    }

    @Override
    public Integer NumberOfAppointementsByDayAndMonth(Integer day,Integer month) {
        Integer i=0;
        List<Appointement> liste = appointementDao.findAll();
        for(Appointement a : liste){
            if(a.getDateAppointement().getDayOfMonth()==day){
                if(a.getDateAppointement().getMonthValue()==month){
                i=i+1;
                }
            }
        }
        return i;
    }

    @Override
    public Integer NumberOfAppointementsByYear(Integer year) {
        Integer i=0;
        List<Appointement> liste = appointementDao.findAll();
        for(Appointement a : liste){
            if(a.getDateAppointement().getYear()==year){
               i = i+1;
            }
        }
        return i;
    }

    @Override
    public Integer NumberOfAppointementsByPatient(String Patientid) {
        Integer i=0;
        List<Appointement> liste = appointementDao.findAll();
        for(Appointement a : liste){
            if(a.getIdPatient().equals(Patientid)==true){
                i=i+1;
            }
        }
        return i;
    }

    @Override
    public Integer NumberOfAppointementsByPatientByYearMonth(String Patientid, Integer month, Integer year) {
        Integer i=0;
        List<Appointement> liste = appointementDao.findAll();
        for(Appointement a : liste){
            if(a.getIdPatient().equals(Patientid)){
                if(a.getDateAppointement().getYear()==year){
                    if(a.getDateAppointement().getMonthValue()==month){ i=i+1;}
                }
            }
        }
        return i;
    }

    @Override
    public Integer NumberOfAppointementsNotYetReachedThisMonth() {
        Integer i=0;
        List<Appointement> liste = appointementDao.findAll();
        for(Appointement a : liste){
            if(a.getDateAppointement().getMonth()==LocalDateTime.now().getMonth()){
                if(a.getStatusAppointement()==0){
                   i=i+1;
                }

            }
        }
        return i;
    }

    @Override
    public Integer NumberOfAppointementsValidatedThisMonth() {
        Integer i=0;
        List<Appointement> liste = appointementDao.findAll();
        for(Appointement a : liste){
            if(a.getDateAppointement().getMonth()==LocalDateTime.now().getMonth()){
                if(a.getStatusAppointement()==1){
                    i=i+1;
                }

            }
        }
        return i;
    }

    @Override
    public Integer NumberOfAppointementsNotValidatedThisMonth() {
        Integer i=0;
        List<Appointement> liste = appointementDao.findAll();
        for(Appointement a : liste){
            if(a.getDateAppointement().getMonth()==LocalDateTime.now().getMonth()){
                if(a.getStatusAppointement()==2){
                    i=i+1;
                }

            }
        }
        return i;
    }

    @Override
    public Integer NumlberofAppointmentsValidatedByPatient(String idPatient) {
       Integer i =0;
        List<Appointement> list = appointementDao.findAll();
        for(Appointement a:list){
            if((a.getIdPatient().equals(idPatient)) &&(a.getStatusAppointement()==1)){
                i = i+1;
            }
        }
        return i;
    }

    @Override
    public Integer NumberOfAppointementsNotValidatedByPatient(String idPatient) {
        Integer i =0;
        List<Appointement> list = appointementDao.findAll();
        for(Appointement a:list){
            if((a.getIdPatient().equals(idPatient)) &&(a.getStatusAppointement()==2)){
                i = i+1;
            }
        }
        return i;
    }

    @Override
    public List<String> PatientBanned() {
        List<String> patientbanned = new ArrayList<>();
        List<Appointement> list = appointementDao.findAll();
        for(Appointement a:list){
            if(appointementService.NumberOfAppointementsNotValidatedByPatient(a.getIdPatient())>=3){
                // pas de redandance
               if(!patientbanned.contains(a.getIdPatient()))
               {patientbanned.add(a.getIdPatient());}
            }
        }
        return patientbanned;
    }

    @Override
    @Scheduled(fixedDelay = 40000) //chaque 40 sec
    public void envoyersms() {
        List<Appointement> liste = appointementDao.findAll();
        for(Appointement a : liste){
           /* System.out.println("Date Rendez vous : "+a.getDateAppointement().minusHours(1));
            System.out.println("Date maintenant : "+LocalDateTime.now().withSecond(0));*/
            if(a.getDateAppointement().minusHours(1).withSecond(0).withNano(0).compareTo(LocalDateTime.now().withSecond(0).withNano(0).plusMinutes(30))==0){
                //intgeration phone number a.getPatientId | getPhoneNumberById(a.getPatientId)
                smsService.sendSms("+21658413462","Your appointment will start in 30 minutes ");
                System.out.println("sms");
            }
        }

    }
}
