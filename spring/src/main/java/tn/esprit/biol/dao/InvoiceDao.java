package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.Invoice;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice,Integer> {

    public Invoice findInvoiceByIdInvoice(Integer id);

    public List<Invoice> findAllByIdPatient(String idPatient);
    public Invoice getInvoiceByIdInvoice(Integer id);
    @Query("SELECT i FROM Invoice i WHERE i.idInvoice = :id")
    Optional<Invoice> findById(@Param("id") Integer id);
}
