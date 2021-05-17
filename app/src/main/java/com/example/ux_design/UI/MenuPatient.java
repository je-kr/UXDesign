package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ux_design.R;

import java.io.IOException;

public class MenuPatient extends AppCompatActivity {

    Button agendaButton;
    Button buttonRetour;
    Button rdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_patient);

        Intent intent = new Intent(this, AgendaPatient.class);

        agendaButton = findViewById(R.id.buttonAgendaPatient);
        buttonRetour = findViewById(R.id.buttonRetour2);
        rdv =  findViewById(R.id.prendrerdv);
        agendaButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPatient.this, MainActivity.class);
                startActivity(intent);
            }
        });

        rdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPatient.this, PrisedeRDV.class);
                Log.d("MenuPatient", "startActivity()");
                startActivity(intent);
            }
        });


    }
}