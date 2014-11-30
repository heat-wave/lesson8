package ru.ifmo.md.lesson8.weather;

/**
 * Created by daria on 29.11.14.
 */
public class Weather {
    private int dayTemp;
    private int minTemp;
    private int maxTemp;
    private int nightTemp;
    private int eveTem;
    private int mornTemp;
    private int pressure;
    private int humidity;
    private int id;
    private int windSpeed;
    private int windDeg;
    private byte[] icon;

    public int getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(int dayTemp) {
        this.dayTemp = dayTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(int nightTemp) {
        this.nightTemp = nightTemp;
    }

    public int getEveTem() {
        return eveTem;
    }

    public void setEveTem(int eveTem) {
        this.eveTem = eveTem;
    }

    public int getMornTemp() {
        return mornTemp;
    }

    public void setMornTemp(int mornTemp) {
        this.mornTemp = mornTemp;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }
}
