package org.example.teletomo.modules.crm.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.example.teletomo.modules.crm.service.CrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(originPatterns = {"http://localhost:*"})
public class CrmController {
	private static final Logger log = LoggerFactory.getLogger(CrmController.class);

	@Autowired
	CrmService service;

	@Value("${message}")
	String message;

	List<String> destinations = Arrays.asList("billing", "email", "inventory", "sms", "voicemail");

	@GetMapping
	public List<String> getRoutes(HttpServletRequest request) {
		return destinations;
	}

	@PostMapping("/{destination}")
	public Map<String, String> send(@PathVariable(name = "destination") String destination, @RequestBody Map<String, String> msg) {
		Map<String, String> response = new HashMap<String, String>();
		if (!destinations.contains(destination)) {
			response.put("Error", String.format("Destination not available: %s", destination));
			return response;
		}
		String res = service.send(destination, (msg == null || msg.isEmpty()) ? message : msg.get("message"));
		response.put("response", res);
		return response;
	}
}
