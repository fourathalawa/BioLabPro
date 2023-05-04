package tn.esprit.biol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.JwtRequest;
import tn.esprit.biol.entity.JwtResponse;
import tn.esprit.biol.entity.User;
import tn.esprit.biol.util.JwtUtil;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginAttemptService loginAttemptService;

    public ResponseEntity<?> createJwtToken(JwtRequest jwtRequest) throws Exception {
        String id = jwtRequest.getId();
        String userPassword = jwtRequest.getUserPassword();
        User user = userDao.findById(id).get();
        if (user.getIsBanned()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("you're blocked due security reasons");
        } else {
            authenticate(id, userPassword);
            UserDetails userDetails = loadUserByUsername(id);
            String newGeneratedToken = jwtUtil.generateToken(userDetails);

            return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(user, newGeneratedToken));
          //  return new JwtResponse(user, newGeneratedToken);


        }

    }
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<User> user = userDao.findById(id);

        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    user.get().getId(),
                    user.get().getUserPassword(),
                    getAuthority(user.get())
            );
        }
        else {
            throw new UsernameNotFoundException("User not found with id: " + id);
        }
    }

    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

   private  void authenticate(String id, String userPassword) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(id,  userPassword));

        } catch (DisabledException e) {
           throw new Exception("Disabled Exception");

        } catch (BadCredentialsException e) {
            System.out.println("errooooooooor");
            loginAttemptService.addUserToLoginAttemptCache(id);


            if (loginAttemptService.hasExceededMaxAttempts(id)) {
                // Bloquer l'utilisateur
                User user = userDao.findById(id).get();
                user.setIsBanned(Boolean.TRUE);
                userDao.save(user);

            }
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
