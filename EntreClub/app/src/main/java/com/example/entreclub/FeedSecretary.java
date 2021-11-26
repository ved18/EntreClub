package com.example.entreclub;

public class FeedSecretary {
    String title;
    String date;
    String agenda,id;

    public FeedSecretary()
    {

    }

    public FeedSecretary(String title, String date, String agenda, String id)
    {
        this.title=title;
        this.date=date;
        this.agenda=agenda;
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAgenda() {
        return agenda;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }
}
