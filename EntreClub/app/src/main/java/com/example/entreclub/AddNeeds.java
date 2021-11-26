package com.example.entreclub;

public class AddNeeds {
    String emailid,description;


    public AddNeeds(String emailid, String description) {
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
