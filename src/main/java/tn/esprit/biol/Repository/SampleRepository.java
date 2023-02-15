package tn.esprit.biol.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.biol.entity.Sample;

public interface SampleRepository extends JpaRepository<Sample,Integer> {

}