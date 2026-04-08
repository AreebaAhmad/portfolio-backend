package com.areebaahmad.portfolio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.areebaahmad.portfolio.entity.Contact;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;

@Service
public class EmailService {

	@Value("${RESEND_API_KEY}")
	private String resendApiKey;

	@Value("${EMAIL_TO}")
	private String toEmail;

	public void sendEmail(Contact contact) throws ResendException {
		Resend resend = new Resend(resendApiKey);

		CreateEmailOptions params = CreateEmailOptions.builder().from("onboarding@resend.dev").to(toEmail)
				.replyTo(contact.getEmail()).subject("New Portfolio Message From " + contact.getName())
				.html("<p><b>Name:</b> " + contact.getName() + "</p>" + "<p><b>Email:</b> " + contact.getEmail()
						+ "</p>" + "<p><b>Message:</b><br>" + contact.getMessage() + "</p>")
				.build();

		resend.emails().send(params);
	}
}