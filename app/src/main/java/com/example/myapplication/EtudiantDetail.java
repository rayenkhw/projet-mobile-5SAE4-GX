package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.Entite.Etudiant;
import com.example.myapplication.Entite.MyDatabase;

import com.example.myapplication.Interface.EtudiantDao;

public class EtudiantDetail extends  AppCompatActivity{

        private EtudiantDao etudiantDao;
        private long etudiantId;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_etudiant_detail);

            Intent intent = getIntent();
            if (intent != null && intent.hasExtra("ETUDIANT_ID")) {
                etudiantId = intent.getIntExtra("ETUDIANT_ID", -1);
            }

            // Initialize the database instance
            etudiantDao = MyDatabase.getInstance(this).etudiantDao();

            // Perform database operations in an AsyncTask
            new LoadEtudiantTask().execute();

            // Assuming you have a "Modifier" button that triggers the update
            findViewById(R.id.buttonModifier).setOnClickListener(v -> updateEtudiantData());

            Button deleteButton = findViewById(R.id.buttonDelete);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle delete button click
                    new DeleteEtudiantTask().execute();
                }
            });

        }


        private void updateEtudiantData() {
            EditText nomEditText = findViewById(R.id.nomEditText);
            EditText prenomEditText = findViewById(R.id.prenomEditText);
            EditText emailEditText = findViewById(R.id.emailEditText);
            EditText niveauEditText = findViewById(R.id.niveauEditText);

            // Assuming you have retrieved the updated values from your EditText fields
            String updatedNom = nomEditText.getText().toString();
            String updatedPrenom = prenomEditText.getText().toString();
            String updatedEmail = emailEditText.getText().toString();
            String updatedNiveau = niveauEditText.getText().toString();

// Create an updated Enseignant object with the new values
            Etudiant updatedEtudiant = new Etudiant();
            updatedEtudiant.setId((int) etudiantId);
            updatedEtudiant.setNom(updatedNom);
            updatedEtudiant.setPrenom(updatedPrenom);
            updatedEtudiant.setEmail(updatedEmail);
            updatedEtudiant.setNiveau(updatedNiveau);

// Perform the update in the background thread
            new UpdateEtudiantTask().execute(updatedEtudiant);

        }




        private class LoadEtudiantTask extends AsyncTask<Void, Void, Etudiant> {

            @Override
            protected Etudiant doInBackground(Void... voids) {
                // Perform database query on a background thread
                return etudiantDao.getEtudiantById(etudiantId);
            }

            @Override
            protected void onPostExecute(Etudiant etudiant) {
                super.onPostExecute(etudiant);

                // Update UI with the retrieved enseignant data
                if (etudiant != null) {
                    // Update your UI components here
                    EditText nomEditText = findViewById(R.id.nomEditText);
                    EditText prenomEditText = findViewById(R.id.prenomEditText);
                    EditText emailEditText = findViewById(R.id.emailEditText);
                    EditText niveauEditText = findViewById(R.id.niveauEditText);

                    nomEditText.setText(etudiant.getNom());
                    prenomEditText.setText(etudiant.getPrenom());
                    emailEditText.setText(etudiant.getEmail());
                    niveauEditText.setText(etudiant.getNiveau());
                }
            }
        }


        private class UpdateEtudiantTask extends AsyncTask<Etudiant, Void, Void> {

            @Override
            protected Void doInBackground(Etudiant... etudiants) {
                // Perform database update on a background thread
                etudiantDao.update(etudiants[0]);
                return null;
            } @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                // Redirect back to ListeEnseignants activity
                Intent intent = new Intent(com.example.myapplication.EtudiantDetail.this, ListeEtudiants.class);
                startActivity(intent);
                finish(); // Finish the current activity to prevent going back to it with outdated data
            }}
        private class DeleteEtudiantTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                // Perform database delete operation on a background thread
                Etudiant etudiantToDelete = etudiantDao.getEtudiantById(etudiantId);
                if (etudiantToDelete != null) {
                    etudiantDao.delete(etudiantToDelete);
                }
                return null;
            }



            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                // Redirect back to ListeEnseignants activity
                Intent intent = new Intent(com.example.myapplication.EtudiantDetail.this, ListeEnseignants.class);
                startActivity(intent);
                finish(); // Finish the current activity to prevent going back to it with outdated data
            }
        }
    }

