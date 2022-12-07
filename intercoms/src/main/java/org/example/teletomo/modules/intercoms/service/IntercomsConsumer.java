package org.example.teletomo.modules.intercoms.service;

import javax.jms.IllegalStateException;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class IntercomsConsumer {
	private static final Logger log = LoggerFactory.getLogger(IntercomsConsumer.class);

	@Autowired
	IntercomsMessageHandler handler;

	@Autowired
	JmsTemplate jmsTemplate;

	@JmsListener(destination = "${spring.jms.template.default-destination}")
	public void onMessage(Message message, Session session) throws JMSException {
		MessageConverter converter = jmsTemplate.getMessageConverter();
		if (converter == null)
			throw new IllegalStateException("No 'messageConverter' specified in jmsTemplate");

		String msg = converter.fromMessage(message).toString();
		log.info("Received message: '{}'. Reply if needed to '{}'", msg, message.getJMSReplyTo());

		String response = handler.handle(msg);
		if (response == null || response.isEmpty())
			return;
		jmsTemplate.convertAndSend(message.getJMSReplyTo(), response);
	}
}
