package com.example.sweater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender mailSender;

    public void send(String emailTo, String subject, String message) {
        SimpleMailMessage mes = new SimpleMailMessage();
        mes.setFrom(username);
        mes.setTo(emailTo);
        mes.setSubject(subject);
        mes.setText(message);
        mailSender.send(mes);
    }
}
