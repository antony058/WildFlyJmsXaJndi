package ru.bellintegrator.weatherbroker.server.weather.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bellintegrator.weatherbroker.server.weather.model.Weather;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class WeatherView implements Serializable {
    private static final long serialVersionUID = -295422703255886286L;
    /*
    * дата
     */
    private String date;

    /*
    * Температура
     */
    private Integer temp;

    /*
    * Описание
     */
    private String text;

    /*
    * Название города
     */
    private String city;

    public WeatherView() {

    }

    public WeatherView(String city, String date, Integer temp, String text) {
        this.city = city;
        this.date = date;
        this.temp = temp;
        this.text = text;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer getTemp() {
        return temp;
    }

    public void setTemp(final Integer temp) {
        this.temp = temp;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "date: " + date + "\ntemp: " + temp + "\ntext: " + text;
    }

    /*
    * Метод принимает результат запроса, полученный от сервера, и название города.
    * Затем полученный результат конвертируется в класс представления {@link WeatherView}
    *
    * @param query - результат запроса, полученный от сервера
    * @param city - название города
    * @return возвращает объект представления погоды конкретного города
     */
    @SuppressWarnings("unchecked")
    public static WeatherView mapToWeather(final Query query, final String city) {
        LinkedHashMap<String, Object> weatherData =
                (LinkedHashMap<String, Object>) query.getQuery();

        weatherData = (LinkedHashMap<String, Object>) weatherData
                .get("results");
        weatherData = (LinkedHashMap<String, Object>) weatherData
                .get("channel");
        weatherData = (LinkedHashMap<String, Object>) weatherData
                .get("item");
        weatherData = (LinkedHashMap<String, Object>) weatherData
                .get("condition");

        return new WeatherView(
                city,
                (String) weatherData.get("date"),
                Integer.valueOf((String) weatherData.get("temp")),
                (String) weatherData.get("text"));
    }

    /*
    * Метод принмает Entity объект погоды и название города.
    * Затем маппит это к представлению {@link WeatherView} и возвращает полученное представление
    *
    * @param weather - Entity-объект погоды из базы данных
    * @param cityName - название города
    * @return возвращает объект представления погоды конкретного города
     */
    public static WeatherView mapToWeatherView(final Weather weather, final String cityName) {
        return new WeatherView(
                cityName,
                null,
                weather.getTemperature(),
                weather.getDescription()
        );
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + city.hashCode();
        result = result * prime + date.hashCode();
        result = result * prime + text.hashCode();
        result = result * prime + temp;

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        WeatherView view = (WeatherView) obj;

        if (city.equals(view.getCity()))
            return false;
        if (date.equals(view.getDate()))
            return false;
        if (text.equals(view.getText()))
            return false;
        if (!temp.equals(view.getTemp()))
            return false;

        return true;
    }
}
