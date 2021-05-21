package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.ux_design.Confirmation;
import com.example.ux_design.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Choisircreneau extends AppCompatActivity {
     Button buttonRetour, prendrerdv;
     CalendarView calendar;
     TextView date;
     String selectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choisircreneau);
        buttonRetour = findViewById(R.id.buttonRetour4);
        calendar = (CalendarView) findViewById(R.id.calendarView);
        date = findViewById(R.id.textView11);
        prendrerdv = findViewById(R.id.buttonRetour5);


        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;

                date.setText(selectedDate);
                date.setVisibility(View.VISIBLE);
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