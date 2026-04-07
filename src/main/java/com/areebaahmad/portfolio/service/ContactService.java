package com.areebaahmad.portfolio.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.areebaahmad.portfolio.entity.Contact;
import com.areebaahmad.portfolio.repository.ContactRepository;

@Service
public class ContactService {

	private final ContactRepository contactRepository;
	private final EmailService emailService;

	public ContactService(ContactRepository contactRepository, EmailService emailService) {
		this.contactRepository = contactRepository;
		this.emailService = emailService;
	}

	private boolean isSpam(String message) {
		List<String> bannedWords = Arrays.asList("viagra", "casino", "loan", "free money");

		// Check for banned words
		for (String word : bannedWords) {
			if (message.toLowerCase().contains(word)) {
				return true;
			}
		}

		// Check for suspicious links
		if (message.contains("http://") || message.contains("https://")) {
			return true;
		}

		// Check for long message or repeated words
		if (message.length() > 1000 || message.matches(".*(.)\\1{10,}.*")) {
			return true;
		}

		return false;
	}

	public String processContact(Contact contact) {
		if (isSpam(contact.getMessage())) {
			return "Message flagged as spam, not sent!";
		}

		contactRepository.save(contact);
		emailService.sendEmail(contact);
		return "Message sent successfully!";
	}

	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}
}
