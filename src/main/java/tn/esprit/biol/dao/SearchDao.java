package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.Search;

@Repository
public interface SearchDao extends JpaRepository<Search,Integer> {
}
