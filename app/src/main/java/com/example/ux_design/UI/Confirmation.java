package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ux_design.Models.AdapterChoisirCreneau;
import com.example.ux_design.Models.AppDatabase;
import com.example.ux_design.Models.DAO.MedecinDAO;
import com.example.ux_design.Models.DAO.PatientDAO;
import com.example.ux_design.Models.DAO.RendezvousDAO;
import com.example.ux_design.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Confirmation extends AppCompatActivity {
        TextView nomMedecin,adresseMedecin,dateHeureRDV;
        Button annuler,buttonConfirmer;

    PatientDAO mPatientDAO;
    MedecinDAO mMedecinDAO;
    RendezvousDAO mRendezvousDAO;
    AppDatabase db;

    String emailPatient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);


        db = AppDatabase.getDatabase(getApplicationContext());

        mMedecinDAO = db.medecinDao();
        mPatientDAO = db.patientDao();
        mRendezvousDAO = db.rendezvousDao();


        dateHeureRDV = findViewById(R.id.textViewDateHeureRDV);
        nomMedecin = findViewById(R.id.textViewNomMedecinConfirmation);
        adresseMedecin = findViewById(R.id.textViewAdresseMedecinConfirmation);
        annuler =  findViewById(R.id.buttonAnnuler);
        buttonConfirmer = findViewById(R.id.buttonConfirmer);

        Intent intent = getIntent();
        String date = intent.getStringExtra("DateRDV");
        emailPatient = intent.getStringExtra("EmailPatient");

        dateHeureRDV.setText(date);

        mMedecinDAO.findByEmail(intent.getStringExtra("EmailMedecin"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        medecin -> {
                            nomMedecin.setText(medecin.getNom());
                            adresseMedecin.setText(medecin.getAdresse());
                        },
                        throwable -> {
                        });





        buttonConfirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRendezvousDAO.findByDateEmail(date,intent.getStringExtra("EmailMedecin"))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                rdvFound -> {

                                    rdvFound.setEmailPatient(emailPatient);
                                    db.databaseWriteExecutor.execute(() -> {
                                        mRendezvousDAO.updateRDV(rdvFound);
                                        Log.d("databaseWriteExecutor","updateRDV");
                                    });
                                    Intent intent = new Intent(Confirmation.this, MenuPatient.class);
                                    intent.putExtra("email",emailPatient);
                                    startActivity(intent);

                                },
                                throwable -> {
                                    Log.d("SubscribeConfirm","Throwable");
                                });


            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Confirmation.this, MenuPatient.class);
                intent.putExtra("email",emailPatient);
                startActivity(intent);
            }
        });


    }
}