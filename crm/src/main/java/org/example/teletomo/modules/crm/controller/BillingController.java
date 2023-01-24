package org.example.teletomo.modules.crm.controller;

import java.util.Map;
import java.util.UUID;

import org.example.teletomo.modules.crm.service.BillingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/billing")
@CrossOrigin(originPatterns = { "http://localhost:*" })
public class BillingController {

    @Autowired
    BillingHandler service;

    @GetMapping
    public JsonNode getBills(@RequestParam Map<String, String> params) {
        return service.getBills(params);
    }

    @PostMapping
    public JsonNode createBill(@RequestBody JsonNode request) {
        return service.createBill(request);
    }

    @GetMapping("/{id}")
    public JsonNode getBill(@PathVariable UUID id) {
        return service.getBill(id);
    }

    @DeleteMapping("/{id}")
    public JsonNode deleteBill(@PathVariable UUID id) {
        return service.deleteBill(id);
    }
}
