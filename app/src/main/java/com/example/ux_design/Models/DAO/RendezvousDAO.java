package com.example.ux_design.Models.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.ux_design.Models.Rendezvous;
import com.example.ux_design.Models.tupleRDVPatient;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface RendezvousDAO {

    @Query("SELECT * FROM Rendezvous")
    LiveData<List<Rendezvous>> getAll();

    @Query("SELECT * FROM Rendezvous WHERE daterdv LIKE :date AND emailMedecin LIKE :email")
    Single<Rendezvous> findByDateEmail(String date,String email);

    @Query("SELECT * FROM Rendezvous WHERE daterdv LIKE :date AND emailMedecin LIKE :email")
    Single<List<Rendezvous>> findListByDateEmail(String date,String email);

    @Query("SELECT p.nom,p.adresse,r.daterdv FROM Rendezvous r JOIN Patient p ON r.emailPatient=p.email WHERE daterdv LIKE :date AND emailMedecin LIKE :email  ")
    Single<tupleRDVPatient> findListByDateEmailMedecin(String date, String email);

    @Query("SELECT m.nom,m.adresse,r.daterdv FROM Rendezvous r JOIN Medecin m ON r.emailMedecin=m.email WHERE daterdv LIKE '%' || :date || '%' AND emailPatient LIKE :email  ")
    Single<List<tupleRDVPatient>> findListByDateEmailPatient(String date, String email);

    @Insert
    void insertAll(Rendezvous... rendezvous);

    @Delete
    void delete(Rendezvous rendezvous);
}
