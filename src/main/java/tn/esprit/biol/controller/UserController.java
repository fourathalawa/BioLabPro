package tn.esprit.biol.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.biol.entity.JwtRequest;
import tn.esprit.biol.entity.JwtResponse;
import tn.esprit.biol.entity.User;

import tn.esprit.biol.service.UserService;


@RestController
public class UserController {
//    @Autowired
//    private JwtService jwtService;

    @Autowired
    private UserService userService;


//    @PostMapping({"/authenticate"})
//    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//        return jwtService.createJwtToken(jwtRequest);
//    }
/*

test fourat
*/
    @GetMapping("/getuser/{id}")
    public User getUserByid(@PathVariable("id") String id)
    {
    return     userService.getUserById(id);
    }

    @PostMapping({"/signup"})
    public ResponseEntity<?> registerNewUser(@RequestBody User user)
    {
       return userService.signUp(user,false);

    }

    @GetMapping({"/getAllUser"})
    public ResponseEntity<?> getUsers()
    {
        return userService.getUsers();

    }

    @PostMapping({"/addNewUser"})
    public ResponseEntity<?> addNewUser(@RequestBody User user)
    {
        return userService.signUp(user,true);
    }

    @PutMapping({"/update/{id}"})
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable("id") String id)
    {
        return userService.updateUser(user,id);
    }
    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<?> updateUser(@PathVariable("id") String id)
    {
        return userService.deleteUser(id);
    }

}
