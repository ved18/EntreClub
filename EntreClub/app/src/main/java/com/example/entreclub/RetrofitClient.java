package com.example.entreclub;

import java.util.ArrayList;

public class RetrofitClient {

    private String firstname,lastname,emailid,password,dob,contact,city,gender;
    private String company,position,description;
    ArrayList<String> haves = new ArrayList<String>();

    public RetrofitClient(String firstname, String lastname, String emailid, String password, String dob, String contact, String city, String gender, String company, String position, String description, ArrayList<String> haves) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailid = emailid;
        this.password = password;
        this.dob = dob;
        this.contact = contact;
        this.city = city;
        this.gender = gender;
        this.company = company;
        this.position = position;
        this.description = description;
        this.haves = haves;
    }



    public String getId() {
        return emailid;
    }


}
