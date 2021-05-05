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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.google.common.io.CharStreams;

@Service
public class EmailServiceImpl implements EmailService {
	
    @Autowired
    private JavaMailSender emailSender;
    
    private final String defaultEmailTemplate = "templates/default_email.html";
    
    @Override
    public void enviarEmail(String emailDestinatario, String assuntoEmail, String mensagemEmail) throws MessagingException {
    	MimeMessage message = emailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
    	
    	helper.setTo(emailDestinatario);
    	helper.setSubject(assuntoEmail);
    	helper.setText(mensagemEmail, true);
        emailSender.send(message);
    }
    
    /**
     * Envia um e-mail utilizando o template default.
     */
    @Override
    public void enviarEmailDefault(String emailDestinatario, String nomeDestinatario, String assuntoEmail, String mensagemEmail) throws MessagingException, IOException {
    	String text_template = getDefaultTemplate();
    	String replaced = text_template
    			.replaceAll("#nome", nomeDestinatario)
    			.replaceAll("#mensagem", mensagemEmail);
    	enviarEmail(emailDestinatario, assuntoEmail, replaced);
    }
    
    /**
     * Recupera o arquivo de template default e retorna como String.
     * @return
     * @throws IOException
     */
    private String getDefaultTemplate() throws IOException {
    	Resource resource = new ClassPathResource(defaultEmailTemplate);
    	InputStream inputStream = new ByteArrayInputStream(resource.getInputStream().readAllBytes());
    	
        String text = null;
        try (Reader reader = new InputStreamReader(inputStream)) {
            text = CharStreams.toString(reader);
        }
        return text;
    }
}