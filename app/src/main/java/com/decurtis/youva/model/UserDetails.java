package com.decurtis.youva.model;

import java.util.ArrayList;

public class UserDetails {

    private String name;
    private String type;
    private String imageURL;
    private String address;
    private String email;
    private String key;
    private ArrayList<String> individualInterest = new ArrayList<>();
    private ArrayList<String> businessInterest = new ArrayList<>();
    private int latitude;
    private int longitude;

    public UserDetails(String name,
                       String type,
                       String imageURL,
                       String address,
                       String email,
                       String key,
                       ArrayList<String> individualInterest,
                       ArrayList<String> businessInterest,
                       int latitude,
                       int longitude) {
        this.name = name;
        this.type = type;
        this.imageURL = imageURL;
        this.address = address;
        this.email = email;
        this.key = key;
        this.individualInterest = individualInterest;
        this.businessInterest = businessInterest;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UserDetails() {
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

    public String getKey() {
        return key;
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

    public void setKey(String key) {
        this.key = key;
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
