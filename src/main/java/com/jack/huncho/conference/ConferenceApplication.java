package com.jack.huncho.conference;

import com.jack.huncho.conference.model.Session;
import com.jack.huncho.conference.model.Speaker;
import com.jack.huncho.conference.repository.SessionRepository;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ConferenceApplication {

    private static final Logger log = LoggerFactory.getLogger(ConferenceApplication.class);

    Speaker cactusJack = new Speaker("Cactus Jack");
    Speaker huncoJack = new Speaker("Huncho Jack");
    List<Speaker> speakers = Arrays.asList(cactusJack, huncoJack);
    LocalTime start = LocalTime.of(6,0);
    LocalTime end = LocalTime.of(7,0);
    long length = Duration.between(start, end).toMinutes();

    public static void main(String[] args) {

        SpringApplication.run(ConferenceApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(SessionRepository repository) {

        return (args) -> {
            repository.save(new Session("Huncho Jack"));
            repository.save(new Session("Jack Huncho"));
            repository.save(new Session("YSL"));
            repository.save(new Session(
                    "Money Talk",
                    start,
                    end,
                    Arrays.asList(),
                    length,
                    "Learn how to speak money fluently"
            ));

            for (Session session : repository.findAll()) {
                log.info("Session held by: " + session.getName());
            }
        };
    }

}
