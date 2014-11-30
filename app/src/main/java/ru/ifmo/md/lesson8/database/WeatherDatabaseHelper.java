package ru.ifmo.md.lesson8.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.Time;

import ru.ifmo.md.lesson8.weather.CurrentWeather;
import ru.ifmo.md.lesson8.weather.Weather;
import ru.ifmo.md.lesson8.weather.WeatherForecast;

/**
 * Created by default on 29.11.14.
 */
public class WeatherDatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "weathertable.db";
    private static final int DATABASE_VERSION = 1;

    public WeatherDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        WeatherTable.onCreate(database);
        ContentValues cv = new ContentValues();
        cv.put(WeatherTable.COLUMN_NAME, "St.Petersburg");
        cv.put(WeatherTable.COLUMN_IS_SELECTED, 1);
        byte[] a = {0,0,0,0};
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(new CurrentWeather(new Time(1123009900909L), new Time(1243243253L),0,0,0,0,0,0,0,0,"dfsf",a));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cv.put(WeatherTable.COLUMN_CURRENT, byteArrayOutputStream.toByteArray() );
        database.insert(WeatherTable.TABLE_FORECAST, null, cv);
    }

    // Method is called during an upgrade of the database,
    // e.g. if you increase the database version
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        WeatherTable.onUpgrade(database, oldVersion, newVersion);
    }


    public static class WeatherCursor extends CursorWrapper {
        private Cursor cursor;
        public WeatherCursor(Cursor cur) {
            super(cur);
            cursor = cur;
        }

        public CurrentWeather getWeather() {
            return getWeather(cursor);

        }

        public static CurrentWeather getWeather(Cursor cur) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(cur.getBlob(1));
            try {
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                return (CurrentWeather) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        public WeatherForecast getForecast() {
            return getForecast(cursor);

        }

        public static WeatherForecast getForecast(Cursor cur) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(cur.getBlob(2));
            try {
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                return (WeatherForecast) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
