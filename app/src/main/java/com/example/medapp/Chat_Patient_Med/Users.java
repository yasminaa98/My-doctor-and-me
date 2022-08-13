package com.example.medapp.Chat_Patient_Med;


public class Users {


    private String Name,userid;
    private String speciality;
    private int Phone;
    public Users() {
    }
    public Users(String name, String userid, String speciality, int Phone) {
        this.Name = name;
        this.userid = userid;
        this.speciality = speciality;
        this.Phone = Phone;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = Phone;
    }

    public Users(String name, String userid, String speciality) {
        Name = name;
        this.userid = userid;
        this.speciality = speciality;
    }

    public String getuserid() {
        return userid;
    }

    public void setuserid(String userid) {
        this.userid = userid;
    }

    public Users(String speciality) {
        this.speciality = speciality;
    }

    public void setspeciality(String speciality) {
        this.speciality = speciality;
    }
    public String getspeciality() {
        return speciality;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Users (String Name,String speciality) {
        this.Name = Name;
        this.speciality=speciality;
    }
    public String getName() {
        return Name;
    }
}
