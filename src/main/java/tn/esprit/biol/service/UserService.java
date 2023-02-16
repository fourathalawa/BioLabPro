package tn.esprit.biol.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.User;

@Service
public class UserService {


    @Autowired
    private UserDao userRepository;

    public User findById(Long id) {
        return userRepository.findById(String.valueOf(id)).orElse(null);
    }
}
