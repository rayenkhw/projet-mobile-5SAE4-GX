package com.example.myapplication.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Entite.Evaluation;

import java.util.List;

@Dao
public interface EvaluationDao {

    @Insert
    void insert(Evaluation evaluation);

    @Update
    void update(Evaluation evaluation);

    @Delete
    void delete(Evaluation evaluation);

    @Query("SELECT * FROM Evaluation")
    List<Evaluation> getAllEvaluations();

    @Query("SELECT * FROM Evaluation WHERE id = :id")
    Evaluation getEvaluationById(long id);
}
