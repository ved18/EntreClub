package com.example.entreclub;

public class deleteProfile {

    String name,emailid;

    public deleteProfile(){}

    public deleteProfile(String name, String emailid) {
        this.name = name;
        this.emailid = emailid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
}

