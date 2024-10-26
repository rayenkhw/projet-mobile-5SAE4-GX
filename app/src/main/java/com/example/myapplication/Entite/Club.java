package com.example.myapplication.Entite;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

@Entity(tableName = "Club")
public class Club {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "nom")
    public String nom;

    @ColumnInfo(name = "president")
    public String president;
    @ColumnInfo(name = "vicep")
    public String vicep;

    @ColumnInfo(name = "description")
    public String description;


    @Ignore
    public Club() {
        // Constructeur sans arguments annoté avec @Ignore
        // Room ignorera ce constructeur lors de la création de la base de données
    }


    public Club(long id, String nom, String president, String vicep, String description) {
        this.id = id;
        this.nom = nom;
        this.president = president;
        this.vicep = vicep;
        this.description = description;
    }
//getter and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public String getVicep() {
        return vicep;
    }

    public void setVicep(String vicep) {
        this.vicep = vicep;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
