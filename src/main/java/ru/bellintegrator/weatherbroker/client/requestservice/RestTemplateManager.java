package ru.bellintegrator.weatherbroker.client.requestservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.bellintegrator.weatherbroker.client.exception.NotFoundException;
import ru.bellintegrator.weatherbroker.server.weather.view.Query;
import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

@Component
public class RestTemplateManager {

    private Logger log = LoggerFactory.getLogger(RestTemplateManager.class);

    private final static String urlFirstPart = "https://query.yahooapis.com/v1/public/yql?q=select item.condition " +
            "from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"";
    private final static String urlLastPart = "\")&format=json&u=c";

    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
    * Метод отправляет запрос посредством {@link RestTemplate} к сервису Yahoo.
    * Полученный результат маппится на {@link WeatherView} и возвращается,
    * как результат работы функции
    *
    * @param cityName - название города, для которого будет выполнен запрос о погоде
    * @return возвращает view с информацией о погоде для запрашиваемого города
     */
    public WeatherView sendRequest(String cityName) throws NotFoundException {
        Query query = restTemplate.getForObject(urlFirstPart + cityName + urlLastPart, Query.class);
        if (!query.isQueryResultExist())
            throw new NotFoundException("Weather for " + cityName + " is not founded");

        log.info("Загружена погода города " + cityName);

        return WeatherView.mapToWeather(query, cityName);
    }
}
