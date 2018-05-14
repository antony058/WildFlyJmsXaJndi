package ru.bellintegrator.weatherbroker.requestservice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import ru.bellintegrator.weatherbroker.client.exception.NotFoundException;
import ru.bellintegrator.weatherbroker.client.requestservice.RestTemplateManager;
import ru.bellintegrator.weatherbroker.server.weather.view.Query;
import ru.bellintegrator.weatherbroker.server.weather.view.WeatherView;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestTemplateManagerTest {

    private RestTemplateManager restTemplateManager;
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = mock(RestTemplate.class);
        restTemplateManager = new RestTemplateManager(restTemplate);
    }

    @Test
    public void sendRequestSuccessTest() throws NoSuchFieldException, IllegalAccessException, NotFoundException {
        Class c = restTemplateManager.getClass();

        Field firstPartUrlField = c.getDeclaredField("urlFirstPart");
        firstPartUrlField.setAccessible(true);

        Field lastPartUrlField = c.getDeclaredField("urlLastPart");
        lastPartUrlField.setAccessible(true);

        String firstPartUrl = (String) firstPartUrlField.get(restTemplateManager);
        String lastPartUrl = (String) lastPartUrlField.get(restTemplateManager);

        Query query = createQuery();
        when(restTemplate.getForObject(firstPartUrl + "Penza" + lastPartUrl, Query.class)).thenReturn(query);
        WeatherView weatherView = restTemplateManager.sendRequest("Penza");

        Assert.assertEquals(weatherView.getCity(), "Penza");
    }

    @SuppressWarnings("unchecked")
    private Query createQuery() {
        Query query = new Query();
        query.setQuery(new LinkedHashMap<String, Object>());

        LinkedHashMap<String, Object> subMap = (LinkedHashMap<String, Object>) query.getQuery();
        subMap.put("results", new LinkedHashMap<String, Object>());
        subMap = (LinkedHashMap<String, Object>) subMap.get("results");

        subMap.put("channel", new LinkedHashMap<String, Object>());
        subMap = (LinkedHashMap<String, Object>) subMap.get("channel");

        subMap.put("item", new LinkedHashMap<String, Object>());
        subMap = (LinkedHashMap<String, Object>) subMap.get("item");

        subMap.put("condition", new LinkedHashMap<String, Object>());
        subMap = (LinkedHashMap<String, Object>) subMap.get("condition");

        subMap.put("date", "12-12-19");
        subMap.put("temp", "23");
        subMap.put("text", "Sunny");

        return query;
    }
}
