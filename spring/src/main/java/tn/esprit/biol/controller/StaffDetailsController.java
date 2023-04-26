package tn.esprit.biol.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.Staff_Details;
import tn.esprit.biol.entity.User;
import tn.esprit.biol.service.IStaffService;
import tn.esprit.biol.service.StaffService;
import tn.esprit.biol.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/Staff")
public class StaffDetailsController {
    @Autowired
    IStaffService staffService;

    @Autowired
    private UserDao userDao;


    @PutMapping("/update-staff/{id}")
    public ResponseEntity<Object> updateStaff(@PathVariable(value = "id") String userId,@RequestBody Staff_Details s) {

          return staffService.updateStaff(userId,s);
        }

    @DeleteMapping("/removeStaffDetails/{staff_det}")
    public void removeOperateur(@PathVariable("staff_det") Integer id) {
        staffService.deleteStaff(id);
    }

    @GetMapping("/all-staff-details")
    public List<Staff_Details> getStaff() {
        return  staffService.getAllStaffs();
    }

    }


