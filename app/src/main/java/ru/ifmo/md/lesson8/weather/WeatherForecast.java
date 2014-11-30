package ru.ifmo.md.lesson8.weather;

import java.util.ArrayList;

/**
 * Created by daria on 29.11.14.
 */
public class WeatherForecast {
    private ArrayList<Weather> weekWeather = new ArrayList<>();
    String name;

    public ArrayList<Weather> getWeekWeather() {
        return weekWeather;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDay(Weather w) {
        weekWeather.add(w);
    }
}
