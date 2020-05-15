package com.example.covid19;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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
