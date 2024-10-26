package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Entite.Evaluation;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.EvaluationDao;

public class AjoutEvaluation extends AppCompatActivity {

    private EditText noteExamenEditText;
    private EditText noteCcEditText;
    private EditText remarqueEditText;
    private Button ajouterEvaluationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_evaluation);

        // Initialize views
        noteExamenEditText = findViewById(R.id.editTextText2);
        noteCcEditText = findViewById(R.id.editTextText3);
        remarqueEditText = findViewById(R.id.textInputLayout);
        ajouterEvaluationButton = findViewById(R.id.button);

        // Add click listener to the "Ajouter" button
        ajouterEvaluationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from EditText fields
                String noteExamen = noteExamenEditText.getText().toString();
                String noteCc = noteCcEditText.getText().toString();
                String remarque = remarqueEditText.getText().toString();

                // Create an Evaluation object
                Evaluation evaluation = new Evaluation(noteExamen, noteCc, remarque);

                // Use Kotlin Coroutines to insert the Evaluation object on a background thread
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        EvaluationDao evaluationDao = MyDatabase.getInstance(AjoutEvaluation.this).evaluationDao();
                        evaluationDao.insert(evaluation);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        // Provide user feedback for successful insertion
                        Toast.makeText(AjoutEvaluation.this, "Évaluation ajoutée avec succès", Toast.LENGTH_SHORT).show();
                    }
                }.execute();
            }
        });
    }
}
