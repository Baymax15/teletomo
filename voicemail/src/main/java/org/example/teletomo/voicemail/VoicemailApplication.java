package org.example.teletomo.voicemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"org.example.teletomo.modules.voicemail",
		"org.example.teletomo.modules.intercoms"
})
public class VoicemailApplication {
	public static void main(String[] args) {
		SpringApplication.run(VoicemailApplication.class, args);
	}
}
