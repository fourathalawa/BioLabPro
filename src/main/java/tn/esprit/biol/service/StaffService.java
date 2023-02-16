package tn.esprit.biol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import tn.esprit.biol.dao.StaffRepository;
import tn.esprit.biol.entity.Staff_Details;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffService implements IStaffService {

@Autowired
    StaffRepository staffRepository;


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


    public Staff_Details updateStaff(Staff_Details s) {
        staffRepository.save(s);
        return s;
    }


    public Staff_Details getStaffById(Integer id) {

        return staffRepository.findById(id).get();
    }

    public List<Staff_Details> getAllStaffs() {
        List<Staff_Details> staffs = new ArrayList<>();
        staffRepository.findAll().forEach(staffs::add);
        return staffs;
    }

}
