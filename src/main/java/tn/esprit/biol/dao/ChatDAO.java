package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.biol.entity.Chat;


import java.util.List;

public interface ChatDAO extends JpaRepository<Chat,Long> {

    Chat findByName(String name);

    @Query("SELECT DISTINCT c FROM Chat c WHERE c.name like %:user% ")
    List<Chat> findByPartecipant(String user);
}
