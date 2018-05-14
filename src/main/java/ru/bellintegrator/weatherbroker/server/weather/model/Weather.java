package ru.bellintegrator.weatherbroker.server.weather.model;

import ru.bellintegrator.weatherbroker.server.city.model.City;

public class Weather {

    private Long id;

    /*
    * Температура
     */
    private Integer temperature;

    /*
    * Описание
     */
    private String description;

    private City city;

    public Weather() {

    }

    public Weather(Integer temperature, String description) {
        this.temperature = temperature;
        this.description = description;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "id: " + id + "\ntemperature: " + temperature + "\ndescription: " + description;
    }
}
