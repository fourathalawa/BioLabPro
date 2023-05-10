package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.TestType;

@Repository
public interface TestTypeDao extends JpaRepository<TestType,Integer> {

    public TestType findFirstByTestName(String name);
}
