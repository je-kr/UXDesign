package com.example.ux_design.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity
public class Medecin {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    public String email;

    @NonNull
    @ColumnInfo(name = "nom")
    public String nom;

    @NonNull
    @ColumnInfo(name = "adresse")
    public String adresse;

    @NonNull
    @ColumnInfo(name = "motpasse")
    public String motpasse;

    @NonNull
    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMotpasse() {
        return motpasse;
    }
}
