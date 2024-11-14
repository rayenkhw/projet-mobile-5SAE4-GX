package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Entite.Enseignant;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.EnseignantDao;

public class EnseignantDetail extends AppCompatActivity {

    private EnseignantDao enseignantDao;
    private long enseignantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enseignant_detail);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("ENSEIGNANT_ID")) {
            enseignantId = intent.getIntExtra("ENSEIGNANT_ID", -1);
        }

        // Initialize the database instance
        enseignantDao = MyDatabase.getInstance(this).enseignantDao();

        // Perform database operations in an AsyncTask
        new LoadEnseignantTask().execute();

        // Assuming you have a "Modifier" button that triggers the update
        findViewById(R.id.buttonModifier).setOnClickListener(v -> updateEnseignantData());

        Button deleteButton = findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete button click
                new DeleteEnseignantTask().execute();
            }
        });

    }


    private void updateEnseignantData() {
        EditText nomEditText = findViewById(R.id.nomEditText);
        EditText prenomEditText = findViewById(R.id.prenomEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText matiereEditText = findViewById(R.id.matiereEditText);

        // Assuming you have retrieved the updated values from your EditText fields
        String updatedNom = nomEditText.getText().toString();
        String updatedPrenom = prenomEditText.getText().toString();
        String updatedEmail = emailEditText.getText().toString();
        String updatedMatiere = matiereEditText.getText().toString();

// Create an updated Enseignant object with the new values
        Enseignant updatedEnseignant = new Enseignant();
        updatedEnseignant.setId((int) enseignantId);
        updatedEnseignant.setNom(updatedNom);
        updatedEnseignant.setPrenom(updatedPrenom);
        updatedEnseignant.setEmail(updatedEmail);
        updatedEnseignant.setMatiere(updatedMatiere);

// Perform the update in the background thread
        new UpdateEnseignantTask().execute(updatedEnseignant);

    }




    private class LoadEnseignantTask extends AsyncTask<Void, Void, Enseignant> {

        @Override
        protected Enseignant doInBackground(Void... voids) {
            // Perform database query on a background thread
            return enseignantDao.getEnseignantById(enseignantId);
        }

        @Override
        protected void onPostExecute(Enseignant enseignant) {
            super.onPostExecute(enseignant);

            // Update UI with the retrieved enseignant data
            if (enseignant != null) {
                // Update your UI components here
                EditText nomEditText = findViewById(R.id.nomEditText);
                EditText prenomEditText = findViewById(R.id.prenomEditText);
                EditText emailEditText = findViewById(R.id.emailEditText);
                EditText matiereEditText = findViewById(R.id.matiereEditText);

                nomEditText.setText(enseignant.getNom());
                prenomEditText.setText(enseignant.getPrenom());
                emailEditText.setText(enseignant.getEmail());
                matiereEditText.setText(enseignant.getMatiere());
            }
        }
    }


    private class UpdateEnseignantTask extends AsyncTask<Enseignant, Void, Void> {

        @Override
        protected Void doInBackground(Enseignant... enseignants) {
            // Perform database update on a background thread
            enseignantDao.update(enseignants[0]);
            return null;
        } @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // Redirect back to ListeEnseignants activity
            Intent intent = new Intent(EnseignantDetail.this, ListeEnseignants.class);
            startActivity(intent);
            finish(); // Finish the current activity to prevent going back to it with outdated data
        }}
    private class DeleteEnseignantTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            // Perform database delete operation on a background thread
            Enseignant enseignantToDelete = enseignantDao.getEnseignantById(enseignantId);
            if (enseignantToDelete != null) {
                enseignantDao.delete(enseignantToDelete);
            }
            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // Redirect back to ListeEnseignants activity
            Intent intent = new Intent(EnseignantDetail.this, ListeEnseignants.class);
            startActivity(intent);
            finish(); // Finish the current activity to prevent going back to it with outdated data
        }
    }
}