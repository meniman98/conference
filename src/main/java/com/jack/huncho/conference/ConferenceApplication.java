package com.jack.huncho.conference;

import com.jack.huncho.conference.model.Review;
import com.jack.huncho.conference.model.Session;
import com.jack.huncho.conference.model.Speaker;
import com.jack.huncho.conference.repository.SessionRepository;
import com.jack.huncho.conference.repository.SpeakerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ConferenceApplication {

    private static final Logger log = LoggerFactory.getLogger(ConferenceApplication.class);

    Speaker cactusJack = new Speaker("Cactus Jack");
    Speaker hunchoJack = new Speaker("Huncho Jack");
    Speaker quavoHuncho = new Speaker("Quavo Huncho");
    List<Speaker> speakers = Arrays.asList(cactusJack, hunchoJack);
    LocalTime start = LocalTime.of(6,0);
    LocalTime end = LocalTime.of(7,0);
    long length = Duration.between(start, end).toMinutes();

    public static void main(String[] args) {
        SpringApplication.run(ConferenceApplication.class, args);
    }

    // the restTemplate
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


    @Bean
    public CommandLineRunner demo(SessionRepository repository,
                                  SpeakerRepository speakerRepository,
                                  RestTemplate restTemplate) {

        return (args) -> {
            Review review = restTemplate.getForObject(
                    "http://localhost:8081/review/get/1",
                    Review.class);
            Review review2 = restTemplate.getForObject(
                    "http://localhost:8081/review/get/2",
                    Review.class);
            repository.save(new Session("Huncho Jack"));
            repository.save(new Session("Jack Huncho"));
            repository.save(new Session("YSL"));
            repository.save(new Session(
                    "Money Talk",
                    start,
                    end,
                    speakers,
                    length,
                    "Learn how to speak money fluently"
            ));
            speakerRepository.save(quavoHuncho);
            speakerRepository.save(new Speaker("Durkio",
                    Arrays.asList("Java", "Kotlin"),
                    Arrays.asList(review, review2)));


            for (Session session : repository.findAll()) {
                log.info("Session held by: " + session.getName());
            }
        };
    }

}
