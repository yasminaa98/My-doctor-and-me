package com.example.medapp.Reservation;

public class Reservations{
    private String Patient;
    private String Time,DoctorsName,Address,Region;

    public Reservations(String patient, String time, String doctorsName) {
        Patient = patient;
        Time = time;
        DoctorsName = doctorsName;
    }

    public Reservations(String patient, String time, String doctorsName, String address, String region) {
        Patient = patient;
        Time = time;
        DoctorsName = doctorsName;
        Address = address;
        Region = region;
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

    public String getDoctorsName() {
        return DoctorsName;
    }

    public void setDoctorsName(String doctorsName) {
        DoctorsName = doctorsName;
    }

    public Reservations() {
    }

    public String getPatient() {
        return Patient;
    }

    public void setPatient(String patient) {
        Patient = patient;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }



}
