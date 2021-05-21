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

import com.example.ux_design.Confirmation;
import com.example.ux_design.Models.AdapterChoisirCreneau;
import com.example.ux_design.Models.AdapterMedecinAgenda;
import com.example.ux_design.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Choisircreneau extends AppCompatActivity {
     Button buttonRetour, prendrerdv;
     CalendarView calendar;
     String selectedDate;

    protected RecyclerView mRecyclerView;
    protected AdapterChoisirCreneau mAdapter;
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
        setContentView(R.layout.activity_choisircreneau);
        buttonRetour = findViewById(R.id.buttonRetour4);
        calendar = (CalendarView) findViewById(R.id.calendarView);
        prendrerdv = findViewById(R.id.buttonRetour5);

        initDataset();

        mRecyclerView = findViewById(R.id.recyclerChoisirCreneau);

        mLayoutManager = new LinearLayoutManager(getApplication());

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AdapterChoisirCreneau(mDataset);

        mRecyclerView.setAdapter(mAdapter);



        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            }
        });


        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choisircreneau.this, MenuPatient.class);
                startActivity(intent);
            }
        });
        prendrerdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choisircreneau.this, Confirmation.class);
                intent.putExtra("date",selectedDate);
                startActivity(intent);
            }
        });
    }
}