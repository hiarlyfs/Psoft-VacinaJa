package com.vacinasja.service.email_service;

import java.io.IOException;

import javax.mail.MessagingException;

public interface EmailService {
	public void enviarEmailDefault(String emailDestinatario, String nomeDestinatario, String assuntoEmail, String mensagemEmail) throws MessagingException, IOException;
}
