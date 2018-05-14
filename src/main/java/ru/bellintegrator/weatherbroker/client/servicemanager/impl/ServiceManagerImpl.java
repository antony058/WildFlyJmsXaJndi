package ru.bellintegrator.weatherbroker.client.servicemanager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.weatherbroker.client.exception.NotFoundException;
import ru.bellintegrator.weatherbroker.client.messageservice.service.MessageService;
import ru.bellintegrator.weatherbroker.client.requestservice.RestTemplateManager;
import ru.bellintegrator.weatherbroker.client.servicemanager.ServiceManager;
import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

@Service
public class ServiceManagerImpl implements ServiceManager {

    private final RestTemplateManager restTemplateManager;
    private final MessageService messageService;

    @Autowired
    public ServiceManagerImpl(RestTemplateManager restTemplateManager, MessageService messageService) {
        this.restTemplateManager = restTemplateManager;
        this.messageService = messageService;
    }

    @Override
    public void pushCity(String cityName) throws NotFoundException {
        WeatherView weatherView = restTemplateManager.sendRequest(cityName);
        messageService.sendMessageToTopic(weatherView);
    }
}
