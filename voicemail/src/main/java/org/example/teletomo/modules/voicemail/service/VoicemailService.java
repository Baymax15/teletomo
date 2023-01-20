package org.example.teletomo.modules.voicemail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VoicemailService {
	private static final Logger log = LoggerFactory.getLogger(VoicemailService.class);

	public void sendVoicemail(String message) {
		log.info("sendVoicemail :: message: {}", message);
	}

}
