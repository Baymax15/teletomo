package org.example.teletomo.modules.crm.serviceImpl;

import java.util.Map;
import java.util.UUID;

import org.example.teletomo.modules.crm.service.BillingHandler;
import org.example.teletomo.modules.intercoms.service.IntercomsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class BillingHandlerImpl implements BillingHandler {
    private static final String destination = "billing";

    @Autowired
    IntercomsHandler handler;

    @Autowired
    ObjectMapper mapper;

    @Override
    public JsonNode getBills(Map<String, String> params) {
        log.debug("getBills :: params: {}", params);

        JsonNode paramsNode = mapper.valueToTree(params);
        return handler.request(destination, "getBills", paramsNode);
    };

    @Override
    public JsonNode createBill(JsonNode request) {
        log.debug("createBill :: request: {}", request);

        return handler.request(destination, "createBill", request);
    };

    @Override
    public JsonNode getBill(UUID id) {
        log.debug("getBill :: id: {}", id);

        ObjectNode identifier = mapper.createObjectNode();
        identifier.put("id", id.toString());
        return handler.request(destination, "getBill", identifier);
    };

    @Override
    public JsonNode deleteBill(UUID id) {
        log.debug("deleteBill :: id: {}", id);

        ObjectNode identifier = mapper.createObjectNode();
        identifier.put("id", id.toString());
        return handler.request(destination, "deleteBill", identifier);
    }
}
