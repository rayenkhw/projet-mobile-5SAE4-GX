package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AccueilPrincipal extends AppCompatActivity {
    CardView StudentCard;
    CardView clubCard;
    CardView teacherCard;
    CardView classeCard;
    Button logoutButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_principal);

        logoutButton = findViewById(R.id.logoutButton);

        StudentCard=findViewById(R.id.StudentCard);
        StudentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilPrincipal.this,GestionEtudiant.class);
                startActivity(intent);
            }
        });
        clubCard=findViewById(R.id.clubCard);
        clubCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilPrincipal.this,GestionClub.class);
                startActivity(intent);
            }
        });
        teacherCard=findViewById(R.id.teacherCard);
        teacherCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AccueilPrincipal.this, GestionEnseignant.class);
                startActivity(intent);
            }
        });
        classeCard=findViewById(R.id.classeCard);
        classeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AccueilPrincipal.this, GestionClasse.class);
                startActivity(intent);
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event (e.g., navigate to MainActivity)
                Intent intent = new Intent(AccueilPrincipal.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}