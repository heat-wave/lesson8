package ru.ifmo.md.lesson8.parsers;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;

import ru.ifmo.md.lesson8.queries.ImageQuery;
import ru.ifmo.md.lesson8.weather.CurrentWeather;

/**
 * Created by daria on 29.11.14.
 */
public class CurrentWeatherParser {
    public static CurrentWeather getWeather(String data) throws JSONException {
        CurrentWeather weather = new CurrentWeather();

        JSONObject jObj = new JSONObject(data);

        JSONObject sysObj = jObj.getJSONObject("sys");
        weather.setSunrise(new Time(Long.parseLong(sysObj.getString("sunrise"))));
        weather.setSunset(new Time(Long.parseLong(sysObj.getString("sunset"))));
        weather.setName(jObj.getString("name"));

        JSONObject jWeather = jObj.getJSONObject("weather");
        weather.setWeatherId(jWeather.getInt("id"));
        weather.setIcon(new ImageQuery().getImage(jWeather.getString("icon")));

        weather.setMaxTemperature((int) (jWeather.getDouble("temp_max") - 273.15));
        weather.setMinTemperature((int) (jWeather.getDouble("temp_min") - 273.15));
        weather.setTemperature((int) (jWeather.getDouble("temp") - 273.15));

        JSONObject wObj = jObj.getJSONObject("wind");
        weather.setWindSpeed((int) wObj.getDouble("speed"));
        weather.setWindDeg((int) wObj.getDouble("deg"));

        return weather;
    }
}
