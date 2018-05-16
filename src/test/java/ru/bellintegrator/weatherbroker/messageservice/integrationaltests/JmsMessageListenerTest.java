package ru.bellintegrator.weatherbroker.messageservice.integrationaltests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.weatherbroker.BrokerproducerApplication;
import ru.bellintegrator.weatherbroker.client.messageservice.JmsMessageProducer;
import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

import javax.jms.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BrokerproducerApplication.class})
@ActiveProfiles("test")
public class JmsMessageListenerTest {

    @Autowired
    private JmsMessageProducer jmsMessageProducer;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    @Transactional
    public void jmsSendAndReceiveTest() throws JMSException {
        WeatherView weatherView = new WeatherView("Moscow", "01-01-10", 33, "Sunny");
        jmsMessageProducer.sendMessage(weatherView);

        Message message = jmsTemplate.receive();

        Assert.assertTrue(message instanceof ObjectMessage);
        WeatherView receivedView = (WeatherView) ((ObjectMessage) message).getObject();

        Assert.assertEquals(weatherView.getDate(), receivedView.getDate());
        Assert.assertEquals(weatherView.getText(), receivedView.getText());
        Assert.assertEquals(weatherView.getTemp(), receivedView.getTemp());
        Assert.assertEquals(weatherView.getCity(), receivedView.getCity());
    }
}
