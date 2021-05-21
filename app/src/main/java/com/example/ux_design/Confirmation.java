package com.example.ux_design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ux_design.UI.Choisircreneau;
import com.example.ux_design.UI.MenuPatient;

public class Confirmation extends AppCompatActivity {
        TextView daterecup;
        Button annuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        daterecup = findViewById(R.id.textView23);
        annuler =  findViewById(R.id.buttonAnnuler);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");

        daterecup.setText(date);
        daterecup.setVisibility(View.VISIBLE);

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Confirmation.this, MenuPatient.class);
                startActivity(intent);
            }
        });

    }
}