package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GestionClub extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_club);

        androidx.cardview.widget.CardView gestionClubCard = findViewById(R.id.GestionClubCard);
        androidx.cardview.widget.CardView consulterClubCard = findViewById(R.id.consulterClubCard);
        androidx.cardview.widget.CardView listEvenCard = findViewById(R.id.listEvenCard);
        androidx.cardview.widget.CardView addEvenCard = findViewById(R.id.addEvenCard);
        ImageView backButton = findViewById(R.id.backbutton);


        gestionClubCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionClub.this, AjoutClub.class);
                startActivity(intent);
            }
        });

        consulterClubCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionClub.this, ListeClubs.class);
                startActivity(intent);
            }
        });
        addEvenCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionClub.this, AjoutEvenement.class);
                startActivity(intent);
            }
        });

        listEvenCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionClub.this, ListeEvenement.class);
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
