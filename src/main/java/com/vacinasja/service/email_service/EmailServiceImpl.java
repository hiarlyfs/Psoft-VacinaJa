package com.vacinasja.service.email_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {
	
    @Autowired
    private JavaMailSender emailSender;
    
    private final String fimEmail = "\n\nEsse e-mail é enviado automaticamente pelo sistema VacinaJá\n"
    		+ "para informar novidades sobre a sua vacinação. - 2021";
    
    public void enviarEmail(
      String destinatario, String assunto, String corpo) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("vacinasja.noreply@gmail.com");
        message.setTo(destinatario); 
        message.setSubject(assunto); 
        message.setText(corpo+fimEmail);
        emailSender.send(message);
    }
}