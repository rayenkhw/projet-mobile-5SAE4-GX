package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.SearchView;
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
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_etudiants);

        recyclerView = findViewById(R.id.etudiantRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        etudiantAdapter = new EtudiantAdapter(new ArrayList<>(),this);
        // Set item click listener
        recyclerView.setAdapter(etudiantAdapter);

        etudiantAdapter.setOnItemClickListener(new EtudiantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        // Initialize SearchView
        searchView = findViewById(R.id.searchViewEtudiants);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submit if needed
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the list based on the search query
                Log.d("SearchView", "Query: " + newText);
                etudiantAdapter.filterEtudiants(newText);
                return true;
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