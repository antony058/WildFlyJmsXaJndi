package ru.bellintegrator.weatherbroker.client.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.bellintegrator.weatherbroker.client.exception.NotFoundException;
import ru.bellintegrator.weatherbroker.client.servicemanager.ServiceManager;

@Controller
public class ViewController {
    private final ServiceManager serviceManager;

    @Autowired
    public ViewController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public ModelAndView weatherBroker(@RequestParam(value = "city") String city) throws NotFoundException {
        serviceManager.pushCity(city);

        ModelAndView modelAndView = new ModelAndView("result");
        modelAndView.addObject("city", city);

        return modelAndView;
    }
}
