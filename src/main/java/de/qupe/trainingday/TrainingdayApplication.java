package de.qupe.trainingday;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TrainingdayApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(TrainingdayApplication.class);
	public static void main(String[] args) {
		LOGGER.info("Applikation wird gestartet");
		SpringApplication.run(TrainingdayApplication.class, args);
	}

}