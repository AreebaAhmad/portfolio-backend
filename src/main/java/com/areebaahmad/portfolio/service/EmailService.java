package com.areebaahmad.portfolio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.areebaahmad.portfolio.entity.Contact;

@Service
public class EmailService {

	private final JavaMailSender mailSender;

	// Get emails from application.properties
	@Value("${EMAIL_FROM}")
	private String fromEmail;

	@Value("${EMAIL_TO}")
	private String toEmail;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendEmail(Contact contact) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmail);
		message.setReplyTo(contact.getEmail());
		message.setTo(toEmail);
		message.setSubject("New Portfolio Message From " + contact.getName());
		message.setText("Name: " + contact.getName() + "\n" + "Email: " + contact.getEmail() + "\nMessage:\n"
				+ contact.getMessage());
		mailSender.send(message);
	}

}
