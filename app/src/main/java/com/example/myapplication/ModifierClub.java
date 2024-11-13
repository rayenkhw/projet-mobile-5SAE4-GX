package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import com.example.myapplication.Entite.Club;
import com.example.myapplication.Interface.ClubDao;
import com.example.myapplication.Entite.MyDatabase;

public class ModifierClub extends AppCompatActivity {

    private EditText nomEditText;
    private EditText presidentEditText;
    private EditText vicepEditText;
    private EditText descriptionEditText;
    private Button saveButton;

    private ClubDao clubDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_club);

        // Initialiser les éléments de l'UI
        nomEditText = findViewById(R.id.nomEditText);
        presidentEditText = findViewById(R.id.presidentEditText);
        vicepEditText = findViewById(R.id.vicepEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        saveButton = findViewById(R.id.saveButton);

        // Récupérer les données passées via l'Intent
        long clubId = getIntent().getLongExtra("CLUB_ID", -1);
        String clubName = getIntent().getStringExtra("CLUB_NAME");
        String clubPresident = getIntent().getStringExtra("CLUB_PRESIDENT");
        String clubVicep = getIntent().getStringExtra("CLUB_VICEP");
        String clubDescription = getIntent().getStringExtra("CLUB_DESCRIPTION");

        if (clubId == -1) {
            Toast.makeText(this, "Erreur : ID du club non valide", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        clubDao = MyDatabase.getInstance(this).clubDao();

        // Pré-remplir les champs avec les données du club
        nomEditText.setText(clubName);
        presidentEditText.setText(clubPresident);
        vicepEditText.setText(clubVicep);
        descriptionEditText.setText(clubDescription);

        // Ajouter la logique de sauvegarde avec gestion des erreurs
        saveButton.setOnClickListener(v -> saveClub(clubId));
    }

    private void saveClub(long clubId) {
        Club updatedClub = new Club(
                clubId,
                nomEditText.getText().toString(),
                presidentEditText.getText().toString(),
                vicepEditText.getText().toString(),
                descriptionEditText.getText().toString()
        );

        if (updatedClub.getNom().isEmpty() || updatedClub.getPresident().isEmpty() || updatedClub.getVicep().isEmpty() || updatedClub.getDescription().isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            try {
                clubDao.update(updatedClub);
                runOnUiThread(() -> {
                    Toast.makeText(ModifierClub.this, "Club mis à jour", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK, new Intent().putExtra("CLUB_UPDATED", true));
                    finish();
                });
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(ModifierClub.this, "Erreur lors de la mise à jour : " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}
