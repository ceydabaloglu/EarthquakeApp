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
    public String getCountry(){
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getPlace() {
        return Place;
    }

    public void setMagnitude(String magnitude) {
        Magnitude = magnitude;
    }
    public String getMagnitude(){
        return Magnitude;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDate() {
        return Date;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTime() {
        return Time;
    }
}

