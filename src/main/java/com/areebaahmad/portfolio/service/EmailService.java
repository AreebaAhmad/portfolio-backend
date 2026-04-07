package com.areebaahmad.portfolio.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.areebaahmad.portfolio.entity.Contact;

@Service
public class EmailService {

	private final JavaMailSender mailSender;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendEmail(Contact contact) {
		System.out.println("DEBUG: " + contact.getName() + " | " + contact.getEmail() + " | " + contact.getMessage());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("arb.areeba@gmail.com");
		message.setReplyTo(contact.getEmail());
		message.setTo("arb_007@live.com");
		message.setSubject("New Portfolio Message From " + contact.getName());
		message.setText("Name: " + contact.getName() + "\n" + "Email: " + contact.getEmail() + "\nMessage:\n"
				+ contact.getMessage());
		mailSender.send(message);
	}

}
