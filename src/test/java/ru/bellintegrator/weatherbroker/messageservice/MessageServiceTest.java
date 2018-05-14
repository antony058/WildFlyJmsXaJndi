package ru.bellintegrator.weatherbroker.messageservice;

import org.junit.Before;
import org.junit.Test;
import ru.bellintegrator.weatherbroker.client.messageservice.JmsMessageProducer;
import ru.bellintegrator.weatherbroker.client.messageservice.service.MessageService;
import ru.bellintegrator.weatherbroker.client.messageservice.service.impl.MessageServiceImpl;
import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MessageServiceTest {
    private JmsMessageProducer jmsMessageProducer;
    private MessageService messageService;

    @Before
    public void setUp() {
        jmsMessageProducer = mock(JmsMessageProducer.class);
        messageService = new MessageServiceImpl(jmsMessageProducer);
    }

    @Test
    public void sendMessageToTopicSuccessTest() {
        WeatherView weatherView = new WeatherView("Penza", "12-12-12", 23, "Sunny");

        messageService.sendMessageToTopic(weatherView);

        verify(jmsMessageProducer).sendMessage(weatherView);
    }
}
