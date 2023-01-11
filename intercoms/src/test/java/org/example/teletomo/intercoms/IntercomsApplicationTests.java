package org.example.teletomo.intercoms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.application.name=intercoms",
		"spring.profiles.active=test"
})
class IntercomsApplicationTests {

	@Test
	void contextLoads() {
	}

	@SpringBootApplication
	static class TestConfiguration {
	}
}
