package org.example.teletomo.modules.intercoms.service;

import javax.jms.IllegalStateException;
import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class IntercomsProducer {
	private static final Logger log = LoggerFactory.getLogger(IntercomsProducer.class);

	@Autowired
	JmsTemplate jmsTemplate;

	public String send(String destination, String message) throws JMSException {
		log.info("sending message: '{}' to destination: '{}'", message, destination);
		
		if (message == null)
			return null;
		
		MessageConverter messageConverter = jmsTemplate.getMessageConverter();
		if (messageConverter == null)
			throw new IllegalStateException("No 'messageConverter' specified in jmsTemplate");

		Message response = jmsTemplate.sendAndReceive(destination, (MessageCreator) session -> {
			return messageConverter.toMessage(message, session);
		});
		
		return messageConverter.fromMessage(response).toString();
	}
}
