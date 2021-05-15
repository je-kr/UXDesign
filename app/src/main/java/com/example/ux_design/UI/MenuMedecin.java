package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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






    }
}