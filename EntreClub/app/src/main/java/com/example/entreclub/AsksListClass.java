package com.example.entreclub;

public class AsksListClass {

  String emailid,description;

  public AsksListClass(){}


    public AsksListClass(String emailid, String description) {
        this.emailid = emailid;
        this.description = description;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
