package com.example.medapp.Session_Patient;

public class Patients {
    String NAME;
    String SURNAME;
    String PHONE;
    String AGE;

    public Patients() {
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setSURNAME(String SURNAME) {
        this.SURNAME = SURNAME;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
    }

    public String getNAME() {
        return NAME;
    }

    public String getSURNAME() {
        return SURNAME;
    }

    public String getPHONE() {
        return PHONE;
    }

    public String getAGE() {
        return AGE;
    }

    public Patients(String NAME, String SURNAME, String PHONE, String AGE) {
        this.NAME = NAME;
        this.SURNAME = SURNAME;
        this.PHONE = PHONE;
        this.AGE = AGE;
    }


}
