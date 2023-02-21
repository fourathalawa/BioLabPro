package tn.esprit.biol.service;

import tn.esprit.biol.entity.DaysOff;

import java.util.Date;

public interface IDaysOffService {
    public DaysOff sendLeaveRequest(String to, String id, Date startDate, Date endDate, String justification);
}
