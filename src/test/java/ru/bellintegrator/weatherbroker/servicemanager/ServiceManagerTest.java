package ru.bellintegrator.weatherbroker.servicemanager;

import org.junit.Before;
import org.junit.Test;
import ru.bellintegrator.weatherbroker.client.exception.NotFoundException;
import ru.bellintegrator.weatherbroker.client.messageservice.service.MessageService;
import ru.bellintegrator.weatherbroker.client.requestservice.RestTemplateManager;
import ru.bellintegrator.weatherbroker.client.servicemanager.ServiceManager;
import ru.bellintegrator.weatherbroker.client.servicemanager.impl.ServiceManagerImpl;
import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

import static org.mockito.Mockito.*;

public class ServiceManagerTest {
    private RestTemplateManager restTemplateManager;
    private MessageService messageService;
    private ServiceManager serviceManager;

    @Before
    public void setUp() {
        restTemplateManager = mock(RestTemplateManager.class);
        messageService = mock(MessageService.class);
        serviceManager = new ServiceManagerImpl(restTemplateManager, messageService);
    }

    @Test
    public void pushCitySuccessTest() throws NotFoundException {
        final String cityName = "Penza";
        WeatherView weatherView = createWeatherView(cityName);

        when(restTemplateManager.sendRequest(cityName)).thenReturn(weatherView);

        serviceManager.pushCity(cityName);

        verify(messageService).sendMessageToTopic(weatherView);
    }

    @Test(expected = NotFoundException.class)
    public void pushCityNotFoundExceptionTest() throws NotFoundException {
        final String cityName = "zxc";

        when(restTemplateManager.sendRequest(cityName)).thenThrow(new NotFoundException(""));
        serviceManager.pushCity(cityName);
    }

    private WeatherView createWeatherView(final String cityName) {
        return new WeatherView(cityName, "12-12-12", 23, "Sunny");
    }
}
