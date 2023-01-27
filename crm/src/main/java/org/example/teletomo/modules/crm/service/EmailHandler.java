package org.example.teletomo.modules.crm.service;

import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;

public interface EmailHandler {
    JsonNode getEmails(Map<String, String> params);

    JsonNode createEmail(JsonNode request);

    JsonNode getEmail(UUID id);

    JsonNode deleteEmail(UUID id);
}
