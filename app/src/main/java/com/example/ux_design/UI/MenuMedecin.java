package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ux_design.R;

public class MenuMedecin extends AppCompatActivity {


    Button buttonRetour,buttonSaisieDossier,buttonConsulterDossier,buttonAgenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_medecin);

        buttonRetour = findViewById(R.id.buttonRetour);
        buttonSaisieDossier = findViewById(R.id.buttonSaisieDossier);
        buttonConsulterDossier = findViewById(R.id.buttonConsulterDossier);
        buttonConsulterDossier = findViewById(R.id.buttonConsulterDossier);
        buttonAgenda = findViewById(R.id.buttonAgenda);

        buttonRetour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuMedecin.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonAgenda.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuMedecin.this, AgendaMedecin.class);
                startActivity(intent);
            }
        });



    }
}