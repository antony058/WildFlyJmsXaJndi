package ru.bellintegrator.weatherbroker.server.city.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bellintegrator.weatherbroker.server.city.model.City;

import java.io.Serializable;

public class CityView implements Serializable {

    /*
    * Название города
     */
    private String city;

    public CityView() {

    }

    public CityView(String city) {
        this.city = city;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static CityView mapToCityView(City city) {
        return new CityView(city.getCityName());
    }
}
