package com.example.medapp.Med_chat;


public class Meds {


    private String Name,userid;

    public Meds() {
    }

    public Meds(String name, String userid) {
        Name = name;
        this.userid = userid;
    }

    public String getuserid() {
        return userid;
    }

    public void setuserid(String userid) {
        this.userid = userid;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }
}
