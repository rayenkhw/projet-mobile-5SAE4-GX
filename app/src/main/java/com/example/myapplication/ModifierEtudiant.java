package com.example.myapplication;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Entite.Enseignant;
import com.example.myapplication.Entite.Etudiant;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.EtudiantDao;

public class ModifierEtudiant extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_enseignant);

        // Retrieve the etudiant ID from the intent
        long etudiantId = getIntent().getLongExtra("ETUDIANT_ID", -1);

        // Use the etudiantId to load the details of the selected enseignant from the database
        // You can then populate the UI elements for modification
        // For example:
        EtudiantDao etudiantDao = MyDatabase.getInstance(this).etudiantDao();
        Etudiant etudiant = etudiantDao.getEtudiantById(etudiantId);
        updateUiWithEtudiantDetails(etudiant);
    }
    private void updateUiWithEtudiantDetails(Etudiant etudiant) {
        // Update the UI elements with enseignant details for modification
        // For example:
        EditText editTextModifyNom = findViewById(R.id.editTextModifyNom);
        EditText editTextModifyPrenom = findViewById(R.id.editTextModifyPrenom);
        EditText editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        EditText editTextPhone = findViewById(R.id.editTextPhone);
        EditText editTextDate = findViewById(R.id.editTextDate);

        editTextModifyNom.setText(etudiant.getNom());
        editTextModifyPrenom.setText(etudiant.getPrenom());
        editTextTextEmailAddress.setText(etudiant.getEmail());
        editTextPhone.setText(etudiant.getNiveau());
        editTextDate.setText(etudiant.getIdentifiant());
    }

}
