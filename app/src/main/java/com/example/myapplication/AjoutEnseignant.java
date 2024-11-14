package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Entite.Enseignant;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.EnseignantDao;

public class AjoutEnseignant extends AppCompatActivity {

    private EditText nomEditText;
    private EditText prenomEditText;
    private EditText emailEditText;
    private EditText matiereEditText;
    private EditText identifiantEditText;
    private Button ajouterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_enseignant);

        // Initialize views
        nomEditText = findViewById(R.id.editTextText2);
        prenomEditText = findViewById(R.id.editTextText3);
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        matiereEditText = findViewById(R.id.editTextPhone);
        identifiantEditText = findViewById(R.id.editTextDate);
        ajouterButton = findViewById(R.id.button);

        // Add click listener to the "Ajouter" button
        ajouterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from EditText fields
                String nom = nomEditText.getText().toString();
                String prenom = prenomEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String matiere = matiereEditText.getText().toString();
                String identifiant = identifiantEditText.getText().toString();

                // Create an Enseignant object
                Enseignant enseignant = new Enseignant();
                enseignant.setNom(nom);
                enseignant.setPrenom(prenom);
                enseignant.setEmail(email);
                enseignant.setMatiere(matiere);
                enseignant.setIdentifiant(identifiant);

                    // Use Kotlin Coroutines to insert the Enseignant object on a background thread
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        EnseignantDao enseignantDao = MyDatabase.getInstance(AjoutEnseignant.this).enseignantDao();
                        enseignantDao.insert(enseignant);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        // Provide user feedback for successful insertion
                        Toast.makeText(AjoutEnseignant.this, "Enseignant ajouté avec succès", Toast.LENGTH_SHORT).show();
                    }
                }.execute();
            }
        });


    }
}
