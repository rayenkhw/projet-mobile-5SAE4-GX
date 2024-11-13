package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.Entite.Classe;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.ClasseDao;

import java.util.ArrayList;
import java.util.List;

public class ListeClasse extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClasseAdapter classeAdapter;
    private List<Classe> classes = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_classe);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.classeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize and set the adapter
        // Initialize and set the adapter
        classeAdapter = new ClasseAdapter(new ArrayList<>()); // Pass a non-null list here


        // Set item click listener
        classeAdapter.setOnItemClickListener(new ClasseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (classes != null && position < classes.size()) {
                    Intent intent = new Intent(ListeClasse.this, ModifierEnseignant.class);
                    intent.putExtra("CLASSE_ID", classes.get(position).getId());
                    startActivity(intent);
                } else {
                    // Log or show a message indicating that the list is null or the position is invalid
                }
            }
        });


        recyclerView.setAdapter(classeAdapter);

        // Load the list of enseignants from your Room database
        loadClasses();
    }
    private void loadClasses() {
        // Use a background thread to retrieve the list of enseignants
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Retrieve the list of enseignants from the Room database
                ClasseDao classeDao = MyDatabase.getInstance(ListeClasse.this).classeDao();
                final List<Classe> classes = classeDao.getAllClasses();

                // Update the adapter with the list of enseignants on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        classeAdapter.setClasses(classes);//setEnseignants(enseignants);
                    }
                });
            }
        }).start();
    }
}