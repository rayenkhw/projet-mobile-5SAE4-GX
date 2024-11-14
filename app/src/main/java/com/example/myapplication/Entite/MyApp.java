package com.example.myapplication.Entite;

import android.app.Application;

import androidx.room.Room;

import com.facebook.stetho.Stetho;

public class MyApp extends Application {
    private MyDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "ProjetMobile").build();
        Stetho.initializeWithDefaults(this);
    }

    public MyDatabase getAppDatabase() {
        return appDatabase;
    }

}
