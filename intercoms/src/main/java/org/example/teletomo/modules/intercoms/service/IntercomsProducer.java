package org.example.teletomo.modules.intercoms.service;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class IntercomsProducer {
	private static final Logger log = LoggerFactory.getLogger(IntercomsProducer.class);

	@Autowired
	JmsTemplate jmsTemplate;

	public void send(String destination, Object obj) throws JMSException {
		log.debug("send :: destination: {} obj: {}", destination, obj);
		jmsTemplate.convertAndSend(destination, obj);
	}

	public void send(String destination, Object obj, String action) throws JMSException {
		log.debug("send :: destination: {} obj: {}", destination, obj);
		jmsTemplate.convertAndSend(destination, obj, message -> {
			message.setStringProperty("action", action);
			return message;
		});
	}
}
