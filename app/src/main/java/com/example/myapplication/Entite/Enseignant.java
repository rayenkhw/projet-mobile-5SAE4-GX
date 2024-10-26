package com.example.myapplication.Entite;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Enseignant {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nom;
    private String prenom;
    private String email;
    private String matiere;
    private String identifiant;

    // Constructors
    public Enseignant() {
        // Default constructor
    }

    public Enseignant(String nom, String prenom, String email, String matiere, String identifiant) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.matiere = matiere;
        this.identifiant = identifiant;
    }

    // Getters and setters
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

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }
}
