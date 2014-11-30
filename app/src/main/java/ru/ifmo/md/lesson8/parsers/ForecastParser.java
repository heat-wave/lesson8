package ru.ifmo.md.lesson8.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ru.ifmo.md.lesson8.queries.ImageQuery;
import ru.ifmo.md.lesson8.weather.Weather;
import ru.ifmo.md.lesson8.weather.WeatherForecast;

/**
 * Created by daria on 29.11.14.
 */
public class ForecastParser {

    public static WeatherForecast getWeather(String data) throws JSONException {
        WeatherForecast weather = new WeatherForecast();

        JSONObject jObj = new JSONObject(data);
        JSONObject cityObject = jObj.getJSONObject("city");


        weather.setName(cityObject.getString("name"));

        JSONArray list = jObj.getJSONArray("list");
        for (int i = 0; i < 7; i++) {
            JSONObject oneDay = list.getJSONObject(i);
            Weather thisDay = new Weather();
            JSONObject temp = oneDay.getJSONObject("temp");
            thisDay.setDayTemp((int) (temp.getDouble("day") - 273.15));
            thisDay.setMinTemp((int) (temp.getDouble("min") - 273.15));
            thisDay.setMaxTemp((int) (temp.getDouble("max") - 273.15));
            thisDay.setEveTem((int) (temp.getDouble("eve") - 273.15));
            thisDay.setNightTemp((int) (temp.getDouble("night") - 273.15));
            thisDay.setMornTemp((int) (temp.getDouble("morn") - 273.15));

            thisDay.setPressure((int) temp.getDouble("pressure"));
            thisDay.setHumidity(temp.getInt("humidity"));

            JSONObject weatherObject = oneDay.getJSONObject("weather");
            thisDay.setId(weatherObject.getInt("id"));
            thisDay.setIcon(new ImageQuery().getImage(weatherObject.getString("icon")));

            thisDay.setWindSpeed((int) oneDay.getDouble("speed"));
            thisDay.setWindDeg(oneDay.getInt("deg"));

            weather.addDay(thisDay);
        }

        return weather;
    }
}
