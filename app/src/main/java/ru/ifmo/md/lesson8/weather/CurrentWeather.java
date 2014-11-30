package ru.ifmo.md.lesson8.weather;

import java.sql.Time;

/**
 * Created by daria on 29.11.14.
 */
public class CurrentWeather {
    private Time sunrise;
    private Time sunset;
    private int temperature;
    private int pressure;
    private int humidity;
    private int minTemperature;
    private int maxTemperature;
    private int windSpeed;
    private int windDeg;
    private int weatherId;
    private String name;
    private byte[] icon;

    public CurrentWeather(Time sunrise, Time sunset, int temperature, int pressure,
                          int humidity, int minTemperature, int maxTemperature, int windSpeed,
                          int windDeg, int weatherId, String name, byte[] icon) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.weatherId = weatherId;
        this.name = name;
        this.icon = icon;
    }

    public CurrentWeather() {}

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getSunrise() {
        return sunrise;
    }

    public void setSunrise(Time sunrise) {
        this.sunrise = sunrise;
    }

    public Time getSunset() {
        return sunset;
    }

    public void setSunset(Time sunset) {
        this.sunset = sunset;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }
}