package tn.esprit.biol.service;


// Java Program to Illustrate Creation Of
// Service Interface

// Importing required classes


// Interface
public interface EmailIService {

    public void sendSimpleMessage(
            String to, String subject, String text);
    public void sendLeaveRequestEmail(String toAddress, String subject, String body);

    }