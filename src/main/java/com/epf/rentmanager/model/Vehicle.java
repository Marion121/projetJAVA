package com.epf.rentmanager.model;

public class Vehicle {
    private int id_V;
    private String constructeur;
    private int nb_places;

    public Vehicle(int id_V, String constructeur, int nb_places) {
        this.id_V = id_V;
        this.constructeur = constructeur;
        this.nb_places = nb_places;
    }

    public int getId_V() {
        return id_V;
    }

    public void setId_V(int id_V) {
        this.id_V = id_V;
    }

    public String getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }


    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id =" + id_V +
                ", constructeur='" + constructeur + '\'' +
                ", nb_places=" + nb_places +
                '}'+'\n';
    }
}
