package com.example.android_finalexam;

// POI class that contains the blueprint of POI
public class POI {
    private String name;
    private String country;
    private String image;
    private double price;

    // constructor for the POI class
    public POI(String name, String country, String image, double price) {
        this.name = name;
        this.country = country;
        this.image = image;
        this.price = price;
    }

    // getter methods for the class variables
    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }
}
