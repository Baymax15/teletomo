package org.example.teletomo.modules.crm.controller;

import java.util.Map;
import java.util.UUID;

import org.example.teletomo.modules.crm.service.EmailHandler;
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
@RequestMapping("/email")
@CrossOrigin(originPatterns = { "http://localhost:*" })
public class EmailController {

    @Autowired
    EmailHandler service;

    @GetMapping
    public JsonNode getEmails(@RequestParam Map<String, String> params) {
        return service.getEmails(params);
    }

    @PostMapping
    public JsonNode createEmail(@RequestBody JsonNode request) {
        return service.createEmail(request);
    }

    @GetMapping("/{id}")
    public JsonNode getEmail(@PathVariable UUID id) {
        return service.getEmail(id);
    }

    @DeleteMapping("/{id}")
    public JsonNode deleteEmail(@PathVariable UUID id) {
        return service.deleteEmail(id);
    }
}
