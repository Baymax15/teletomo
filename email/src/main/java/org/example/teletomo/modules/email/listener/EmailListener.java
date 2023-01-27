package org.example.teletomo.modules.email.listener;

import javax.jms.JMSException;
import javax.jms.Message;

import org.example.teletomo.modules.email.dto.EmailList;
import org.example.teletomo.modules.email.dto.MailId;
import org.example.teletomo.modules.email.model.Email;
import org.example.teletomo.modules.email.service.EmailService;
import org.example.teletomo.modules.intercoms.service.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class EmailListener {
    private static final String destination = "email";

    @Autowired
    EmailService service;

    @Autowired
    JsonMessageConverter converter;

    @JmsListener(destination = destination, selector = "action = 'createEmail'")
    public EmailList createEmail(Message message) {
        log.debug("createEmail:: msesage: {}", message);

        try {
            Email mail = converter.fromMessage(message, Email.class);
            if (mail.getSender() != null &&
                    mail.getRecipients() != null && mail.getRecipients().size() > 0 &&
                    mail.getMessage() != null) {
                return service.createEmail(mail);
            }
        } catch (JMSException e) {
            log.error("Message conversion failed:", e);
        }
        return service.getEmails(JsonNodeFactory.instance.objectNode());
    }

    @JmsListener(destination = destination, selector = "action = 'deleteEmail'")
    public JsonNode deleteEmail(Message message) {
        log.debug("deleteEmail:: msesage: {}", message);
        MailId id = new MailId();
        try {
            id = converter.fromMessage(message, MailId.class);
        } catch (JMSException e) {
            log.error("Message conversion failed:", e);
        }
        return service.deleteEmail(id.getId());
    }

    @JmsListener(destination = destination, selector = "action = 'getEmail'")
    public Email getEmail(Message message) {
        log.debug("getEmail:: msesage: {}", message);
        MailId id = new MailId();
        try {
            id = converter.fromMessage(message, MailId.class);
        } catch (JMSException e) {
            log.error("Message conversion failed:", e);
            return null;
        }
        return service.getEmail(id.getId());
    }

    @JmsListener(destination = destination, selector = "action = 'getEmails'")
    public EmailList getEmails(Message message) {
        log.debug("getEmails:: msesage: {}", message);
        JsonNode params = JsonNodeFactory.instance.objectNode();
        try {
            params = converter.fromMessage(message);
        } catch (JMSException e) {
            log.error("Message conversion failed:", e);
            return null;
        }
        return service.getEmails(params);
    }
}
