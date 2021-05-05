package com.vacinasja.service.email_service;

public interface EmailService {
	public void enviarEmail(String to, String subject, String text);
}
