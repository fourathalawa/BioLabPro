package tn.esprit.biol.service;

import tn.esprit.biol.entity.Email;

public interface IEmailService {

     // Method
     // To send a simple email
     String sendSimpleMail(Email details);

     // Method
     // To send an email with attachment
     String sendMailWithAttachment(Email details);
}
