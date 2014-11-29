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
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_WEATHER = "weather";

    //Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_FORECAST
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_CITY + " text not null, "
            + COLUMN_WEATHER + " blob not null"
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
