package com.decurtis.youva.model;

import java.util.ArrayList;

public class UserDeails {

    private String name;
    private String type;
    private String imageURL;
    private String address;
    private String email;
    private ArrayList<String> individualInterest;
    private ArrayList<String> businessInterest;
    private int latitude;
    private int longitude;

    public UserDeails(String name,
                      String type,
                      String imageURL,
                      String address,
                      String email,
                      ArrayList<String> individualInterest,
                      ArrayList<String> businessInterest,
                      int latitude,
                      int longitude) {
        this.name = name;
        this.type = type;
        this.imageURL = imageURL;
        this.address = address;
        this.email = email;
        this.individualInterest = individualInterest;
        this.businessInterest = businessInterest;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UserDeails() {
        /*
         * Empty constructor
         * */
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<String> getIndividualInterest() {
        return individualInterest;
    }

    public ArrayList<String> getBusinessInterest() {
        return businessInterest;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIndividualInterest(ArrayList<String> individualInterest) {
        this.individualInterest = individualInterest;
    }

    public void setBusinessInterest(ArrayList<String> businessInterest) {
        this.businessInterest = businessInterest;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

}
