package com.example.ux_design;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import com.example.ux_design.Models.AppDatabase;
import com.example.ux_design.Models.DAO.MedecinDAO;
import com.example.ux_design.Models.Medecin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MedecinGetTest {
    public MedecinDAO mMedecinDAO;

    public Medecin queryResult;

    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = getApplicationContext();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "AppDatabase").fallbackToDestructiveMigration().createFromAsset("database.db").build();

        mMedecinDAO = db.medecinDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void medecinGet() {
        this.queryResult =  mMedecinDAO.findByEmail("dr.smith@gmail.com").blockingGet();
        assertEquals(this.queryResult.getMotpasse(), "Azerty123");
    }

}

