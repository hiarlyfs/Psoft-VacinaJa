package com.vacinasja.service.email_service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.google.common.io.CharStreams;
import com.vacinasja.error.template_error.TemplateNaoEncontrado;
import com.vacinasja.model.EmailTemplate;
import com.vacinasja.repository.EmailTemplateRepository;

@Service
public class EmailServiceImpl implements EmailService {
	
    @Autowired
    private JavaMailSender emailSender;
    
    @Autowired
    private EmailTemplateRepository emailTemplateRepository;
    
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
     * Envia um e-mail utilizando um template.
     * @throws TemplateNaoEncontrado 
     */
    @Override
    public void enviarEmailComTemplate(String emailDestinatario, String assuntoEmail, String nameTemplate, Map<String, String> values) throws MessagingException, IOException, TemplateNaoEncontrado {
    	Optional<EmailTemplate> optionalTemplate = emailTemplateRepository.findByName(nameTemplate);
    	
    	if (!optionalTemplate.isPresent()) throw new TemplateNaoEncontrado(nameTemplate);
    	EmailTemplate template = optionalTemplate.get();
    	String text_template = getTemplate(template.getPath());
    	
    	String replaced = replaceTemplate(text_template, values);
    	
    	enviarEmail(emailDestinatario, assuntoEmail, replaced);
    }
    
    /**
     * Dada uma template convertido em string, com dados a serem substituidos no formato #/nomeValue/#, e um Map, com a chave sendo
     * o nomeValue, e o value o que será substituido.
     * Ex: "Oi, {#nomeCidadao}" Map contendo ("nomeCidadao", "Lucas"), a função retorna a string "Oi, Lucas".
     * @param templateString
     * @param values
     * @return
     */
    private String replaceTemplate(String templateString, Map<String, String> values) {
    	String templateStringResult = templateString;
    	for (String key:values.keySet()) {
    		String keyToTemplate = String.format("#/%s/#", key);
    		templateStringResult = templateStringResult.replaceAll(keyToTemplate, values.get(key));
    	}
    	return templateStringResult;
    }
    
    /**
     * Recupera o arquivo de template default e retorna como String.
     * @return
     * @throws IOException
     */
    private String getTemplate(String path) throws IOException {
    	Resource resource = new ClassPathResource(path);
    	InputStream inputStream = new ByteArrayInputStream(resource.getInputStream().readAllBytes());
    	
        String text = null;
        try (Reader reader = new InputStreamReader(inputStream)) {
            text = CharStreams.toString(reader);
        }
        return text;
    }
}