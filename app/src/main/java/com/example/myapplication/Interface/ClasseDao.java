package com.example.myapplication.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Entite.Classe;

import java.util.List;

@Dao
public interface ClasseDao {
    @Insert
    void insert(Classe classe);

    @Update
    void update(Classe classe);

    @Delete
    void delete(Classe classe);

    @Query("SELECT * FROM Classe")
    List<Classe> getAllClasses();

    @Query("SELECT * FROM Classe  WHERE nom = :nomClasse")
    Classe getClasseByName(String nomClasse);
}
