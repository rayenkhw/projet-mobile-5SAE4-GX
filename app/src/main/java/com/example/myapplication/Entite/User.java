package com.example.myapplication.Entite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "nom")
    public String nom;

    @ColumnInfo(name = "prenom")
    public String prenom;

    @ColumnInfo(name = "role")
    public String role;

    @ColumnInfo(name = "mail")
    public String mail;

    @ColumnInfo(name = "identifiant")
    public String identifiant;

    @Ignore
    public User() {
        // Constructeur sans arguments annoté avec @Ignore
        // Room ignorera ce constructeur lors de la création de la base de données
    }


    public User(Long id,String nom, String prenom, String role, String mail, String identifiant) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.mail = mail;
        this.identifiant = identifiant;
    }

}
