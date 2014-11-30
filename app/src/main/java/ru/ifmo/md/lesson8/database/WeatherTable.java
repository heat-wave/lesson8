package ru.ifmo.md.lesson8.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by default on 29.11.14.
 */
public class WeatherTable {

    //Database table
    public static final String TABLE_FORECAST = "forecast";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CURRENT = "current";
    public static final String COLUMN_LONGFORECAST = "long_forecast";
    public static final String COLUMN_IS_SELECTED = "is_selected";
    public static final String COLUMN_NAME = "name";
    /*public static final String COLUMN_CITY = "city";
    public static final String COLUMN_SUNRISE = "sunrise";
    public static final String COLUMN_SUNSET = "sunset";
    public static final String COLUMN_TEMPERATURE = "temperature";
    public static final String COLUMN_PRESSURE = "pressure";
    public static final String COLUMN_HUMIDITY = "humidity";
    public static final String COLUMN_MINTEMPERATURE = "mintemperature";
    public static final String COLUMN_MAXTEMPERATURE = "maxtemperature";
    public static final String COLUMN_WINDSPEED = "windspeed";
    public static final String COLUMN_WINDDEGREE = "winddegree";
    public static final String COLUMN_WEATHERID = "weatherID";
    public static final String COLUMN_ICON = "icon";*/

    //Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_FORECAST
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + "string not null, "
            + COLUMN_IS_SELECTED + "integer not null, "
            + COLUMN_CURRENT + "blob not null, "
            + COLUMN_LONGFORECAST + "blob not null"
            /*+ COLUMN_CITY + " text not null, "
            + COLUMN_SUNRISE + "integer not null, "
            + COLUMN_SUNSET + "integer not null, "
            + COLUMN_TEMPERATURE + "integer not null, "
            + COLUMN_PRESSURE + "integer not null, "
            + COLUMN_HUMIDITY + "integer not null, "
            + COLUMN_MINTEMPERATURE+ "integer not null, "
            + COLUMN_MAXTEMPERATURE + "integer not null, "
            + COLUMN_WINDSPEED+ "integer not null, "
            + COLUMN_WINDDEGREE + "integer not null, "
            + COLUMN_WEATHERID + "integer not null, "
            + COLUMN_ICON + "blob not null"*/
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(WeatherTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_FORECAST);
        onCreate(database);
    }

}
