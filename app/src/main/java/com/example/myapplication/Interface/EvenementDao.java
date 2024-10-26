package com.example.myapplication.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Entite.Evenement;

import java.util.List;

@Dao
public interface EvenementDao {

    @Insert
    void insert(Evenement evenement);

    @Update
    void update(Evenement evenement);

    @Delete
    void delete(Evenement evenement);

    @Query("SELECT * FROM Evenements")
    List<Evenement> getAllEvenements();

}
