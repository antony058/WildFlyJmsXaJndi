package ru.bellintegrator.weatherbroker.client.servicemanager;

import ru.bellintegrator.weatherbroker.client.exception.NotFoundException;

public interface ServiceManager {
    void pushCity(String cityName) throws NotFoundException;
}
