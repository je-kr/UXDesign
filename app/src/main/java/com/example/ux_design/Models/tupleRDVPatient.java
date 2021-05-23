package com.example.ux_design.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class tupleRDVPatient {
    @NonNull
    @ColumnInfo(name = "m.nom")
    public String nom;

    @NonNull
    @ColumnInfo(name = "m.adresse")
    public String adresse;

    @NonNull
    @ColumnInfo(name = "r.daterdv")
    public String daterdv;

    @NonNull
    public String getNom() {
        return nom;
    }

    @NonNull
    public String getAdresse() {
        return adresse;
    }

    @NonNull
    public String getDaterdv() {
        return daterdv;
    }
}
