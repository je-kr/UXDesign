package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.ux_design.Models.AppDatabase;
import com.example.ux_design.Models.AdapterMedecinAgenda;
import com.example.ux_design.Models.DAO.MedecinDAO;
import com.example.ux_design.Models.DAO.PatientDAO;
import com.example.ux_design.Models.DAO.RendezvousDAO;
import com.example.ux_design.Models.Medecin;
import com.example.ux_design.Models.Rendezvous;
import com.example.ux_design.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AgendaMedecin extends AppCompatActivity {

    protected RecyclerView mRecyclerView;
    protected AdapterMedecinAgenda mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    String[] mDataset;

    Button buttonRecycler[];

    // On instancie un DAO (Database Access Object) par entité de la BDD à manipuler
    PatientDAO mPatientDAO;
    MedecinDAO mMedecinDAO;
    RendezvousDAO mRendezvousDAO;

    CalendarView calendar;

    AppDatabase db;

    TextView textViewDate;

    String selectedDate;

    Medecin currentMedecin;



    private void initDataset() {
        mDataset = new String[11];
        for (int i = 0; i < 11; i++) {
            mDataset[i] =  String.format("%02d:%02d",i+8,0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = AppDatabase.getDatabase(getApplicationContext());

        mMedecinDAO = db.medecinDao();
        mPatientDAO = db.patientDao();
        mRendezvousDAO = db.rendezvousDao();



        setContentView(R.layout.activity_agenda_medecin);

        initDataset();

        calendar = (CalendarView) findViewById(R.id.calendarAgendaMedecin);

        mRecyclerView = findViewById(R.id.agendaMedecin);
        textViewDate = findViewById(R.id.textViewDateAgendaMedecin);

        mLayoutManager = new LinearLayoutManager(getApplication());

        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                textViewDate.setText(selectedDate);
                calendar.setVisibility(View.INVISIBLE);
                mAdapter.notifyDataSetChanged();
            }
        });

        textViewDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                calendar.setVisibility(View.VISIBLE);

            }
        });


        mAdapter = new AdapterMedecinAgenda(mDataset){
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, final int position) {

                viewHolder.getTextView().setText(mDataset[position]);

                String datetime = (String) viewHolder.getTextView().getText() + ' ' + textViewDate.getText();

                mRendezvousDAO.findListByDateEmailMedecin(datetime,email)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                rdvFound -> {
                                    viewHolder.getBoutonCreerCreneau().setEnabled(false);
                                    viewHolder.getBoutonCreerCreneau().setText("M "+rdvFound.getNom());
                                    viewHolder.getBoutonCreerCreneau().setBackgroundColor(0x42424242);

                                },
                                throwable -> {
                                });

                viewHolder.getBoutonCreerCreneau().setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Button button = (Button) v;
                        if(button.getText().equals("Créer créneau")) {
                            button.setBackgroundColor(0xFF6200EE);
                            button.setText("Supprimer créneau");
                            créerCréneau(datetime,email);
                        }
                        else{
                            supprimerCréneau(datetime,email);
                            button.setBackgroundColor(0xFF018786);
                            button.setText("Créer créneau");
                        }
                    }
                });

                viewHolder.getTextView().setText(mDataset[position]);
            }
        };

        mRecyclerView.setAdapter(mAdapter);

    }

    public void supprimerCréneau(String date,String emailMedecin){

        mRendezvousDAO.findByDateEmail(date,emailMedecin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        rdvFound -> {

                            db.databaseWriteExecutor.execute(() -> {
                                mRendezvousDAO.delete(rdvFound);
                            });
                        },

                        throwable -> {
                            Log.d("SubscribeSingle", "Query error");
                        });


    }
    public void créerCréneau(String date,String emailMedecin){

        Rendezvous rendezvous = new Rendezvous();

        rendezvous.setDaterdv(date);
        rendezvous.setEmailMedecin(emailMedecin);
        db.databaseWriteExecutor.execute(() -> {
            mRendezvousDAO.insertAll(rendezvous);
        });


    }
}