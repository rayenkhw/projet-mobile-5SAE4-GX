package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Entite.Etudiant;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.EtudiantDao;

import java.util.ArrayList;
import java.util.List;

public class ListeEtudiants extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EtudiantAdapter etudiantAdapter;

    private List<Etudiant> etudiants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_etudiants);

        recyclerView = findViewById(R.id.etudiantRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        etudiantAdapter = new EtudiantAdapter(new ArrayList<>());
        // Set item click listener
        etudiantAdapter.setOnItemClickListener(new EtudiantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (etudiants != null && position < etudiants.size()) {
                    Intent intent = new Intent(ListeEtudiants.this, ModifierEnseignant.class);
                    intent.putExtra("ETUDIANT_ID", etudiants.get(position).getId());
                    startActivity(intent);
                } else {
                    // Log or show a message indicating that the list is null or the position is invalid
                }
            }
        });


        recyclerView.setAdapter(etudiantAdapter);

        // Load the list of enseignants from your Room database
        loadEtudiant();
    }

    private void loadEtudiant() {
        // Use a background thread to retrieve the list of enseignants
        new Thread(new Runnable() {
            @Override
            public void run() {

                EtudiantDao etudiantDao = MyDatabase.getInstance(ListeEtudiants.this).etudiantDao();
                final List<Etudiant> etudiants = etudiantDao.getAllEtudiant();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        etudiantAdapter.setEtudiants(etudiants);
                    }
                });
            }
        }).start();
    }





}