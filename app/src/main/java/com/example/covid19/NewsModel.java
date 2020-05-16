package com.example.covid19;

public class NewsModel {
    String url;
    String description;
    String image;
    String name;
    String source;
    String date;

    public NewsModel(String url, String description, String image, String name, String source, String date) {

        this.url = url;
        this.description = description;
        this.image = image;
        this.name = name;
        this.source = source;
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }
}
