package org.example.teletomo.modules.intercoms.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonMessageConverter implements MessageConverter {
    private static final Logger log = LoggerFactory.getLogger(JsonMessageConverter.class);

    @Autowired
    ObjectMapper mapper;

    @Override
    public Message toMessage(Object obj, Session session) throws JMSException, MessageConversionException {
        log.debug("toMessage :: obj: {}", obj);
        String payload;
        try {
            payload = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new MessageConversionException("Object could not be parsed", e);
        }
        return session.createTextMessage(payload);
    }

    @Override
    public JsonNode fromMessage(Message message) throws JMSException, MessageConversionException {
        log.debug("fromMessage :: message: {}", message);
        String payload = ((TextMessage) message).getText();

        try {
            return mapper.readTree(payload);
        } catch (JsonProcessingException e) {
            throw new MessageConversionException("Message could not be processed", e);
        }
    }

    public <T> T fromMessage(Message message, Class<T> valueType) throws JMSException, MessageConversionException {
        log.debug("fromMessage :: message: {}, valueType: {}", message, valueType.getName());
        String payload = ((TextMessage) message).getText();

        try {
            return mapper.readValue(payload, valueType);
        } catch (DatabindException e) {
            throw new MessageConversionException("Message could not be converted to target class", e);
        } catch (JsonProcessingException e) {
            throw new MessageConversionException("Message could not be processed", e);
        }
    }
}
