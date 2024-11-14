package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GestionEtudiant extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_etudiant);

        androidx.cardview.widget.CardView gestionEtudiantCard = findViewById(R.id.GestionEtudiantCard);
        androidx.cardview.widget.CardView consulterEtudiantCard = findViewById(R.id.ConsulterEtudiantCard);
        ImageView backButton = findViewById(R.id.backbutton);


        gestionEtudiantCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionEtudiant.this, AjoutEtudiant.class);
                startActivity(intent);
            }
        });

        consulterEtudiantCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionEtudiant.this, ListeEtudiants.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event (e.g., navigate back to the previous page)
                onBackPressed();
            }
        });

    }
}
