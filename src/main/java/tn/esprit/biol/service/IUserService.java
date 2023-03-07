package tn.esprit.biol.service;

import org.springframework.http.ResponseEntity;
import tn.esprit.biol.entity.Equipment;
import tn.esprit.biol.entity.User;

import java.util.List;

public interface IUserService {
    public void initRoleAndUser();
    public ResponseEntity<?> getUsers( );

    public ResponseEntity<?> signUp(User user , boolean isStaff );

    public ResponseEntity<?> updateUser(User user ,String id );

    public ResponseEntity<?> deleteUser(String id ) ;

    public String getEncodedPassword(String password);

    public ResponseEntity<?> allowUser(String id);
}
