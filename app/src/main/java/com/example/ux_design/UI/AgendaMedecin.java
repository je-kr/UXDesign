package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    protected String[] mDataset;

    Button buttonRecycler[];

    // On instancie un DAO (Database Access Object) par entité de la BDD à manipuler
    PatientDAO mPatientDAO;
    MedecinDAO mMedecinDAO;
    RendezvousDAO mRendezvousDAO;

    AppDatabase db;

    EditText editTextDate;

    Medecin currentMedecin;



    private void initDataset() {
        mDataset = new String[11];
        for (int i = 8; i < 19; i++) {
            mDataset[i-8] =  String.format("%02d:%02d",i,0);
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

        mRecyclerView = findViewById(R.id.agenda1);
        editTextDate = findViewById(R.id.editTextDateAgendaMedecin);

        mLayoutManager = new LinearLayoutManager(getApplication());

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AdapterMedecinAgenda(mDataset){
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, final int position) {

                // Get element from your dataset at this position and replace the
                // contents of the view with that element

                viewHolder.getBoutonCreerCreneau().setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Button button = (Button) v;
                        if(button.getText().equals("Créer créneau")) {
                            button.setBackgroundColor(0xFF6200EE);
                            button.setText("Supprimer créneau");
                            créerCréneau((String) viewHolder.getTextView().getText()+' '+editTextDate.getText(),"dr.smith@gmail.com");
                        }
                        else{
                            supprimerCréneau((String) viewHolder.getTextView().getText(),"dr.smith@gmail.com");
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