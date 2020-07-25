package com.example.youva.model;

import java.util.ArrayList;
import java.util.List;

public class UserDetails {

    private String name;
    private String type;
    private String imageURL;
    private String address;
    private String email;
    private String key;
    private ArrayList<String> individualInterest = new ArrayList<>();
    private ArrayList<String> businessInterest = new ArrayList<>();

    private String firstname;
    private String lastname;
    private Long phonenumber;
    private int gender;
    private List<Double> individuallonglat;
    private String businessname;
    private String businessaddress;
    private long businessphonenumber;
    private List<Double> businesslonglat;



    public UserDetails(String name,
                       String type,
                       String imageURL,
                       String address,
                       String email,
                       String key,
                       ArrayList<String> individualInterest,
                       ArrayList<String> businessInterest) {
        this.name = name;
        this.type = type;
        this.imageURL = imageURL;
        this.address = address;
        this.email = email;
        this.key = key;
        this.individualInterest = individualInterest;
        this.businessInterest = businessInterest;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public String getBusinessaddress() {
        return businessaddress;
    }

    public void setBusinessaddress(String businessaddress) {
        this.businessaddress = businessaddress;
    }

    public long getBusinessphonenumber() {
        return businessphonenumber;
    }

    public void setBusinessphonenumber(long businessphonenumber) {
        this.businessphonenumber = businessphonenumber;
    }

    public List<Double> getIndividuallonglat() {
        return individuallonglat;
    }

    public void setIndividuallonglat(List<Double> individuallonglat) {
        this.individuallonglat = individuallonglat;
    }

    public List<Double> getBusinesslonglat() {
        return businesslonglat;
    }

    public void setBusinesslonglat(List<Double> businesslonglat) {
        this.businesslonglat = businesslonglat;
    }
}
