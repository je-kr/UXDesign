package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ux_design.Models.AdapterChoisirCreneau;
import com.example.ux_design.Models.AdapterPrendreRDV;
import com.example.ux_design.Models.AppDatabase;
import com.example.ux_design.Models.DAO.MedecinDAO;
import com.example.ux_design.Models.Medecin;
import com.example.ux_design.R;

import java.util.List;
import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PrisedeRDV extends AppCompatActivity {

    Button buttonrechercher, buttonRetour3;
    EditText fieldcodepost;
    TextView format;
    protected RecyclerView mRecyclerView;
    protected AdapterPrendreRDV mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Medecin> mDataset;

    String emailPatient;

    MedecinDAO mMedecinDAO;
    AppDatabase db;

    private void initDataset(String cp) {

        mMedecinDAO.findByCP(cp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listeMedecins -> {
                            mDataset = listeMedecins;

                            mRecyclerView = findViewById(R.id.recyclerPriseRDV);

                            mLayoutManager = new LinearLayoutManager(getApplication());

                            mRecyclerView.setLayoutManager(mLayoutManager);

                            mAdapter = new AdapterPrendreRDV(mDataset,emailPatient);

                            mRecyclerView.setAdapter(mAdapter);

                        },
                        throwable -> {
                        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prisede_r_d_v);

        db = AppDatabase.getDatabase(getApplicationContext());

        mMedecinDAO = db.medecinDao();

        buttonrechercher = findViewById(R.id.buttonrechercher);
        fieldcodepost = findViewById(R.id.PostalAddress);
        format = findViewById(R.id.textView12);

        Intent intent = getIntent();
        emailPatient = intent.getStringExtra("email");

        buttonrechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codePostal = fieldcodepost.getText().toString();
                if (Pattern.matches("\\d{5}",codePostal)){
                    Log.d("PatternMatch", "OK");
                    initDataset(codePostal);
                }
                else {
                    format.setText("Format incorrect");
                    format.setVisibility(View.VISIBLE);
                }
            }
        });


    }
}