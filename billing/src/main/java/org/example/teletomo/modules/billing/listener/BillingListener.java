package org.example.teletomo.modules.billing.listener;

import java.util.UUID;

import org.example.teletomo.modules.billing.dto.BillList;
import org.example.teletomo.modules.billing.model.Bill;
import org.example.teletomo.modules.billing.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class BillingListener {
    private static final String destination = "billing";

    @Autowired
    BillingService service;

    @Autowired
    ObjectMapper mapper;

    @JmsListener(destination = destination, selector = "action = 'createBill'")
    public BillList createBill(JsonNode request) {
        log.debug("createBill:: request: {}", request);
        if (request.hasNonNull("price") && request.hasNonNull("paymentMode")) {
            Bill bill = new Bill(
                    request.get("price").asInt(),
                    request.get("paymentMode").asText(),
                    UUID.randomUUID());
            return service.createBill(bill);
        }
        return service.getBills(mapper.createObjectNode());
    }

    @JmsListener(destination = destination, selector = "action = 'deleteBill'")
    public JsonNode deleteBill(ObjectNode id) {
        log.debug("deleteBill:: id: {}", id);

        UUID ref = UUID.fromString(id.get("id").asText());
        return service.deleteBill(ref);
    }

    @JmsListener(destination = destination, selector = "action = 'getBill'")
    public Bill getBill(ObjectNode id) {
        log.debug("getBill:: id: {}", id);

        UUID ref = UUID.fromString(id.get("id").asText());
        return service.getBill(ref);
    }

    @JmsListener(destination = destination, selector = "action = 'getBills'")
    public BillList getBills(JsonNode params) {
        log.debug("getBills:: params: {}", params);
        return service.getBills(params);
    }
}
