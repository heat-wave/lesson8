package ru.ifmo.md.lesson8.dummy;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.ifmo.md.lesson8.weather.CurrentWeather;
import ru.ifmo.md.lesson8.weather.WeatherForecast;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
        byte[] a = {0,0,0,0};
        addItem(new DummyItem("1", new CurrentWeather(new Time(1123009900909L), new Time(1243243253L),0,0,0,0,0,0,0,0,"Moscow",a),
                new WeatherForecast()));
        addItem(new DummyItem("2", new CurrentWeather(new Time(1123009900909L), new Time(1243243253L),0,0,0,0,0,0,0,0,"St.Petersburg",a),
                new WeatherForecast()));
        addItem(new DummyItem("3", new CurrentWeather(new Time(1123009900909L), new Time(1243243253L),0,0,0,0,0,0,0,0,"Beijing",a),
                new WeatherForecast()));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public CurrentWeather currentWeather;
        public WeatherForecast forecast;


        public DummyItem(String id, CurrentWeather currentWeather, WeatherForecast weatherForecast) {
            this.id = id;
            this.currentWeather = currentWeather;
            this.forecast = weatherForecast;
        }

        @Override
        public String toString() {
            return this.currentWeather.getName();
        }
    }
}
