package ru.bellintegrator.weatherbroker.client.messageservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.weatherbroker.client.messageservice.JmsMessageProducer;
import ru.bellintegrator.weatherbroker.client.messageservice.service.MessageService;
import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

@Service
public class MessageServiceImpl implements MessageService {
    private Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final JmsMessageProducer jmsMessageProducer;

    /**
    * @param jmsMessageProducer - bean отправки сообщений в Topic
     */
    @Autowired
    public MessageServiceImpl(JmsMessageProducer jmsMessageProducer) {
        this.jmsMessageProducer = jmsMessageProducer;
    }

    /**
    * {@inheritDoc}
     */
    @Override
    public void sendMessageToTopic(WeatherView view) {
        jmsMessageProducer.sendMessage(view);
        log.info("Данные о погоде отправлены в topic");
    }
}
