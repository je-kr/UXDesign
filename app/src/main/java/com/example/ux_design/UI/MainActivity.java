package com.example.ux_design.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ux_design.Models.AppDatabase;
import com.example.ux_design.Models.DAO.MedecinDAO;
import com.example.ux_design.Models.DAO.PatientDAO;
import com.example.ux_design.Models.Medecin;
import com.example.ux_design.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button buttonseconnecter, buttonoublie;
    EditText fieldemail,fieldmotpasse;
    Spinner dropdown;
    PatientDAO mPatientDAO;
    MedecinDAO mMedecinDAO;

    // On instancie une variable db représentant notre BDD
    AppDatabase db;


    private void createDb() { // Fonction permettant de créer la BDD à partir du fichier SQLITE3 database.db et de la stocker dans la variable db
        Context context = getApplicationContext();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "AppDatabase").fallbackToDestructiveMigration().createFromAsset("database.db").build();
    }

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
        mMedecinDAO = db.medecinDao();
        mPatientDAO = db.patientDao();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String email = fieldemail.getText().toString();
        String motpasse = fieldmotpasse.getText().toString();

        buttonseconnecter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        mMedecinDAO.findByEmail(email)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    medecinFound -> {
                                        if (email.equals(motpasse))
                                        Intent intent = new Intent(MainActivity.this, MenuMedecin.class);
                                        startActivity(intent);
                                        //Cette partie est effectuée dès que la Query a retourné un résultat (ici le résultat est représenté par la variable 'medecinFound' )
                                        // Il faut donc bien faire attention a effectuer les actions nécéssitant le résultat de la query dans cette partie

                                        // On peut donc récupérer les infos du médecin trouvé avec les getters de la classe :

                                        //Chaque attribut des tables patient/médecin a un getter associé (nom, telephone, email etc...) (voir les fichiers dans 'Models')

                                    },

                                    throwable -> {
                                        // Cette partie est executée quand la query a échoué
                                        Log.d("SubscribeSingle", "Query error");
                                    });
                          // Intent intent = new Intent(MainActivity.this, MenuMedecin.class);
                         //   startActivity(intent);
                        //
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