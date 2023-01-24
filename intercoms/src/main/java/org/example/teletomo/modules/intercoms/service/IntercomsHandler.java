package org.example.teletomo.modules.intercoms.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class IntercomsHandler {
	private static final Logger log = LoggerFactory.getLogger(IntercomsHandler.class);

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	JsonMessageConverter converter;

	public void respond(Message request, Object obj) {
		log.debug("respond :: request: {} obj: {}", request, obj);

		Destination dest;
		String requestId;
		try {
			dest = request.getJMSReplyTo();
			requestId = request.getJMSMessageID();
		} catch (JMSException e) {
			log.error("could not get reply destination or messageId", e);
			return;
		}

		jmsTemplate.convertAndSend(dest, obj, response -> {
			response.setJMSCorrelationID(requestId);
			return response;
		});
	}

	public JsonNode request(String destination, String action, Object obj) {
		log.debug("request :: destination: {} action: {} obj: {}", destination, action, obj);

		Message response = jmsTemplate.sendAndReceive(destination, session -> {
			Message message = converter.toMessage(obj, session);
			message.setStringProperty("action", action);
			return message;
		});

		try {
			return converter.fromMessage(response);
		} catch (MessageConversionException e) {
			log.error("response conversion failed", e);
			return null;
		} catch (JMSException e) {
			log.error("jms exception", e);
			return null;
		}
	}
}
