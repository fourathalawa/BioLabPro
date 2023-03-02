package tn.esprit.biol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.Rating;
import tn.esprit.biol.entity.Search;

import java.util.List;

@Repository
public interface SearchDao extends JpaRepository<Search,Integer> {

    @Query(value = " SELECT * FROM search   where user_id like :iduser",nativeQuery = true)
    List<Search> getSearchByIDUSER(String iduser);

    @Query(value = " SELECT * FROM search   where user_id like :iduser group by expression",nativeQuery = true)
    List<Search> getSearchByIDUSERG(String iduser);
}
