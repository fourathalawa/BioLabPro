package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.biol.entity.Message;

import java.util.List;

public interface MessageDAO extends JpaRepository<Message,Long> {
    @Query("SELECT m FROM Message m WHERE m.chat_id = :id ")
    List<Message> findAllByChat(Long id);
}
