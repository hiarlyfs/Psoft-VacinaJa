package com.vacinasja.service.email_service;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import com.vacinasja.error.template_error.TemplateNaoEncontrado;

public interface EmailService {
	public void enviarEmail(String emailDestinatario, String assuntoEmail, String mensagemEmail) throws MessagingException;
	/**
	 * Envia um e-mail utilizando o template default.
	 * @throws TemplateNaoEncontrado 
	 */
	void enviarEmailComTemplate(String emailDestinatario, String assuntoEmail, String nameTemplate,
			Map<String, String> values) throws MessagingException, IOException, TemplateNaoEncontrado;
}
