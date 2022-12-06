package org.example.teletomo.modules.crm.controller;

import java.util.Arrays;
import java.util.List;

import org.example.teletomo.modules.intercoms.service.IntercomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrmController {

	@Autowired
	IntercomsService intercomsService;

	@Value("${message}")
	String message;

	List<String> destinations = Arrays.asList("billing", "email", "inventory", "sms", "voicemail");

	@GetMapping
	public String launch() {
		StringBuilder builder = new StringBuilder("Teletomo Application");
		destinations.forEach(dest -> builder.append(String.format("<br><a href=\"/%s\" target=\"_blank\">%s</a>", dest, dest)));
		return builder.toString();
	}

	@GetMapping("/{destination}")
	public String send(@PathVariable(name = "destination") String destination, @RequestParam(required = false) String msg) {
		String packet = msg;

		if (!destinations.contains(destination))
			return String.format("Destination not available: %s", destination);
		if (msg == null || msg.isEmpty())
			packet = message;

		intercomsService.sendMessage(destination, packet);
		return String.format("Sent message %s to destination: %s", packet, destination);
	}
}
