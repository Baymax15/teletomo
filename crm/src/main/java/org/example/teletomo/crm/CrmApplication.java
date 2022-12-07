package org.example.teletomo.crm;

import org.example.teletomo.modules.intercoms.service.IntercomsMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"org.example.teletomo.modules.crm","org.example.teletomo.modules.intercoms"})
public class CrmApplication {
	private static final Logger log = LoggerFactory.getLogger(CrmApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}

	@Bean
	public IntercomsMessageHandler handler() {
		return new IntercomsMessageHandler() {

			@Override
			public String handle(String msg) {
				log.info("Handled message: {}", msg);
				return null;
			}
			
		};
	}
}
