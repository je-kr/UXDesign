package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ux_design.R;

import java.util.regex.Pattern;

public class PrisedeRDV extends AppCompatActivity {
    Button buttonrechercher, buttonRetour3;
    EditText fieldcodepost;
    TextView format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prisede_r_d_v);

        buttonrechercher = findViewById(R.id.buttonrechercher);
        fieldcodepost = findViewById(R.id.PostalAddress);
        buttonRetour3 =findViewById(R.id.buttonRetour3);
        format = findViewById(R.id.textView12);

        buttonrechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codePostal = fieldcodepost.getText().toString();
                if (Pattern.matches("\\d{5}",codePostal)){
                    Log.d("PatternMatch", "OK");
                }
                else {
                    format.setText("Format incorrect");
                    format.setVisibility(View.VISIBLE);
                }
            }
        });

        buttonRetour3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrisedeRDV.this, MenuPatient.class);
                startActivity(intent);
            }
        });

    }
}