package com.example.ux_design.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    @NonNull
    @ColumnInfo(name = "daterdv")
    public String daterdv;

    @NonNull
    @ColumnInfo(name = "emailMedecin")
    public String emailMedecin;

    @Nullable
    @ColumnInfo(name = "emailPatient")
    public String emailPatient;






    public String getDaterdv() {
        return daterdv;
    }

    public void setDaterdv(String daterdv) {
        this.daterdv = daterdv;
    }

    public String getEmailPatient() {
        return emailPatient;
    }

    public void setEmailPatient(String emailPatient) {
        this.emailPatient = emailPatient;
    }

    public String getEmailMedecin() {
        return emailMedecin;
    }

    public void setEmailMedecin(String emailMedecin) {
        this.emailMedecin = emailMedecin;
    }


}
