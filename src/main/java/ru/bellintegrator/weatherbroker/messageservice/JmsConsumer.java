package ru.bellintegrator.weatherbroker.messageservice;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class JmsConsumer {
    public void getMessage(Message message) throws JMSException {
        if (message instanceof TextMessage) {
            String text = ((TextMessage) message).getText();

            System.out.println(text);
            if (text == null)
                throw new RuntimeException("runtime exception");
        }
    }
}
