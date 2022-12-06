package org.example.teletomo.modules.sms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
	private static final Logger log = LoggerFactory.getLogger(SmsService.class);

	public void sendSMS(String message) {
		log.info("Sending message: {}", message);
	}

}
