package org.example.teletomo.sms;

import org.example.teletomo.modules.intercoms.service.IntercomsMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"org.example.teletomo.modules.sms", "org.example.teletomo.modules.intercoms"})
public class SmsApplication {
	private static final Logger log = LoggerFactory.getLogger(SmsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SmsApplication.class, args);
	}

	@Bean
	public IntercomsMessageHandler handler() {
		return new IntercomsMessageHandler() {

			@Override
			public void handle(String msg) {
				log.info("Handled message: {}", msg);
			}
			
		};
	}
}
