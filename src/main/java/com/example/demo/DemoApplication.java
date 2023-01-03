package com.example.demo;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	private static String portNumber = null;

	private static final String DEFAULT_PORT = "8080";

	public static void main(String[] args) {
		for (String s : args) {
			logger.debug("Args : " + s);
			if (s.contains("--port")) {
				portNumber = s.substring(s.length() - 4, s.length());
				logger.debug("port number : " + portNumber);
			}
		}

		if (portNumber == null) {
			portNumber = System.getenv("APP_PORT");
			logger.debug("Sys ENV portNumber : " + portNumber);
		}

		if (portNumber == null) {
			portNumber = DEFAULT_PORT;
		}

		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		app.run(args);

	}

}
