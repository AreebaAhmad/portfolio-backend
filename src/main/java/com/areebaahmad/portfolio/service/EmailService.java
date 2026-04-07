package com.areebaahmad.portfolio.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.areebaahmad.portfolio.entity.Contact;

@Service
public class EmailService {

	private final JavaMailSender mailSender;

	// Get emails from application.properties
	private final String fromEmail = System.getenv("EMAIL_FROM");
	private final String toEmail = System.getenv("EMAIL_TO");

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendEmail(Contact contact) {
		System.out.println("DEBUG: " + contact.getName() + " | " + contact.getEmail() + " | " + contact.getMessage());
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
