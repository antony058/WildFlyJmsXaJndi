package ru.bellintegrator.weatherbroker.server.weather.view;

import java.util.LinkedHashMap;

public class Query {

    /*
    * Результат запроса к сервису Yahoo
     */
    private Object query;

    public void setQuery(Object object) {
        this.query = object;
    }

    public Object getQuery() {
        return query;
    }

    /*
    * Проверка на то, что результат получен
     */
    public boolean isQueryResultExist() {
        return ((LinkedHashMap<String, Object>) query).get("results") != null;
    }
}
