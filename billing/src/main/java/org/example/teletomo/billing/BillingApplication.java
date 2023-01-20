package org.example.teletomo.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"org.example.teletomo.modules.billing",
		"org.example.teletomo.modules.intercoms"
})
public class BillingApplication {
	public static void main(String[] args) {
		SpringApplication.run(BillingApplication.class, args);
	}
}
