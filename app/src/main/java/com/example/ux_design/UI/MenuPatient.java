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
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        agendaButton = findViewById(R.id.buttonAgendaPatient);

        rdv =  findViewById(R.id.prendrerdv);

        agendaButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPatient.this, AgendaPatient.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });


        rdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPatient.this, PrisedeRDV.class);
                intent.putExtra("email",email);
                Log.d("MenuPatient", "startActivity()");
                startActivity(intent);
            }
        });






    }
}