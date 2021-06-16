package com.jack.huncho.conference;

import com.jack.huncho.conference.model.Session;
import com.jack.huncho.conference.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConferenceApplication {

	private static final Logger log = LoggerFactory.getLogger(ConferenceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConferenceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(SessionRepository repository) {
		return (args) -> {
			repository.save(new Session("Huncho Jack"));
			repository.save(new Session("Jack Huncho"));
			repository.save(new Session("YSL"));

			for (Session session : repository.findAll()) {
				log.info("Session held by: " + session.getName());
			}
		};
	}

}
