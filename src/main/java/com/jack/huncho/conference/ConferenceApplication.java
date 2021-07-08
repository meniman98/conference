package com.jack.huncho.conference;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4200",
        methods = {RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.POST,
                RequestMethod.DELETE})
public class ConferenceApplication {

    private static final Logger log = LoggerFactory.getLogger(ConferenceApplication.class);

    Speaker cactusJack = new Speaker(
            "Cactus Jack",
            Arrays.asList("Can read and write upside down", "Can drink boiling hot tea"),
            "Brick layer",
            "Capgemini",
            "Sometimes it's very hard to understand what Cactus Jack says"
    );
    Speaker hunchoJack = new Speaker(
            "Huncho Jack",
            Arrays.asList("Can run backwards very fast", "Drives a car without a licence"),
            "Dishwasher",
            "Hilton Hotel",
            "Huncho loves washing dishes by the day and performs questionable activities during the night");
    Speaker quavoHuncho = new Speaker(
            "Quavo Huncho",
            Arrays.asList("Makes the best pizzas", "Can stand on a chair with one leg"),
            "Pizza deliverer",
            "Pizza hut",
            "Quavo loves making pizzas but hates delivering them");
    Speaker meekMill = new Speaker(
            "Meek Mill",
            Arrays.asList("Speaks money fluently", "Excels in tax evasion"),
            "Receptionist",
            "The local dentist",
            "Meek Mill likes to eat his fries on his lap without using a plate"
    );
    Speaker thugger = new Speaker(
            "Jeffery",
            Arrays.asList("Can chop fruit and vegetables quickly", "Has the ability to invent a language"),
            "Kitchen assistant",
            "Capgemini",
            "Jeffery hasn't booked a doctor appointment since 2008"
    );
    List<Speaker> stackedMoney = Arrays.asList(cactusJack, hunchoJack);
    List<Speaker> moneyFluency = Arrays.asList(thugger, meekMill);
    List<Speaker> badSpendingHabits = Arrays.asList(cactusJack, hunchoJack, quavoHuncho);
    LocalDateTime start = LocalDateTime.of(2022, 5, 8, 9, 0);
    long length = Duration.between(start, start).toMinutes();

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
            // sessions
            repository.save(new Session(
                    "Stacked money",
                    start,
                    Arrays.asList(hunchoJack),
                    60L,
                    "Our host for today is going to show you how money can be stacked up to the ceiling"));
            repository.save(new Session(
                    "Money fluency",
                    start,
                    Arrays.asList(meekMill),
                    60L,
                    "Our host is going to teach you how to speak money fluently"
            ));
            repository.save(new Session(
                    "Count fast",
                    start,
                    Arrays.asList(cactusJack),
                    60L,
                    "Our host today will show you fancy tricks to count money fast with your hands"
            ));
            repository.save(new Session(
                    "Tax evasion",
                    start,
                    Arrays.asList(thugger),
                    60L,
                    "Our hosts are going to teach you slick tricks to evade tax discreetly"
            ));
            repository.save(new Session(
                    "Bad spending habits",
                    start,
                    Arrays.asList(quavoHuncho),
                    60L,
                    "Our host will teach you how to quit bad spending habits"
            ));


            for (Session session : repository.findAll()) {
                log.info("Session held by: " + session.getName());
            }
        };
    }

}
