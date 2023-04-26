package tn.esprit.biol.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.biol.dao.UserDao;
import tn.esprit.biol.entity.JwtRequest;
import tn.esprit.biol.entity.User;
import tn.esprit.biol.service.LoginAttemptService;
import tn.esprit.biol.util.JwtUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {



    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private UserDao userDao;



    @Override
    @Transactional
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        ObjectMapper objectMapper = new ObjectMapper();
        JwtRequest myRequest = objectMapper.readValue(request.getReader(), JwtRequest.class);
        System.out.println(myRequest);
        loginAttemptService.addUserToLoginAttemptCache(myRequest.getId());



        if (loginAttemptService.hasExceededMaxAttempts(myRequest.getId())) {
            // Bloquer l'utilisateur
          User user = userDao.findById(myRequest.getId()).get();
                  user.setIsBanned(Boolean.TRUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Votre compte a ete bloqué pour des raisons de securite");
            response.getWriter().flush();

        }



    }


}
