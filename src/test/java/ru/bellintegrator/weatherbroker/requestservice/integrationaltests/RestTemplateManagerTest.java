package ru.bellintegrator.weatherbroker.requestservice.integrationaltests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.bellintegrator.weatherbroker.BrokerproducerApplication;
import ru.bellintegrator.weatherbroker.client.exception.NotFoundException;
import ru.bellintegrator.weatherbroker.client.requestservice.RestTemplateManager;
import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BrokerproducerApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext
public class RestTemplateManagerTest {

    @Autowired
    private RestTemplateManager restTemplateManager;

    @Test
    public void sendQueryToRemoteWeatherServiceSuccessTest() throws NotFoundException {
        WeatherView weatherView = restTemplateManager.sendRequest("Пенза");

        Assert.assertNotNull(weatherView.getCity());
        Assert.assertNotNull(weatherView.getTemp());
        Assert.assertNotNull(weatherView.getText());
        Assert.assertNotNull(weatherView.getDate());
    }
}
