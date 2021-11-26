package com.example.entreclub;

public class Event {

    String title, agenda,date,time,id,ammendments,decisions,scope,link,remarks;
    int amount;
    public Event()
    {

    }

    public Event(String title, String agenda, String date, String time, String id, String ammendments, String decisions, String scope, String link, String remarks, int amount) {
        this.title = title;
        this.agenda = agenda;
        this.date = date;
        this.time = time;
        this.id = id;
        this.ammendments = ammendments;
        this.decisions = decisions;
        this.scope = scope;
        this.link = link;
        this.remarks = remarks;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getAmmendments() {
        return ammendments;
    }

    public void setAmmendments(String ammendments) {
        this.ammendments = ammendments;
    }

    public String getDecisions() {
        return decisions;
    }

    public void setDecisions(String decisions) {
        this.decisions = decisions;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
