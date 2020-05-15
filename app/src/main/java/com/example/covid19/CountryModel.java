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

public class CountryModel {

    String location;
    int total;
    int recovered;
    int deaths;
    int active;


    public CountryModel(String location, int total, int recovered, int deaths, int active) {

        this.location = location;
        this.total = total;
        this.recovered = recovered;
        this.deaths = deaths;
        this.active = active;
    }

    public String getLocation() {
        return location;
    }

    public int getTotal() {
        return total;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getActive() {
        return active;
    }
}


