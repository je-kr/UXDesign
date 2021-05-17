package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ux_design.Models.CustomAdapter;
import com.example.ux_design.R;

public class AgendaMedecin extends AppCompatActivity {

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;



    private void initDataset() {
        mDataset = new String[11];
        for (int i = 8; i < 19; i++) {
            mDataset[i-8] =  String.format("%02d:%02d",i,0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_medecin);

        initDataset();


        mRecyclerView = findViewById(R.id.agenda1);
        mLayoutManager = new LinearLayoutManager(getApplication());

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CustomAdapter(mDataset);

        mRecyclerView.setAdapter(mAdapter);

    }
}