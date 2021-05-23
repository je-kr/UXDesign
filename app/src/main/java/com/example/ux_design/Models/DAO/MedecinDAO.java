package com.example.ux_design.Models.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import com.example.ux_design.Models.Medecin;

import io.reactivex.Single;

@Dao
public interface  MedecinDAO {

    @Query("SELECT * FROM Medecin")
    LiveData<List<Medecin>> getAll();

    @Query("SELECT * FROM Medecin WHERE email LIKE :email")
    Single<Medecin> findByEmail(String email);

    @Query("SELECT * FROM Medecin WHERE adresse LIKE '%' || :cp || '%'")
    Single<List<Medecin>> findByCP(String cp);

//    @Query("SELECT * FROM Pays")
//    LiveData<List<String>> getStringList();

    @Insert
    void insertAll(Medecin... pays);

    @Delete
    void delete(Medecin pays);

}