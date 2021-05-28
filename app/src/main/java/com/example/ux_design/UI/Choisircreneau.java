package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.ux_design.Models.AdapterChoisirCreneau;
import com.example.ux_design.Models.AdapterPatientAgenda;
import com.example.ux_design.Models.AppDatabase;
import com.example.ux_design.Models.DAO.MedecinDAO;
import com.example.ux_design.Models.DAO.PatientDAO;
import com.example.ux_design.Models.DAO.RendezvousDAO;
import com.example.ux_design.Models.Rendezvous;
import com.example.ux_design.R;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Choisircreneau extends AppCompatActivity {
     Button buttonRetour, prendrerdv;
     CalendarView calendar;
     TextView date;
     String selectedDate;

     TextView textViewAucunRDV,textViewNomMedecin;

    PatientDAO mPatientDAO;
    MedecinDAO mMedecinDAO;
    RendezvousDAO mRendezvousDAO;
    AppDatabase db;

    String emailPatient;


    protected RecyclerView mRecyclerView;
    protected AdapterChoisirCreneau mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Rendezvous> mDataset;

    private void initDataset(String date,String email) {

        mRendezvousDAO.findListFreeRDV(date,email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listeRdvFound -> {
                            if (listeRdvFound.size()!=0) {
                                mDataset = listeRdvFound;
                                mRecyclerView = findViewById(R.id.recyclerChoisirCreneau);
                                mLayoutManager = new LinearLayoutManager(getApplication());
                                mRecyclerView.setLayoutManager(mLayoutManager);
                                mAdapter = new AdapterChoisirCreneau(mDataset,emailPatient);
                                mRecyclerView.setAdapter(mAdapter);
                            }
                            else{
                                textViewAucunRDV.setVisibility(View.VISIBLE);
                            }

                        },
                        throwable -> {
                            textViewAucunRDV.setVisibility(View.VISIBLE);

                        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choisircreneau);
        buttonRetour = findViewById(R.id.buttonRetour4);
        calendar = (CalendarView) findViewById(R.id.calendarView);
        date = findViewById(R.id.textView11);

        db = AppDatabase.getDatabase(getApplicationContext());

        mMedecinDAO = db.medecinDao();
        mPatientDAO = db.patientDao();
        mRendezvousDAO = db.rendezvousDao();

        textViewAucunRDV = findViewById(R.id.textViewAucunRDV);
        textViewNomMedecin = findViewById(R.id.textViewNomMedecinChoisirCreneau);
        Intent intent = getIntent();

        textViewNomMedecin.setText(intent.getStringExtra("NomMedecin"));

        emailPatient = intent.getStringExtra("EmailPatient");

        calendar.setVisibility(View.INVISIBLE);
        //textViewAucunRDV.setVisibility(View.INVISIBLE);
        date.setVisibility(View.VISIBLE);

        date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calendar.setVisibility(View.VISIBLE);
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;

                date.setText(selectedDate);
                calendar.setVisibility(View.INVISIBLE);
                initDataset(selectedDate,intent.getStringExtra("EmailMedecin"));
                textViewAucunRDV.setVisibility(View.INVISIBLE);
                Log.d("setOnDateChangeListener", "calendar");
            }
        });


        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choisircreneau.this, MenuPatient.class);
                startActivity(intent);
            }
        });
//        prendrerdv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Choisircreneau.this, Confirmation.class);
//                intent.putExtra("date",selectedDate);
//                startActivity(intent);
//            }
//        });
    }
}