package com.example.covid_19apps.Model;

public class User {

    private int id;
    private String provinsi;
    private int positif;
    private int meninggal;
    private int sembuh;

    public User(int id, String provinsi, int positif, int meninggal, int sembuh) {
        this.id = id;
        this.provinsi = provinsi;
        this.positif = positif;
        this.meninggal = meninggal;
        this.sembuh = sembuh;
    }

    public int getId() {
        return id;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public int getPositif() {
        return positif;
    }

    public int getMeninggal() {
        return meninggal;
    }

    public int getSembuh() {
        return sembuh;
    }
}
