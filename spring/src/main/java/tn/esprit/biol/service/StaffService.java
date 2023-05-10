package tn.esprit.biol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.biol.dao.StaffRepository;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.Staff_Details;
import tn.esprit.biol.entity.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class StaffService implements IStaffService {

@Autowired
    StaffRepository staffRepository;
    @Autowired
    private UserDao userDao;


    @Override
    public Staff_Details AddStaff(Staff_Details s)
    {
        staffRepository.save(s);
        return s;
    }

    public String deleteStaff(Integer id) {
        staffRepository.deleteById(id);
        return " Staff  Deleted";
    }

    public ResponseEntity<Object> updateStaff(String id, Staff_Details s) {
        User user =userDao.findById(id).get();
        if (user != null ) {

             s.setUser(user);
            staffRepository.save(s);
            return ResponseEntity.ok(s);
        }
        return ResponseEntity.notFound().build();
    }


    public Staff_Details getStaffById(Integer id) {

        return staffRepository.findById(id).get();
    }

    public List<Staff_Details> getAllStaffs() {
       return staffRepository.findAll();
    }

}
