package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Entite.Evaluation;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.EvaluationDao;

public class EvaluationDetail extends AppCompatActivity {

    private EvaluationDao evaluationDao;
    private long evaluationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_detail);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("EVALUATION_ID")) {
            // Use getLongExtra instead of getIntExtra
            evaluationId = intent.getLongExtra("EVALUATION_ID", -1);
        }

        // Initialize the database instance
        evaluationDao = MyDatabase.getInstance(this).evaluationDao();

        // Perform database operations in an AsyncTask
        new LoadEvaluationTask().execute();

        // Assuming you have a "Modifier" button that triggers the update
        findViewById(R.id.buttonModifier1).setOnClickListener(v -> updateEvaluationData());

        Button deleteButton = findViewById(R.id.buttonDelete1);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete button click
                new DeleteEvaluationTask().execute();
            }
        });
    }

    private void updateEvaluationData() {
        EditText noteExamenEditText = findViewById(R.id.noteExamenTextView);
        EditText noteCcEditText = findViewById(R.id.noteCcTextView);
        EditText remarqueEditText = findViewById(R.id.remarqueTextView);

        // Retrieve values from EditText fields
        String updatedNoteExamen = noteExamenEditText.getText().toString();
        String updatedNoteCc = noteCcEditText.getText().toString();
        String updatedRemarque = remarqueEditText.getText().toString();

        // Create an updated Evaluation object with the new values
        Evaluation updatedEvaluation = new Evaluation();
        updatedEvaluation.setId((int) evaluationId);
        updatedEvaluation.setNoteExamen(updatedNoteExamen);
        updatedEvaluation.setNoteCc(updatedNoteCc);
        updatedEvaluation.setRemarque(updatedRemarque);

        // Perform the update in the background thread
        new UpdateEvaluationTask().execute(updatedEvaluation);
    }

    private class LoadEvaluationTask extends AsyncTask<Void, Void, Evaluation> {

        @Override
        protected Evaluation doInBackground(Void... voids) {
            // Perform database query on a background thread
            return evaluationDao.getEvaluationById(evaluationId);
        }

        @Override
        protected void onPostExecute(Evaluation evaluation) {
            super.onPostExecute(evaluation);

            // Update UI with the retrieved evaluation data
            if (evaluation != null) {
                // Update your UI components here
                EditText noteExamenEditText = findViewById(R.id.noteExamenTextView);
                EditText noteCcEditText = findViewById(R.id.noteCcTextView);
                EditText remarqueEditText = findViewById(R.id.remarqueTextView);

                noteExamenEditText.setText(evaluation.getNoteExamen());
                noteCcEditText.setText(evaluation.getNoteCc());
                remarqueEditText.setText(evaluation.getRemarque());
            }
        }
    }

    private class UpdateEvaluationTask extends AsyncTask<Evaluation, Void, Void> {

        @Override
        protected Void doInBackground(Evaluation... evaluations) {
            // Perform database update on a background thread
            evaluationDao.update(evaluations[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // Redirect back to ListeEvaluations activity
            Intent intent = new Intent(EvaluationDetail.this, ListeEvaluations.class);
            startActivity(intent);
            finish(); // Finish the current activity to prevent going back to it with outdated data
        }
    }

    private class DeleteEvaluationTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            // Perform database delete operation on a background thread
            Evaluation evaluationToDelete = evaluationDao.getEvaluationById(evaluationId);
            if (evaluationToDelete != null) {
                evaluationDao.delete(evaluationToDelete);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // Redirect back to ListeEvaluations activity
            Intent intent = new Intent(EvaluationDetail.this, ListeEvaluations.class);
            startActivity(intent);
            finish(); // Finish the current activity to prevent going back to it with outdated data
        }
    }
}
