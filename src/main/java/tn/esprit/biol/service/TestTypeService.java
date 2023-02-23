package tn.esprit.biol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.biol.dao.TestTypeDao;
import tn.esprit.biol.entity.TestType;

import java.util.List;
@Service

public class TestTypeService {
    @Autowired
    TestTypeDao testTypeDao;

    public TestType addTest(TestType testType){
        return testTypeDao.save(testType);
    }
}
