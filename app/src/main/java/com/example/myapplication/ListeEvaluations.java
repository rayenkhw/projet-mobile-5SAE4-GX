package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entite.Evaluation;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.EvaluationDao;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListeEvaluations extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EvaluationAdapter evaluationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_evaluations);

        recyclerView = findViewById(R.id.evaluationsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize and set the adapter
        evaluationAdapter = new EvaluationAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(evaluationAdapter);

        // Set item click listener for the RecyclerView
        evaluationAdapter.setOnItemClickListener(new EvaluationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click, e.g., navigate to the detail view
                Evaluation clickedEvaluation = evaluationAdapter.getEvaluation(position);

                // Assuming you have an EvaluationDetail activity
                Intent intent = new Intent(ListeEvaluations.this, EvaluationDetail.class);
                intent.putExtra("EVALUATION_ID", clickedEvaluation.getId());
                startActivity(intent);
            }
        });

        // Load the list of evaluations from your Room database
        loadEvaluations();

        // Set click listener for the download PDF button
        Button downloadButton = findViewById(R.id.buttonDownloadPDF);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadPDF();
            }
        });
    }

    private void loadEvaluations() {
        // Use a background thread to retrieve the list of evaluations
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Retrieve the list of evaluations from the Room database
                EvaluationDao evaluationDao = MyDatabase.getInstance(ListeEvaluations.this).evaluationDao();
                final List<Evaluation> evaluations = evaluationDao.getAllEvaluations();

                // Update the adapter with the list of evaluations on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        evaluationAdapter.setEvaluations(evaluations);
                    }
                });
            }
        }).start();
    }

    // Method to handle the download PDF button click
    private void downloadPDF() {
        Toast.makeText(this, "Generating PDF...", Toast.LENGTH_SHORT).show();
        //  generation in the background
        new GeneratePdfTask().execute();
                               }

    private class GeneratePdfTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            return generatePDFContent();
        }

        @Override
        protected void onPostExecute(String pdfContent) {
            // Create a PDF file
            File pdfFile = new File(getExternalFilesDir(null), "evaluations.pdf");

            try {
                PdfWriter writer = new PdfWriter(new FileOutputStream(pdfFile));
                PdfDocument pdfDocument = new PdfDocument(writer);
                com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);

                // Add content to the PDF document
                document.add(new com.itextpdf.layout.element.Paragraph(pdfContent));

                document.close();

                Toast.makeText(ListeEvaluations.this, "PDF Downloaded", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(ListeEvaluations.this, "Error downloading PDF", Toast.LENGTH_SHORT).show();
                                    }
                                                            }
                                                                              }

    private String generatePDFContent() {


        StringBuilder content = new StringBuilder();
        List<Evaluation> evaluations = evaluationAdapter.evaluations;

        for (Evaluation evaluation : evaluations) {
            // Append evaluation details to the content string
            content.append("Evaluation ID: ").append(evaluation.getId()).append("\n");
            content.append("Note Examen: ").append(evaluation.getNoteExamen()).append("\n");
            content.append("Note CC: ").append(evaluation.getNoteCc()).append("\n");
            content.append("remarque: ").append(evaluation.getRemarque()).append("\n");

            content.append("\n");
        }

        return content.toString();
    }


    private void saveToFile(String fileName, String content) {
        try {
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(directory, fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
