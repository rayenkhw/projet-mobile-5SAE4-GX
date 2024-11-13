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
        setContentView(R.layout.activity_ajout_classe);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.clubRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                }
            }

            @Override
            public void onDeleteClick(int position) {
                if (classes != null && position < classes.size()) {
                    Classe classeToDelete = classes.get(position);
                    ClasseDao classeDao = MyDatabase.getInstance(ListeClasse.this).classeDao();

                    // Exécution de la suppression dans un thread secondaire
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // Supprimer la classe dans la base de données
                            classeDao.delete(classeToDelete);

                            // Une fois l'opération terminée, mettre à jour la liste sur le thread principal
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Retirer la classe de la liste et mettre à jour l'adaptateur
                                    classes.remove(position);
                                    classeAdapter.setClasses(classes); // Mettre à jour la liste dans l'adaptateur
                                }
                            });
                        }
                    }).start();
                }
            }
        });

        recyclerView.setAdapter(classeAdapter);

        // Load the list of classes from your Room database
        loadClasses();
    }

    private void loadClasses() {
        // Use a background thread to retrieve the list of classes
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Retrieve the list of classes from the Room database
                ClasseDao classeDao = MyDatabase.getInstance(ListeClasse.this).classeDao();
                final List<Classe> classes = classeDao.getAllClasses();

                // Update the adapter with the list of classes on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        classeAdapter.setClasses(classes);
                    }
                });
            }
        }).start();
    }
}
