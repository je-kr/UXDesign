package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ux_design.Models.DAO.MedecinDAO;
import com.example.ux_design.Models.Medecin;
import com.example.ux_design.R;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button buttonseconnecter, buttonoublie;
    EditText fieldemail,fieldmotpasse;
    Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] items = new String[]{"Médecin", "Patient", "Pharmacie"};

        buttonseconnecter = findViewById(R.id.seconnecter);
        fieldemail = findViewById(R.id.email);
        fieldmotpasse = findViewById(R.id.motpasse);
        buttonoublie = findViewById(R.id.motpasseoublie);

        dropdown = (Spinner)findViewById(R.id.droplist);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String email = fieldemail.getText().toString();
        String motpasse = fieldmotpasse.getText().toString();
        buttonseconnecter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        //if (email.equals(mMedecinDAO.findByEmail) && motpasse.equals(Medecin.motpasse)) {
                           // Intent intent = new Intent(MainActivity.this, MenuMedecin.class);
                         //   startActivity(intent);
                        //}
                        break;
                    case 1:
                        // Whatever you want to happen when the second item gets selected
                        break;
                    case 2:
                        // Whatever you want to happen when the thrid item gets selected
                        break;
                }
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}