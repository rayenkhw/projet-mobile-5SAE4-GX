package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entite.Enseignant;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.EnseignantDao;

import java.util.ArrayList;
import java.util.List;

public class ListeEnseignants extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EnseignantAdapter enseignantAdapter;
    private SearchView searchView;

    private List<Enseignant> enseignants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_enseignants);

        recyclerView = findViewById(R.id.enseignantsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize and set the adapter
        enseignantAdapter = new EnseignantAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(enseignantAdapter);

        // Set item click listener
        enseignantAdapter.setOnItemClickListener(new EnseignantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        // Initialize SearchView
        searchView = findViewById(R.id.searchViewEnseignants);
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
                enseignantAdapter.filterEnseignants(newText);
                return true;
            }


        });


        recyclerView.setAdapter(enseignantAdapter);

        // Load the list of enseignants from your Room database
        loadEnseignants();
    }

    private void loadEnseignants() {
        // Use a background thread to retrieve the list of enseignants
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Retrieve the list of enseignants from the Room database
                EnseignantDao enseignantDao = MyDatabase.getInstance(ListeEnseignants.this).enseignantDao();
                final List<Enseignant> enseignants = enseignantDao.getAllEnseignants();

                // Update the adapter with the list of enseignants on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        enseignantAdapter.setEnseignants(enseignants);
                    }
                });
            }
        }).start();
    }





}