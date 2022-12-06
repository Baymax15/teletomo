package org.example.teletomo.modules.email.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	private static final Logger log = LoggerFactory.getLogger(EmailService.class);

	public void sendEmail(String message) {
		log.info("Sending Email: {}", message);
	}

}
