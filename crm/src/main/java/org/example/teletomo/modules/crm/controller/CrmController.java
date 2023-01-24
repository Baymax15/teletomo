package org.example.teletomo.modules.crm.controller;

import org.example.teletomo.modules.crm.service.CrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
@CrossOrigin(originPatterns = { "http://localhost:*" })
public class CrmController {
	private static final Logger log = LoggerFactory.getLogger(CrmController.class);

	@Autowired
	CrmService crmService;

	@GetMapping("/")
	public JsonNode homepageDetails() {
		log.debug("homepageDetails :: noArgs");
		return crmService.getHomepageDetails();
	}
}
