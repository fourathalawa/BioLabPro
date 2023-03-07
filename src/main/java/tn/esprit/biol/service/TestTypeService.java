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
    public Float PourcentageTestTypes(String testName){
        Float i=(float)0;
        Float x=(float)0;
        Float res=(float)0;
        List<TestType> list = testTypeDao.findAll();
        for(TestType t : list){
            i = i+1;

        }
        System.out.println(i);
        for(TestType p:list){
            if(p.getTestName().equals(testName)){
                x=x+1;
            }
        }
        System.out.println(x);
        res = x/i ;

        return (res*100);

    }
}
