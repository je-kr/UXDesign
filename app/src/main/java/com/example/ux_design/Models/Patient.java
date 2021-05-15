package com.example.ux_design.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Patient {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    public String email;

    @NonNull
    @ColumnInfo(name = "prenom")
    public String prenom;

    @NonNull
    @ColumnInfo(name = "nom")
    public String nom;

    @NonNull
    @ColumnInfo(name = "numtelephone")
    public int numtelephone;

    @NonNull
    @ColumnInfo(name = "adresse")
    public String adresse;

    @NonNull
    @ColumnInfo(name = "motpasse")
    public String motpasse;

    public String getEmail() {
        return email;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public int getNumtelephone() {
        return numtelephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMotpasse() {
        return motpasse;
    }




}
