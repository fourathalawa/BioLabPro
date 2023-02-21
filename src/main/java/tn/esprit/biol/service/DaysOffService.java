package tn.esprit.biol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.biol.dao.DaysOffDao;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.DaysOff;
import tn.esprit.biol.entity.User;

import java.util.Date;

@Service
public class DaysOffService implements  IDaysOffService {
@Autowired
EmailService emailService;

@Autowired
      UserDao userDao;
@Autowired
    DaysOffDao daysOffDao;



    public DaysOff sendLeaveRequest(String to, String id, Date startDate, Date endDate, String justification) {
        // Envoi de l'email de demande de congé
        emailService.sendLeaveRequestEmail(to,"Demande Congé",justification);

        // Sauvegarde de la demande de congé
        User user =userDao.findById(id).get();
        DaysOff daysOff=new DaysOff();
        daysOff.setRequest(false);
        daysOff.setStartDate(startDate);
        daysOff.setEndDate(endDate);
        daysOff.setJustification(justification);
        daysOff.setUser(user);
        return daysOffDao.save(daysOff);





    }

}
