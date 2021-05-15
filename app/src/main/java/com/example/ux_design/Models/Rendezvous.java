package com.example.ux_design.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(primaryKeys = {"daterdv","emailMedecin"},
        foreignKeys= {
                @ForeignKey(entity=Medecin.class,
                childColumns="emailMedecin",
                parentColumns="email"),
                @ForeignKey(entity=Patient.class,
                childColumns="emailPatient",
                 parentColumns="email")
                    }
        )


public class Rendezvous {

    @PrimaryKey
    @ColumnInfo(name = "daterdv")
    public String daterdv;

    @ColumnInfo(name = "emailPatient")
    public String emailPatient;

    @ColumnInfo(name = "emailMedecin")
    public String emailMedecin;


}
