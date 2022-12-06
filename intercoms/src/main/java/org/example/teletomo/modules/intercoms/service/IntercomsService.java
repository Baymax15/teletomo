package org.example.teletomo.modules.intercoms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class IntercomsService {
	private static final Logger log = LoggerFactory.getLogger(IntercomsService.class);

	@Autowired
	IntercomsMessageHandler handler;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@JmsListener(destination = "${spring.application.name}")
	public void listener(String message) {
		log.info("Incomming message: {}", message);
		handler.handle(message);
	}
	
	public void sendMessage(String destination, String message) {
		jmsTemplate.convertAndSend(destination, message);
	}
}
