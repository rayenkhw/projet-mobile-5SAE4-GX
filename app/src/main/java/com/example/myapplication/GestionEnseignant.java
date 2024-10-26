package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GestionEnseignant extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_enseignant);

        androidx.cardview.widget.CardView gestionEnseignantCard = findViewById(R.id.GestionEnseignantCard);
        androidx.cardview.widget.CardView consulterEnseignantCard = findViewById(R.id.consulterEnseignantCard);
        androidx.cardview.widget.CardView addNoteCard = findViewById(R.id.addNoteCard);
        androidx.cardview.widget.CardView listNotesCard = findViewById(R.id.listNotesCard);
        ImageView backButton = findViewById(R.id.backbutton);


        gestionEnseignantCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionEnseignant.this, AjoutEnseignant.class);
                startActivity(intent);
            }
        });

        consulterEnseignantCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionEnseignant.this, ListeEnseignants.class);
                startActivity(intent);
            }
        });

        addNoteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionEnseignant.this, AjoutEvaluation.class);
                startActivity(intent);
            }
        });

        listNotesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionEnseignant.this, ListeEvaluations.class);
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
