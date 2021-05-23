package com.example.ux_design.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class tupleRDVMedecin {
    @NonNull
    @ColumnInfo(name = "nom")
    public String nom;

    @NonNull
    @ColumnInfo(name = "adresse")
    public String adresse;

    @NonNull
    @ColumnInfo(name = "daterdv")
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
