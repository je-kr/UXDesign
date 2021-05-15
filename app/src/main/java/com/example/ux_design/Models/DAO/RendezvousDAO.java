package com.example.ux_design.Models.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.ux_design.Models.Rendezvous;

import java.util.List;

import io.reactivex.Single;

public interface RendezvousDAO {

    @Query("SELECT * FROM Rendezvous")
    LiveData<List<Rendezvous>> getAll();

    @Query("SELECT * FROM Rendezvous WHERE daterdv LIKE :date")
    Single<List<Rendezvous>> findByDate(String date);

//    @Query("SELECT * FROM Pays")
//    LiveData<List<String>> getStringList();

    @Insert
    void insertAll(Rendezvous... pays);

    @Delete
    void delete(Rendezvous pays);
}
