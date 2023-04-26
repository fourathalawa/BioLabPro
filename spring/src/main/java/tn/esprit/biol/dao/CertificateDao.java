package tn.esprit.biol.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.Certificate;

@Repository
public interface CertificateDao extends JpaRepository<Certificate,Integer> {
}
