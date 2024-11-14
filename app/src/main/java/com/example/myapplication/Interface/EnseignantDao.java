package com.example.myapplication.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Entite.Enseignant;

import java.util.List;

@Dao
public interface EnseignantDao {
    @Insert
    void insert(Enseignant enseignant);

    @Update
    void update(Enseignant enseignant);

    @Delete
    void delete(Enseignant enseignant);

    @Query("SELECT * FROM Enseignant")
    List<Enseignant> getAllEnseignants();

    @Query("SELECT * FROM Enseignant WHERE id = :id")
    Enseignant getEnseignantById(long id);
}

