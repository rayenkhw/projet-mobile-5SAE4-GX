package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.Entite.Club;
import com.example.myapplication.Entite.Enseignant;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.ClubAdapter;
import com.example.myapplication.Interface.ClubDao;
import com.example.myapplication.Interface.EnseignantDao;

import java.util.ArrayList;
import java.util.List;

public class ListeClubs extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClubAdapter clubAdapter;

    private List<Club> clubs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_clubs);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.clubRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize and set the adapter
        // Initialize and set the adapter
        clubAdapter = new ClubAdapter(new ArrayList<>()); // Pass a non-null list here


        // Set item click listener
        clubAdapter.setOnItemClickListener(new ClubAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (clubs != null && position < clubs.size()) {
                    Intent intent = new Intent(ListeClubs.this, ModifierEnseignant.class);
                    intent.putExtra("CLUB_ID", clubs.get(position).getId());
                    startActivity(intent);
                } else {
                    // Log or show a message indicating that the list is null or the position is invalid
                }
            }
        });


        recyclerView.setAdapter(clubAdapter);

        // Load the list of enseignants from your Room database
        loadClubs();
    }

    private void loadClubs() {
        // Use a background thread to retrieve the list of enseignants
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Retrieve the list of enseignants from the Room database
                ClubDao clubDao = MyDatabase.getInstance(ListeClubs.this).clubDao();
                final List<Club> clubs = clubDao.getAllClubs();

                // Update the adapter with the list of enseignants on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        clubAdapter.setClubs(clubs);//setEnseignants(enseignants);
                    }
                });
            }
        }).start();
    }


}