package ru.bellintegrator.weatherbroker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BrokerproducerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BrokerproducerApplication.class, args);
	}
}
