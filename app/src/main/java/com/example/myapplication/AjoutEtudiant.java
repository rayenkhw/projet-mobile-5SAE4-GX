package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Entite.Classe;
import com.example.myapplication.Entite.Club;
import com.example.myapplication.Entite.Enseignant;
import com.example.myapplication.Entite.Etudiant;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.ClasseDao;
import com.example.myapplication.Interface.ClubDao;
import com.example.myapplication.Interface.EtudiantDao;

public class AjoutEtudiant extends AppCompatActivity {


    private EditText nom;
    private EditText prenom;
    private EditText email;
    private EditText niveau;
    private EditText identifiant;

    private Button addButton;

    MyDatabase appDatabase;
    EtudiantDao etudiantDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_etudiant);

        //initialiser
       nom = findViewById(R.id.editTextText2);
       prenom = findViewById(R.id.editTextText3);
       email = findViewById(R.id.editTextTextEmailAddress);
       niveau =findViewById(R.id.editTextNiveau);
       identifiant =findViewById(R.id.editTextidentifiant);
       addButton = findViewById(R.id.button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(
                        ()->{accesDatabase();
                            Etudiant etudiant  = new Etudiant();
                            etudiant.setIdentifiant(identifiant.getText().toString());
                            etudiant.setPrenom(prenom.getText().toString());
                            etudiant.setNom(nom.getText().toString());
                            etudiant.setEmail(email.getText().toString());
                            etudiant.setNiveau(niveau.getText().toString());
                            etudiantDao.insert(etudiant);
                            Intent i = new Intent(getApplicationContext(),ListeEtudiants.class);
                            startActivity(i);
                        }
                ).start();
            }
        });


    }
    public void accesDatabase(){

        MyDatabase appDatabase=MyDatabase.getInstance(AjoutEtudiant.this);
        etudiantDao=appDatabase.etudiantDao();
    }






}


