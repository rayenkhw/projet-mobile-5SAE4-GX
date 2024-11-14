package com.example.myapplication;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Entite.Enseignant;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.EnseignantDao;

public class ModifierEnseignant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_enseignant);

        // Retrieve the enseignant ID from the intent
        long enseignantId = getIntent().getLongExtra("ENSEIGNANT_ID", -1);

        // Use the enseignantId to load the details of the selected enseignant from the database
        // You can then populate the UI elements for modification
        // For example:
        EnseignantDao enseignantDao = MyDatabase.getInstance(this).enseignantDao();
        Enseignant enseignant = enseignantDao.getEnseignantById(enseignantId);
        updateUiWithEnseignantDetails(enseignant);
    }

    private void updateUiWithEnseignantDetails(Enseignant enseignant) {
        // Update the UI elements with enseignant details for modification
        // For example:
        EditText editTextModifyNom = findViewById(R.id.editTextModifyNom);
        EditText editTextModifyPrenom = findViewById(R.id.editTextModifyPrenom);
        EditText editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        EditText editTextPhone = findViewById(R.id.editTextPhone);
        EditText editTextDate = findViewById(R.id.editTextDate);

        editTextModifyNom.setText(enseignant.getNom());
        editTextModifyPrenom.setText(enseignant.getPrenom());
        editTextTextEmailAddress.setText(enseignant.getEmail());
        editTextPhone.setText(enseignant.getMatiere());
        editTextDate.setText(enseignant.getIdentifiant());
    }
}
