package tn.esprit.biol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.biol.dao.AppointementDao;
import tn.esprit.biol.entity.Appointement;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class AppointementService implements AppointementIService{
    @Autowired
    AppointementDao appointementDao;

    @Override
    public Integer FindAppointementsNumberPerDateandHour(LocalDateTime dateApp){
        Integer i = 0;
        List<Appointement> liste = appointementDao.findAll();
        for(Appointement a : liste){
            if(a.getDateAppointement().equals(dateApp)){
                i = i +1 ;
            }
        }
        return i;
    }

    @Override
    public Appointement AddAppointement(Appointement a){
        // kan fi date w fil heure heki fama akal min 2 rendez vous yet3ada
        if(FindAppointementsNumberPerDateandHour(a.getDateAppointement().plusHours(1))<2)
        { a.setDateAppointement(a.getDateAppointement().plusHours(1));
        return appointementDao.save(a);}
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
        Appointement a  = appointementDao.getAppointementByIdAppointement(id);
        a.setDateAppointement(ap.getDateAppointement());
        a.setTypeAppointement(ap.getTypeAppointement());
       return appointementDao.save(a);}
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

}
