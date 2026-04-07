package com.areebaahmad.portfolio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.areebaahmad.portfolio.entity.Contact;
import com.areebaahmad.portfolio.service.ContactService;

@RestController
@RequestMapping("api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

	private final ContactService contactService;

	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	@PostMapping()
	public String submitContact(@RequestBody Contact contact) {
		return contactService.processContact(contact);
	}

	@GetMapping
	public List<Contact> getAllContacts() {
		return contactService.getAllContacts();
	}
}
