package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.ux_design.Models.AdapterMedecinAgenda;
import com.example.ux_design.Models.AdapterPatientAgenda;
import com.example.ux_design.Models.AdapterPrendreRDV;
import com.example.ux_design.Models.AppDatabase;
import com.example.ux_design.Models.DAO.MedecinDAO;
import com.example.ux_design.Models.DAO.PatientDAO;
import com.example.ux_design.Models.DAO.RendezvousDAO;
import com.example.ux_design.Models.tupleRDVPatient;
import com.example.ux_design.R;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AgendaPatient extends AppCompatActivity {


    protected RecyclerView mRecyclerView;
    protected AdapterPatientAgenda mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<tupleRDVPatient> mDataset;

    CalendarView calendar;
    TextView textViewDate;

    PatientDAO mPatientDAO;
    MedecinDAO mMedecinDAO;
    RendezvousDAO mRendezvousDAO;

    AppDatabase db;
    String selectedDate;

    Button buttonRetour;

    private static final int DATASET_COUNT = 60;

    private void initDataset(String date,String email) {

        mRendezvousDAO.findListByDateEmailPatient(date,email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listeRdvFound -> {
                            mDataset = listeRdvFound;

                            mRecyclerView = findViewById(R.id.agendaPatient);

                            mLayoutManager = new LinearLayoutManager(getApplication());

                            mRecyclerView.setLayoutManager(mLayoutManager);

                            mAdapter = new AdapterPatientAgenda(mDataset);

                            mRecyclerView.setAdapter(mAdapter);

                        },
                        throwable -> {
                        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_patient);

        db = AppDatabase.getDatabase(getApplicationContext());

        mMedecinDAO = db.medecinDao();
        mPatientDAO = db.patientDao();
        mRendezvousDAO = db.rendezvousDao();

        calendar = (CalendarView) findViewById(R.id.calendarViewAgendaPatient);
        textViewDate = findViewById(R.id.textViewDateAgendaPatient);
        buttonRetour = findViewById(R.id.buttonRetourAgendaPatient);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                textViewDate.setText(selectedDate);
                calendar.setVisibility(View.INVISIBLE);
                initDataset(selectedDate,"francoise.dupont@gmail.com");
            }
        });
        textViewDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                calendar.setVisibility(View.VISIBLE);


            }
        });

        buttonRetour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AgendaPatient.this, MenuPatient.class);
                startActivity(intent);
            }
        });



    }
}