package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Entite.Club;
import com.example.myapplication.Interface.ClubDao;
import com.example.myapplication.Entite.MyDatabase;
import java.util.ArrayList;
import java.util.List;

public class ListeClubs extends AppCompatActivity {

    private static final int REQUEST_CODE_MODIFY_CLUB = 1; // Code pour l'activité de modification
    private RecyclerView recyclerView;
    private ClubAdapter clubAdapter;
    private List<Club> clubs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_clubs);

        // Initialiser RecyclerView
        recyclerView = findViewById(R.id.clubRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialiser et définir l'adaptateur
        clubAdapter = new ClubAdapter(new ArrayList<>());
        recyclerView.setAdapter(clubAdapter);

        // Configurer SearchView
        SearchView searchView = findViewById(R.id.clubSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                clubAdapter.filterClubs(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                clubAdapter.filterClubs(newText);
                return false;
            }
        });

        // Définir le listener pour les actions de modification et suppression
        clubAdapter.setOnItemClickListener(new ClubAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openModifyClubActivity(position);
            }

            @Override
            public void onUpdateClick(int position) {
                openModifyClubActivity(position);
            }

            @Override
            public void onDeleteClick(int position) {
                deleteClub(position);
            }
        });

        // Charger la liste des clubs depuis la base de données
        loadClubs();
    }

    private void openModifyClubActivity(int position) {
        // Ouvrir ModifierClubActivity pour modifier un club
        if (clubs != null && position < clubs.size()) {
            Club selectedClub = clubs.get(position);
            Intent intent = new Intent(ListeClubs.this, ModifierClub.class);
            intent.putExtra("CLUB_ID", selectedClub.getId());
            intent.putExtra("CLUB_NAME", selectedClub.getNom());
            intent.putExtra("CLUB_PRESIDENT", selectedClub.getPresident());
            intent.putExtra("CLUB_VICEP", selectedClub.getVicep());
            intent.putExtra("CLUB_DESCRIPTION", selectedClub.getDescription());
            startActivityForResult(intent, REQUEST_CODE_MODIFY_CLUB);
        } else {
            Toast.makeText(ListeClubs.this, "ID du club non valide", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadClubs() {
        // Charger la liste des clubs en arrière-plan
        new Thread(() -> {
            ClubDao clubDao = MyDatabase.getInstance(ListeClubs.this).clubDao();
            final List<Club> clubsList = clubDao.getAllClubs();

            runOnUiThread(() -> {
                clubs = clubsList;
                clubAdapter.setClubs(clubs);
            });
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_MODIFY_CLUB && resultCode == RESULT_OK && data != null) {
            boolean clubUpdated = data.getBooleanExtra("CLUB_UPDATED", false);
            if (clubUpdated) {
                loadClubs();  // Recharger la liste des clubs pour refléter les modifications
            }
        }
    }

    private void deleteClub(final int position) {
        // Supprimer le club de la base de données en arrière-plan
        new Thread(() -> {
            ClubDao clubDao = MyDatabase.getInstance(ListeClubs.this).clubDao();
            Club clubToDelete = clubs.get(position);

            // Supprimer le club de la base de données
            clubDao.delete(clubToDelete);
            runOnUiThread(() -> {
                clubs.remove(position);
                clubAdapter.notifyItemRemoved(position);
                Toast.makeText(ListeClubs.this, "Club supprimé", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }
}
