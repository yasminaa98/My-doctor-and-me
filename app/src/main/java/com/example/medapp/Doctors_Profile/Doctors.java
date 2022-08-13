package com.example.medapp.Doctors_Profile;

public class Doctors {


    private String Name,userid,Address,Region;
    private String speciality;
    private int Phone;
    //private String Phone;
    public Doctors() {
    }

    public Doctors(String name, String userid, String address, String region, String speciality, int phone) {
        Name = name;
        this.userid = userid;
        Address = address;
        Region = region;
        this.speciality = speciality;
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public Doctors(String name, String speciality, int phone) {
        Name = name;
        this.speciality = speciality;
        Phone = phone;
    }

    public Doctors(int phone) {
        Phone = phone;
    }
     public Doctors(String name, String userid, String speciality, int Phone) {
        Name = name;
        this.userid = userid;
        this.speciality = speciality;
        this.Phone = Phone;
    }

   /* public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = Phone;
    } */

    public Doctors(String name, String speciality) {
        Name = name;
        this.speciality = speciality;
    }

    public Doctors(String speciality) {
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

    public String getName() {
        return Name;
    }
}


