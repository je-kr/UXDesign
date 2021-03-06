
package com.example.ux_design.Models;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ux_design.Models.DAO.MedecinDAO;
import com.example.ux_design.Models.DAO.PatientDAO;
import com.example.ux_design.Models.DAO.RendezvousDAO;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Medecin.class,Patient.class,Rendezvous.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {


    public ExecutorService databaseWriteExecutor=
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract MedecinDAO medecinDao();
    public abstract PatientDAO patientDao();
    public abstract RendezvousDAO rendezvousDao();

    private static AppDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;




    public static AppDatabase getDatabase(@NonNull final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "AppDatabase")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .createFromAsset("database.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
