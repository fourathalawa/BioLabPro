package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.Invoice;

import java.util.Optional;
import java.util.Set;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice,Integer> {

    public Invoice findInvoiceByIdInvoice(Integer id);

    public Invoice getInvoiceByIdInvoice(Integer id);
    @Query("SELECT i FROM Invoice i WHERE i.idInvoice = :id")
    Optional<Invoice> findById(@Param("id") Integer id);
}
