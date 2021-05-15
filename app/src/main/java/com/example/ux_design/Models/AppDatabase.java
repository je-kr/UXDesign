
package com.example.ux_design.Models;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ux_design.Models.DAO.MedecinDAO;
import com.example.ux_design.Models.DAO.PatientDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Medecin.class,Patient.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    public abstract MedecinDAO medecinDao();
    public abstract PatientDAO patientDao();

    private static AppDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;


    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


//    static AppDatabase getDatabase(@NonNull final Context context) {
//        if (INSTANCE == null) {
//            synchronized (AppDatabase.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                            AppDatabase.class, "AppDatabase")
//                            // Wipes and rebuilds instead of migrating
//                            // if no Migration object.
//                            // Migration is not part of this practical.
//                            .fallbackToDestructiveMigration()
//                            .createFromAsset("database.db")
//                            .build();
//                }
//            }
//        }
//        return INSTANCE;
//    }

}
