package ru.bellintegrator.weatherbroker.client.messageservice.service;

import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

public interface MessageService {

    /**
    * Метод сервиса, передающий полученную view в класс отправки JMS-сообщений (@see JmsMessageProducer)
    *
    * @param view - представление, которое будет отправлено в Topic
     */
    void sendMessageToTopic(WeatherView view);
}
