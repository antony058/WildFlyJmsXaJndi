package ru.bellintegrator.weatherbroker.client.messageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

@Component
public class JmsMessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
    * Метод принимает в качестве параметра view и отправляет ее в JMS Topic
    * посредством JmsTemplate.
    *
    * @param weatherView - представление, которое будет отправлено в Topic
     */
    @Transactional(rollbackFor = Exception.class)
    public void sendMessage(final WeatherView weatherView) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(weatherView);
            }
        });
    }
}
