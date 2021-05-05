package com.vacinasja.service.email_service;

public interface EmailService {
	public void sendEmail(String to, String subject, String text);
}
