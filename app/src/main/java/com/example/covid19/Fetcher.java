package com.example.covid19;


import android.os.Build;
import androidx.annotation.RequiresApi;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Fetcher {
    private static ArrayList<CountryModel> countries;



    public static List<CountryModel> fetch() throws IOException, JSONException {
        JSONObject jsonObject = getJSONObjectFromURL("https://covid19.timsah.cloud/v2/current");
        JSONArray result = jsonObject.getJSONArray("data");

        countries = new ArrayList<>();

        for (int i=0; i<result.length(); i++) {
            JSONObject country = result.getJSONObject(i);

            countries.add(new CountryModel(
                    country.getString("location"),
                    country.getInt("confirmed"),
                    country.getInt("recovered"),
                    country.getInt("deaths"),
                    country.getInt("active")
            ));
        }
        return countries;
    }

    public static List<NewsModel> fetchNews() throws IOException, JSONException {

        HttpURLConnection urlConnection = null;
        URL url = new URL("https://api.collectapi.com/corona/coronaNews");
        urlConnection = (HttpURLConnection) url.openConnection();

        String userCredentials = "apikey 4gRBWILcPMMV1lHWOC0vVu:0kylcjqFHbDGhe7tgARyN1";

        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("content-type", "application/json");
        urlConnection.setRequestProperty ("authorization", userCredentials);
        urlConnection.setReadTimeout(100000 /* milliseconds */ );
        urlConnection.setConnectTimeout(30000 /* milliseconds */ );
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        InputStream inputStream;

        int status = urlConnection.getResponseCode();

        if (status != HttpURLConnection.HTTP_OK)
            inputStream = urlConnection.getErrorStream();
        else
            inputStream = urlConnection.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);

        JSONObject jsonObject = new JSONObject(jsonString);;
        JSONArray result = jsonObject.getJSONArray("result");

        ArrayList news = new ArrayList<>();

        for (int i=0; i<result.length(); i++) {
            JSONObject data = result.getJSONObject(i);

            news.add(new NewsModel(
                    data.getString("url"),
                    data.getString("description"),
                    data.getString("image"),
                    data.getString("name"),
                    data.getString("source"),
                    data.getString("date")
            ));
        }
        return news;
    }

    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }
}
