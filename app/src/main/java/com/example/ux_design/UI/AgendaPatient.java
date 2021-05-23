package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

    PatientDAO mPatientDAO;
    MedecinDAO mMedecinDAO;
    RendezvousDAO mRendezvousDAO;

    AppDatabase db;

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

        initDataset("08/12/2021","francoise.dupont@gmail.com");





    }
}