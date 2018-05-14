package ru.bellintegrator.weatherbroker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.weatherbroker.client.exception.NotFoundException;
import ru.bellintegrator.weatherbroker.client.servicemanager.ServiceManager;

@RestController
public class JmsController {
    private final ServiceManager serviceManager;

    @Autowired
    public JmsController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @RequestMapping(value = "/send/{text}", method = RequestMethod.GET)
    public String sendMessage(@PathVariable String text) throws NotFoundException {
        serviceManager.pushCity(text);

        return "message was send";
    }
}
