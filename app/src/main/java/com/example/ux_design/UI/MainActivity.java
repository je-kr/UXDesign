package com.example.ux_design.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ux_design.Models.AppDatabase;
import com.example.ux_design.Models.DAO.MedecinDAO;
import com.example.ux_design.Models.DAO.PatientDAO;
import com.example.ux_design.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button buttonseconnecter, buttonoublie;
    EditText fieldemail, fieldmotpasse;
    Spinner dropdown;
    TextView motincorrect;
    PatientDAO mPatientDAO;
    MedecinDAO mMedecinDAO;

    // On instancie une variable db représentant notre BDD
    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] items = new String[]{"Médecin", "Patient", "Pharmacie"};

        buttonseconnecter = findViewById(R.id.seconnecter);
        fieldemail = findViewById(R.id.email);
        fieldmotpasse = findViewById(R.id.motpasse);
        buttonoublie = findViewById(R.id.motpasseoublie);
        motincorrect = findViewById(R.id.textView6);


        dropdown = (Spinner) findViewById(R.id.droplist);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);


        db = AppDatabase.getDatabase(getApplicationContext());

        mMedecinDAO = db.medecinDao();
        mPatientDAO = db.patientDao();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        buttonseconnecter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = fieldemail.getText().toString();
                String motpasse = fieldmotpasse.getText().toString();
                Log.d("Email : ", email);
                switch (position) {
                    case 0:
                        mMedecinDAO.findByEmail(email)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        medecinFound -> {
                                            if (medecinFound.getMotpasse().equals(motpasse)) {
                                                Intent intent = new Intent(MainActivity.this, MenuMedecin.class);
                                                startActivity(intent);
                                            } else {
                                                motincorrect.setText("Mot de passe incorrect");
                                                motincorrect.setVisibility(View.VISIBLE);
                                            }
                                        },

                                        throwable -> {
                                            // Cette partie est executée quand la query a échoué
                                            Log.d("SubscribeSingle", "Query error");
                                        });
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