package ru.bellintegrator.weatherbroker.client.servicemanager;

import ru.bellintegrator.weatherbroker.client.exception.NotFoundException;

public interface ServiceManager {
    /**
     * Метод получает погоду для заданного города посредством класса (@see RestTemplateManager).
     * Полученный результат о погоде будет отправлен в JMS-топик (see MessageService)
     *
     * @param cityName
     * @throws NotFoundException
     */
    void pushCity(String cityName) throws NotFoundException;
}
