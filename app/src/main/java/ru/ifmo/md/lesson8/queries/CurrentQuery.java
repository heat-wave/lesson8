package ru.ifmo.md.lesson8.queries;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by daria on 29.11.14.
 */
public class CurrentQuery {

    private static final String CURRENT_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

     public String getWeatherData(String location){
         HttpURLConnection con = null;
         InputStream is = null;

         try {
             con = (HttpURLConnection) (new URL(CURRENT_URL + location)).openConnection();
             con.setRequestMethod("GET");
             con.setDoInput(true);
             con.setDoInput(true);
             con.connect();

             //Reading the response
             StringBuilder buffer = new StringBuilder();
             is = con.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is));
             String line = null;
             while ((line = br.readLine()) != null) {
                 buffer.append(line + "\r\n");
             }
             is.close();
             con.disconnect();
             return buffer.toString();

         }
         catch (Throwable t) {
             t.printStackTrace();
             Log.e("ERROR ", "Connection failed");
         }
         finally {
            try {
                is.close();
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
            try {
                con.disconnect();
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
         }
         return null;
     }

}
