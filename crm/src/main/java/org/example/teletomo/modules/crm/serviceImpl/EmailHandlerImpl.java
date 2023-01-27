package org.example.teletomo.modules.crm.serviceImpl;

import java.util.Map;
import java.util.UUID;

import org.example.teletomo.modules.crm.service.EmailHandler;
import org.example.teletomo.modules.intercoms.service.IntercomsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class EmailHandlerImpl implements EmailHandler {
    private static final String destination = "email";

    @Autowired
    IntercomsHandler handler;

    @Autowired
    ObjectMapper mapper;

    @Override
    public JsonNode getEmails(Map<String, String> params) {
        log.debug("getEmails :: params: {}", params);

        JsonNode paramsNode = mapper.valueToTree(params);
        return handler.request(destination, "getEmails", paramsNode);
    };

    @Override
    public JsonNode createEmail(JsonNode request) {
        log.debug("createEmail :: request: {}", request);

        return handler.request(destination, "createEmail", request);
    };

    @Override
    public JsonNode getEmail(UUID id) {
        log.debug("getEmail :: id: {}", id);

        ObjectNode identifier = mapper.createObjectNode();
        identifier.put("id", id.toString());
        return handler.request(destination, "getEmail", identifier);
    };

    @Override
    public JsonNode deleteEmail(UUID id) {
        log.debug("deleteEmail :: id: {}", id);

        ObjectNode identifier = mapper.createObjectNode();
        identifier.put("id", id.toString());
        return handler.request(destination, "deleteEmail", identifier);
    }
}
