package tn.esprit.biol.service;

import org.springframework.http.ResponseEntity;
import tn.esprit.biol.entity.Staff_Details;

import java.util.List;

public interface IStaffService {
    public Staff_Details AddStaff(Staff_Details s);
    public ResponseEntity<Object> updateStaff(String id, Staff_Details s);
    public String deleteStaff(Integer id) ;
    public List<Staff_Details> getAllStaffs();



    }
