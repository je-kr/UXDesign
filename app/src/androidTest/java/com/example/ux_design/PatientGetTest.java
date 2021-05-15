package com.example.ux_design;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.ux_design.Models.AppDatabase;
import com.example.ux_design.Models.DAO.PatientDAO;
import com.example.ux_design.Models.Medecin;
import com.example.ux_design.Models.Patient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class PatientGetTest {
    public PatientDAO mPatientDAO;

    public Patient queryResult;

    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = getApplicationContext();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "AppDatabase").fallbackToDestructiveMigration().createFromAsset("database.db").build();

        mPatientDAO = db.patientDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void patientGet() {
        this.queryResult =  mPatientDAO.findByEmail("francoise.dupont@gmail.com").blockingGet();
        assertEquals(this.queryResult.getNom(), "Dupont");
    }
}

