package ru.bellintegrator.weatherbroker.server.city.model;

import ru.bellintegrator.weatherbroker.server.weather.model.Weather;

public class City {

    private Long id;

    /*
    * Название города
     */
    private String cityName;

    private Weather weather;

    public City() {

    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
