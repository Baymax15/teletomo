package org.example.teletomo.modules.crm.service;

import org.example.teletomo.modules.intercoms.service.IntercomsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrmServiceImpl implements CrmService {
	@Autowired
	IntercomsProducer producer;

	@Override
	public String send(String destination, String message) {
		try {
			return producer.send(destination, message);
		} catch (Exception e) {
			e.printStackTrace();
			return String.format("Could not send message. Error: %s", e.getMessage());
		}
	}
}
