package com.example.myapplication.Entite;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.myapplication.Interface.ClasseDao;
import com.example.myapplication.Interface.ClubDao;
import com.example.myapplication.Interface.EnseignantDao;
import com.example.myapplication.Interface.EtudiantDao;
import com.example.myapplication.Interface.EvaluationDao;
import com.example.myapplication.Interface.ClubDao;
import com.example.myapplication.Interface.EvenementDao;

@Database(entities = {Enseignant.class, Evaluation.class, Classe.class,Club.class, Evenement.class, Etudiant.class}, version = 2)

public abstract class MyDatabase extends RoomDatabase {
    public abstract EnseignantDao enseignantDao();
    public abstract EvaluationDao evaluationDao();

     public abstract ClubDao clubDao();
    public abstract ClasseDao classeDao();

     public abstract EtudiantDao etudiantDao();

    private static MyDatabase instance;

    public static synchronized MyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "my_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }



    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };



}