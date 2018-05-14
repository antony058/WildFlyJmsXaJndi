package ru.bellintegrator.weatherbroker.messageservice.integrationaltests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.weatherbroker.BrokerproducerApplication;
import ru.bellintegrator.weatherbroker.client.messageservice.JmsMessageProducer;
import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BrokerproducerApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class JmsMessageListenerTest {

    @Autowired
    private JmsMessageProducer jmsMessageProducer;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    @Transactional
    public void jmsSendingTest() {
        WeatherView weatherView = new WeatherView("Пенза", "12-12-12", 11, "Прохладно");
        jmsMessageProducer.sendMessage(weatherView);
        System.out.println(weatherView);
    }

    @Test
    @Transactional
    @Commit
    public void jmsSendingTest2() {
        MessageListener messageListener = message -> {
            TextMessage textMessage = (TextMessage) message;
            System.out.println(textMessage.toString());
            try {
                Assert.assertNotEquals("Hello", textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        };

        jmsTemplate.send(session -> {
            session.setMessageListener(messageListener);
            return session.createTextMessage("Hello");
        });
    }
}
