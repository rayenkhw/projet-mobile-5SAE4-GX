package com.example.myapplication.Entite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Etudiant  {

    @PrimaryKey(autoGenerate = true)
    private int id;


    public String nom;

    public String prenom;


    public String email;


    public String niveau;


    public String identifiant;


   // public long classeId;

    public Etudiant() {
    }


    public Etudiant(String nom, String prenom, String email, String niveau, String identifiant) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.niveau = niveau;
        this.identifiant = identifiant;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }


}
