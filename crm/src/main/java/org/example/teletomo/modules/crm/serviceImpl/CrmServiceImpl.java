package org.example.teletomo.modules.crm.serviceImpl;

import org.example.teletomo.modules.crm.service.CrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class CrmServiceImpl implements CrmService {
	private static final Logger log = LoggerFactory.getLogger(CrmServiceImpl.class);

	@Value("${message}")
	String message;

	@Override
	public JsonNode getHomepageDetails() {
		log.debug("getHomepageData", message);
		ObjectNode response = JsonNodeFactory.instance.objectNode();
		response.put("headline", message);
		return response;
	}
}
