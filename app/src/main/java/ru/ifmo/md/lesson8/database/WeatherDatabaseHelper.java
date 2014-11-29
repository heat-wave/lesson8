package ru.ifmo.md.lesson8.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    }

    // Method is called during an upgrade of the database,
    // e.g. if you increase the database version
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        WeatherTable.onUpgrade(database, oldVersion, newVersion);
    }

}
