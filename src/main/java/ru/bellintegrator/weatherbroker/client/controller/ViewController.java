package ru.bellintegrator.weatherbroker.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.bellintegrator.weatherbroker.client.exception.NotFoundException;
import ru.bellintegrator.weatherbroker.client.servicemanager.ServiceManager;

/**
 * Класс, реализующий маппинг определенных URL к .jsp
 */
@Controller
public class ViewController {
    private final ServiceManager serviceManager;

    @Autowired
    public ViewController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    /**
     * При базовом URL пользователь увидит страницу index.jsp
     *
     * @return возвращает модель и представление для DispatcherServlet
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        return new ModelAndView("index");
    }


    /**
     * Метод требует название города в качестве параметра.
     * Полученный в качестве параметра город отправится в (@link ServiceManager#pushCity(String))
     * Пользователю покажется страница result.jsp
     *
     * @param city - название города
     * @return возвращает модель и представление для DispatcherServlet
     * @throws NotFoundException Если город не найден
     */
    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public ModelAndView weatherBroker(@RequestParam(value = "city") String city) throws NotFoundException {
        serviceManager.pushCity(city);

        ModelAndView modelAndView = new ModelAndView("result");
        modelAndView.addObject("city", city);

        return modelAndView;
    }
}
