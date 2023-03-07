package tn.esprit.biol.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import tn.esprit.biol.entity.JwtRequest;
import tn.esprit.biol.entity.JwtResponse;
import tn.esprit.biol.entity.User;

import tn.esprit.biol.service.IUserService;
import tn.esprit.biol.service.JwtService;
import tn.esprit.biol.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;


    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }

    @PostMapping({"/signup"})
    public ResponseEntity<?> registerNewUser(@RequestBody User user)
    {

       return userService.signUp(user,false);

    }

    @PostMapping({"/addNew"})
    @PreAuthorize("hasRole('HeadSupervisor') ")
    public ResponseEntity<?> addNewUser(@RequestBody User user)
    {
        return userService.signUp(user,true);
    }

    @GetMapping({"/getAll"})
    public ResponseEntity<?> getUsers()
    {
        return userService.getUsers();

    }



    @PutMapping({"/update/{id}"})
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable("id") String id)
    {
        return userService.updateUser(user,id);
    }

    @DeleteMapping({"/delete/{id}"})
    @PreAuthorize("hasRole('HeadSupervisor') ")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id)
    {
        return userService.deleteUser(id);
    }
    @PutMapping({"/allow/{id}"})
    public ResponseEntity<?> updateUser(@PathVariable("id") String id)
    {
        return userService.allowUser(id);
    }

}
