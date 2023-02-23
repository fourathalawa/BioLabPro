package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.Invoice;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice,Integer> {

    public Invoice findInvoiceByIdInvoice(Integer id);
}
