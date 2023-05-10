package tn.esprit.biol.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.Staff_Details;

@Repository
public interface StaffRepository extends JpaRepository<Staff_Details, Integer>
{

}
