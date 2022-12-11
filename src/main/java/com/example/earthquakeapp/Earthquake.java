package com.example.earthquakeapp;

public class Earthquake {

    private String Country;
    private String Place;
    private String Magnitude;
    private String Date;
    private String Time;

    public Earthquake(String country,
                      String place,
                      String magnitude,
                      String date,
                      String time) {
        Country = country;
        Place = place;
        Magnitude = magnitude;
        Date = date;
        Time = time;
    }
}

