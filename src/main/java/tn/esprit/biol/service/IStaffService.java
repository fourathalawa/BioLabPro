package tn.esprit.biol.service;

import tn.esprit.biol.entity.Staff_Details;

import java.util.List;

public interface IStaffService {
    public Staff_Details AddStaff(Staff_Details s);
    public Staff_Details updateStaff(Staff_Details s);
    public String deleteStaff(Integer id) ;
    public List<Staff_Details> getAllStaffs();



    }
