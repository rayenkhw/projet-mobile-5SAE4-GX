package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Entite.Classe;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.ClasseDao;


public class ClasseModification extends AppCompatActivity {
    private ClasseDao classeDao;
    private long classeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_classe);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("CLASSE_ID")) {
            classeId = intent.getLongExtra("CLASSE_ID", -1);
        }

        // Initialize the database instance
        classeDao = MyDatabase.getInstance(this).classeDao();

        // Perform database operations in an AsyncTask
        new LoadClasseTask().execute();

        findViewById(R.id.buttonModifier).setOnClickListener(v -> updateClasseData());

        Button deleteButton = findViewById(R.id.buttonSupprimer);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete button click
                new DeleteClasseTask().execute();
            }
        });

    }

    private void updateClasseData() {
        EditText nomEditText = findViewById(R.id.nomEditText);
        EditText specialiteEditText = findViewById(R.id.specialiteEditText);
        EditText numeroEditText = findViewById(R.id.numeroEditText);  // Correction ici

        String updatedNom = nomEditText.getText().toString();
        String updatedSpecialite = specialiteEditText.getText().toString();
        String updatedNumero = numeroEditText.getText().toString();  // Correction ici

        Classe updatedClasse = new Classe();
        updatedClasse.setId(classeId);  // Assurez-vous de définir l'ID ici
        updatedClasse.setNom(updatedNom);
        updatedClasse.setSpecialite(updatedSpecialite);
        updatedClasse.setNumero(updatedNumero);

        new UpdateClasseTask().execute(updatedClasse);
    }


    private class LoadClasseTask extends AsyncTask<Void, Void, Classe> {

        @Override
        protected Classe doInBackground(Void... voids) {
            // Perform database query on a background thread
            return classeDao.getClasseById(classeId);
        }

        @Override
        protected void onPostExecute(Classe classe) {
            super.onPostExecute(classe);

            // Update UI with the retrieved enseignant data
            if (classe != null) {
                // Update your UI components here
                EditText nomEditText = findViewById(R.id.nomEditText);
                EditText specialiteEditText = findViewById(R.id.specialiteEditText);
                EditText numeroEditText = findViewById(R.id.numeroEditText);

                // Remplir les champs EditText avec les valeurs de l'objet Classe
                nomEditText.setText(classe.getNom());
                specialiteEditText.setText(classe.getSpecialite());
                numeroEditText.setText(classe.getNumero());
            }
        }
    }

    private class UpdateClasseTask extends AsyncTask<Classe, Void, Void> {

        @Override
        protected Void doInBackground(Classe... classes) {
            // Perform database update on a background thread
            classeDao.update(classes[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(ClasseModification.this, ListeClasse.class);
            startActivity(intent);
            finish(); // Finish the current activity to prevent going back to it with outdated data
        }
    }

    private class DeleteClasseTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Classe classeToDelete = classeDao.getClasseById(classeId);
            if (classeToDelete != null) {
                classeDao.delete(classeToDelete);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(ClasseModification.this, ListeClasse.class);
            startActivity(intent);
            finish();
        }
    }




    }


