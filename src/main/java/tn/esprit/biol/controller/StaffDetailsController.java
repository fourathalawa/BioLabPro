package tn.esprit.biol.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.biol.entity.Staff_Details;
import tn.esprit.biol.entity.User;
import tn.esprit.biol.service.IStaffService;
import tn.esprit.biol.service.StaffService;
import tn.esprit.biol.service.UserService;

@RestController
@RequestMapping("/Staff")
public class StaffDetailsController {
    @Autowired
    IStaffService staffService;
    @Autowired
    UserService userService;



    @PutMapping("/{id}/update-staff")
    public ResponseEntity<?> updateStaff(@PathVariable(value = "id") Long userId,@RequestBody Staff_Details s) {

            User user = userService.findById(userId);

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            Staff_Details staffDet = user.getStaff_details();

            if (staffDet == null) {
                return ResponseEntity.notFound().build();
            }
        Staff_Details updatedStaffDet = staffService.updateStaff(staffDet);
            return ResponseEntity.ok(updatedStaffDet);
        }


    }


