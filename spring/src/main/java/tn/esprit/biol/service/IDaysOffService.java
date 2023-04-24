package tn.esprit.biol.service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.biol.entity.DaysOff;
import tn.esprit.biol.entity.Staff_Details;

import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IDaysOffService {
    public Boolean sendLeaveRequest(String to, String id, LocalDate startDate, LocalDate endDate, String justification,MultipartFile file) throws IOException;

    public boolean validateLeaveRequest(LocalDate startDate, LocalDate endDate);
    public List<DaysOff> getAllDaysOff();

}
