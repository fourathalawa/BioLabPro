package tn.esprit.biol.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biol.entity.User;


@Repository
public interface UserDao extends CrudRepository<User, String> {

}
