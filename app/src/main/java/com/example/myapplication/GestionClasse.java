package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GestionClasse extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_classe);

        androidx.cardview.widget.CardView gestionClasseCard = findViewById(R.id.GestionClasseCard);
        androidx.cardview.widget.CardView consulterClasseCard = findViewById(R.id.ConsulterClasseCard);
        ImageView backButton = findViewById(R.id.backbutton);


        gestionClasseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionClasse.this, AjoutClasse.class);
                startActivity(intent);
            }
        });

        consulterClasseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionClasse.this, ListeClasse.class);
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
