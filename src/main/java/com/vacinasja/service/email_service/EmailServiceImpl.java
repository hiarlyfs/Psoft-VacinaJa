package com.vacinasja.service.email_service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.google.common.io.CharStreams;

@Service
public class EmailServiceImpl implements EmailService {
	
    @Autowired
    private JavaMailSender emailSender;
    
    @Autowired
    ResourceLoader resourceLoader;
    
    public void enviarEmailDefault(
      String emailDestinatario, String nomeDestinatario, String assuntoEmail, String mensagemEmail) throws MessagingException, IOException {
    	MimeMessage message = emailSender.createMimeMessage();
    	
    	MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
    	helper.setTo(emailDestinatario);
    	helper.setSubject(assuntoEmail);
    	String text_template = getDefaultTemplate();
    	String replaced = text_template.replaceAll("#nome", nomeDestinatario)
    			.replaceAll("#mensagem", mensagemEmail);
    	helper.setText(replaced, true);
        emailSender.send(message);
    }
    
    private String getDefaultTemplate() throws IOException {
    	Resource resource = new ClassPathResource("templates/default_email.html");
    	InputStream inputStream = new ByteArrayInputStream(resource.getInputStream().readAllBytes());
    	
        String text = null;
        try (Reader reader = new InputStreamReader(inputStream)) {
            text = CharStreams.toString(reader);
        }
        return text;
    }
}