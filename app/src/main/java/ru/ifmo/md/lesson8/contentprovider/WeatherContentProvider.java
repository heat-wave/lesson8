package ru.ifmo.md.lesson8.contentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.HashSet;

import ru.ifmo.md.lesson8.database.WeatherDatabaseHelper;
import ru.ifmo.md.lesson8.database.WeatherTable;
import ru.ifmo.md.lesson8.weather.Weather;

/**
 * Created by default on 29.11.14.
 */
public class WeatherContentProvider extends ContentProvider{

    //Database
    private WeatherDatabaseHelper database;

    //For URI Matcher
    public static final int FORECASTS = 10;
    public static final int FORECAST_ID = 20;

    public static final String AUTHORITY = "ru.ifmo.md.lesson8.contentprovider";

    public static final String BASE_PATH = "lesson8";
    public static final Uri CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/"
            + BASE_PATH);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/forecasts";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/forecast";

    public static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, FORECASTS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", FORECAST_ID);
    }

    @Override
    public boolean onCreate() {
        database = new WeatherDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String [] projection, String selection,
                        String [] selectionArgs, String sortOrder) {

        // Uisng SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // Check if the caller has requested a column which does not exists
        checkColumns(projection);

        // Set the table
        queryBuilder.setTables(WeatherTable.TABLE_FORECAST);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case FORECASTS:
                break;
            case FORECAST_ID:
                // Adding the ID to the original query
                queryBuilder.appendWhere(WeatherTable.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        // Make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        //int rowsDeleted = 0;
        long id = 0;
        switch (uriType) {
            case FORECASTS:
                id = sqlDB.insert(WeatherTable.TABLE_FORECAST, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case FORECASTS:
                rowsDeleted = sqlDB.delete(WeatherTable.TABLE_FORECAST, selection,
                        selectionArgs);
                break;
            case FORECAST_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(WeatherTable.TABLE_FORECAST,
                            WeatherTable.COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(WeatherTable.TABLE_FORECAST,
                            WeatherTable.COLUMN_ID + "=" + id
                                    + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case FORECASTS:
                rowsUpdated = sqlDB.update(WeatherTable.TABLE_FORECAST,
                        values,
                        selection,
                        selectionArgs);
                break;
            case FORECAST_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(WeatherTable.TABLE_FORECAST,
                            values,
                            WeatherTable.COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(WeatherTable.TABLE_FORECAST,
                            values,
                            WeatherTable.COLUMN_ID + "=" + id
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    private void checkColumns(String[] projection) {
        String[] available = { WeatherTable.COLUMN_LONGFORECAST,
                WeatherTable.COLUMN_CURRENT, WeatherTable.COLUMN_ID,
                WeatherTable.COLUMN_IS_SELECTED, WeatherTable.COLUMN_NAME};
        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
            // check if all columns which are requested are available
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException("Unknown columns in projection");
            }
        }
    }
}
