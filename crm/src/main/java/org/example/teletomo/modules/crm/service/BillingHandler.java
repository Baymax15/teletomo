package org.example.teletomo.modules.crm.service;

import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;

public interface BillingHandler {
    JsonNode getBills(Map<String, String> params);

    JsonNode createBill(JsonNode request);

    JsonNode getBill(UUID id);

    JsonNode deleteBill(UUID id);
}
