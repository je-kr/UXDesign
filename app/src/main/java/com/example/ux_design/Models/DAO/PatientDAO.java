package com.example.ux_design.Models.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ux_design.Models.Patient;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface PatientDAO {

    @Query("SELECT * FROM Patient")
    LiveData<List<Patient>> getAll();

    @Query("SELECT * FROM Patient WHERE email LIKE :email")
    Single<Patient> findByEmail(String email);

//    @Query("SELECT * FROM Pays")
//    LiveData<List<String>> getStringList();

    @Insert
    void insertAll(Patient... pays);

    @Delete
    void delete(Patient pays);
}
