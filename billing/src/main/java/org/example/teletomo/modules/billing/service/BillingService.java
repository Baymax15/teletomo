package org.example.teletomo.modules.billing.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.example.teletomo.modules.billing.dto.BillList;
import org.example.teletomo.modules.billing.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BillingService {
    List<Bill> bills = new ArrayList<>(loadBills());

    @Autowired
    ObjectMapper mapper;

    public BillList createBill(Bill request) {
        bills.add(request);
        return new BillList(bills);
    }

    public JsonNode deleteBill(UUID id) {
        boolean status = bills.removeIf(bill -> bill.getReference().equals(id));
        return mapper.createObjectNode().put("data", status);
    }

    public Bill getBill(UUID id) {
        return bills.stream()
                .filter(bill -> bill.getReference().equals(id))
                .findFirst()
                .orElse(new Bill());
    }

    public BillList getBills(JsonNode params) {
        if (params.has("paymentMode")) {
            String mode = params.get("paymentMode").asText().toLowerCase();
            return new BillList(bills.stream()
                    .filter(bill -> bill.getPaymentMode().toLowerCase().contains(mode))
                    .collect(Collectors.toList()));
        }
        return new BillList(bills);
    }

    private static List<Bill> loadBills() {
        return Arrays.asList(
                new Bill(110, "Google Pay", UUID.randomUUID()),
                new Bill(150, "Referral", UUID.randomUUID()),
                new Bill(220, "Debit Card", UUID.randomUUID()),
                new Bill(55, "Google Pay", UUID.randomUUID()),
                new Bill(250, "Coupon", UUID.randomUUID()),
                new Bill(110, "Google Pay", UUID.randomUUID()));
    }
}
