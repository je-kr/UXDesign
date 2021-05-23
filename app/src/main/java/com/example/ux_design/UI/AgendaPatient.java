package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ux_design.Models.AdapterMedecinAgenda;
import com.example.ux_design.R;

public class AgendaPatient extends AppCompatActivity {


    protected RecyclerView mRecyclerView;
    protected AdapterMedecinAgenda mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;

    private static final int DATASET_COUNT = 60;

    private void initDataset() {
        mDataset = new String[DATASET_COUNT];
        for (int i = 0; i < DATASET_COUNT; i++) {
            mDataset[i] = "This is element #" + i;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_patient);

        initDataset();


        mRecyclerView = findViewById(R.id.agendaPatient);
        mLayoutManager = new LinearLayoutManager(getApplication());

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AdapterMedecinAgenda(mDataset);

        mRecyclerView.setAdapter(mAdapter);







    }
}