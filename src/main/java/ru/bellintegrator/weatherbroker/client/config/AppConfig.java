package ru.bellintegrator.weatherbroker.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.bellintegrator.weatherbroker.client.requestservice.RestTemplateManager;

@Configuration
public class AppConfig {
    private static final String CONNECTION_FACTORY = "/jms/RemoteConnectionFactory";
    private static final String QUEUE_DESTINATION = "java:jboss/exported/jms/queue/messageBoxQueue";

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplateManager getRestTemplateManager() {
        return new RestTemplateManager(getRestTemplate());
    }
}
