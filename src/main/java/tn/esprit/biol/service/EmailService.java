package tn.esprit.biol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


    @Service
    public class EmailService
    {
            @Autowired
            private JavaMailSender mailSender;

            public void sendLeaveRequestEmail(String toAddress, String subject, String body) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(toAddress);
                message.setSubject(subject);
                message.setText(body);
                mailSender.send(message);
            }
    }


