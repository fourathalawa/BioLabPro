package tn.esprit.biol.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.biol.dao.RoleDao;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.Role;
import tn.esprit.biol.entity.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserService implements  IUserService {



    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @PostConstruct
    public void initRoleAndUser() {
        Role headSupervisor = new Role();
        headSupervisor.setRoleName("HeadSupervisor");
        headSupervisor.setRoleDescription("Admin");
        roleDao.save(headSupervisor);


        Role doctor = new Role();
        doctor.setRoleName("Doctor");
        doctor.setRoleDescription("Doctor");
        roleDao.save(doctor);

        Role biologist = new Role();
        biologist.setRoleName("Biologist");
        biologist.setRoleDescription("Biologist");
        roleDao.save(biologist);


        Role patient = new Role();
        patient.setRoleName("Patient");
        patient.setRoleDescription("Patient");
        roleDao.save(patient);


        Role trainer = new Role();
        trainer.setRoleName("Trainer");
        trainer.setRoleDescription("Trainer");
        roleDao.save(trainer);


        User adminUser = new User();
        adminUser.setId("07998550");
        adminUser.setUserPassword(getEncodedPassword("chahine123"));
        adminUser.setUserFirstName("chahine");
        adminUser.setUserLastName("kouki");
        adminUser.setAdress("Rue Khairedine tunis");
        adminUser.setEmail("chahinekouki1998@gmail.com");
        adminUser.setTel("+21653000000");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(headSupervisor);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User patientUser = new User();
        patientUser.setId("07998551");
        patientUser.setUserPassword(getEncodedPassword("assyl123"));
        patientUser.setUserFirstName("assyl");
        patientUser.setUserLastName("kriaa");
        patientUser.setAdress("Rue Khairedine tunis");
        patientUser.setEmail("assyl@gmail.com");
        patientUser.setTel("+21653100000");
        Set<Role> patientRoles = new HashSet<>();
        patientRoles.add(patient);
        patientUser.setRole(patientRoles);

        userDao.save(patientUser);

        User biologistUser = new User();
        biologistUser.setId("07998552");
        biologistUser.setUserPassword(getEncodedPassword("chiheb123"));
        biologistUser.setUserFirstName("chieb");
        biologistUser.setUserLastName("aroua");
        biologistUser.setAdress("Rue Khairedine tunis");
        biologistUser.setEmail("chiheb@gmail.com");
        biologistUser.setTel("+21653120000");
        Set<Role> biologistRoles = new HashSet<>();
        biologistRoles.add(biologist);
        patientUser.setRole(biologistRoles);
        userDao.save(biologistUser);


        User trainerUser = new User();
        trainerUser.setId("09999556");
        trainerUser.setUserPassword(getEncodedPassword("fourat123"));
        trainerUser.setUserFirstName("fourat");
        trainerUser.setUserLastName("halaoua");
        trainerUser.setAdress("Rue Khairedine tunis");
        trainerUser.setEmail("fourat@gmail.com");
        trainerUser.setTel("+21653120001");
        Set<Role> trainerRoles = new HashSet<>();
        trainerRoles.add(trainer);
        trainerUser.setRole(trainerRoles);
        userDao.save(trainerUser);

    }
    public ResponseEntity<?> getUsers( ) {

       List<User> users = (List<User>) userDao.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);

    }

    public ResponseEntity<?> signUp(User user , boolean isStaff ){
        if (user.getId() ==null || user.getId().length() !=8  ) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("veuillez corriger le champs CIN");
        }
        else if (  user.getUserFirstName() ==null || user.getUserFirstName().length() <2   ) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("veuillez saisir un prenom correcte");
        }  else if (  user.getUserLastName() ==null || user.getUserLastName().length() <2  ) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("veuillez saisir un nom correcte");
        }
        else if ( user.getAdress() == null  ) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("veuillez saisir une  adresse correcte");
        }
        else if ( user.getTel() == null || !Pattern.matches("\\b\\d{8}\\b", user.getTel()) ) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("veuillez saisir un num tel correcte");
        }
        else if ( user.getEmail() == null || !Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", user.getEmail()) ) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("veuillez saisir un email  correcte");
        }

        else {
            if(!isStaff) {
                Role role = roleDao.findById("Patient").get();

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            }
            user.setUserPassword(getEncodedPassword(user.getUserPassword()));
            userDao.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
    }

    public ResponseEntity<?> updateUser(User user ,String id ) {
        User oldUser=userDao.findById(id).get();
        if (user.getTel() != null) {
            if (!Pattern.matches("\\b\\d{8}\\b", user.getTel())) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("veuillez saisir un num tel correcte");
            }
            oldUser.setTel(user.getTel());
        }
         if (user.getEmail() != null) {
            if (!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", user.getEmail())) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("veuillez saisir un email  correcte");
            }
            oldUser.setEmail(user.getEmail());
        }
         if (user.getAdress() != null) {
            oldUser.setAdress(user.getAdress());
        }

            userDao.save(oldUser);
            return ResponseEntity.status(HttpStatus.OK).body(oldUser);

    }
    @Transactional
    public ResponseEntity<?> deleteUser(String id ) {

        Optional<User> user = userDao.findById(id);
         if(!user.isPresent()) {
             return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("there is no such user with this id: "+id);
         }

        userDao.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");

    }

    public void banUser(String id) throws Exception {
        Optional<User> user = userDao.findById(id);
        if(user.isPresent() ){
            user.get().setIsBanned(Boolean.TRUE);
            userDao.save(user.get());
            log.debug("User banned", user);
        } else throw new Exception(String.valueOf(HttpStatus.NOT_ACCEPTABLE));
    }

    public ResponseEntity<?> allowUser(String id) {

        Optional<User> user = userDao.findById(id);
        if(user.isPresent() ){
            user.get().setIsBanned(Boolean.FALSE);
            userDao.save(user.get());
            loginAttemptService.evictUserFromLoginAttemptCache(user.get().getId());
            log.debug("User allowed", user);

            return  ResponseEntity.status(HttpStatus.OK).body("user authorized ");

        } else  return ResponseEntity.status(HttpStatus.OK).body("Invalid Id ");
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public String getIdCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }

}
