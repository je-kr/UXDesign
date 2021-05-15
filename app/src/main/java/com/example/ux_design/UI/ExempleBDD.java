package com.example.ux_design.UI;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.ux_design.Models.AppDatabase;
import com.example.ux_design.Models.DAO.MedecinDAO;
import com.example.ux_design.Models.DAO.PatientDAO;
import com.example.ux_design.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


// Exemple utilisation BDD

public class ExempleBDD extends AppCompatActivity {

    TextView textView;

    // On instancie un DAO (Database Access Object) par entité de la BDD à manipuler
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
        setContentView(R.layout.exemple_bdd);

        textView = findViewById(R.id.textView5);

        createDb();

        // On récupère les DAO de notre BDD :

        mMedecinDAO = db.medecinDao();
        mPatientDAO = db.patientDao();

        // Exemple pour retrouver un médecin par email :

        // On appelle la fonction findByEmail du DAO mMedecinDAO

        mMedecinDAO.findByEmail("dr.smith@gmail.com")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        medecinFound -> {
                            //Cette partie est effectuée dès que la Query a retourné un résultat (ici le résultat est représenté par la variable 'medecinFound' )
                            // Il faut donc bien faire attention a effectuer les actions nécéssitant le résultat de la query dans cette partie

                            // On peut donc récupérer les infos du médecin trouvé avec les getters de la classe :

                            textView.setText(medecinFound.getMotpasse()); // Par exemple on récupère le mdp avec getMotpasse() et on l'affiche dans un textView

                            //Chaque attribut des tables patient/médecin a un getter associé (nom, telephone, email etc...) (voir les fichiers dans 'Models')

                        },

                        throwable -> {
                            // Cette partie est executée quand la query a échoué
                            Log.d("SubscribeSingle", "Query error");
                        });


        }

}

