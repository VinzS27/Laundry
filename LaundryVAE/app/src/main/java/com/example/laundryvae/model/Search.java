package com.example.laundryvae.model;

public class Search {

    private int image;
    private String name;
    private String rate;
    private String position;


    public Search(int image, String name, String rate, String position) {
        this.image = image;
        this.name = name;
        this.rate = rate;
        this.position = position;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
