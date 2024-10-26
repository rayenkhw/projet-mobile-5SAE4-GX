package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

   /* private static class InsertAsyncTask extends AsyncTask<Void, Void, Void> {
        private Context context;
        private String nomClasse, nom, prenom, email, niveau, identifiant;

        public InsertAsyncTask(Context context, String nomClasse, String nom, String prenom, String email, String niveau, String identifiant) {
            this.context = context;
            this.nomClasse = nomClasse;
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.niveau = niveau;
            this.identifiant = identifiant;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Get the class by name
            ClasseDao classeDao = MyDatabase.getInstance(context).classeDao();
            Classe classe = classeDao.getClasseByName(nomClasse);

            if (classe != null) {
                // Create and insert the student with the assigned classId
                Etudiant etudiant = new Etudiant(nom, prenom, email, identifiant, niveau, classe.getId());
                EtudiantDao etudiantDao = MyDatabase.getInstance(context).etudiantDao();
                etudiantDao.insert(etudiant);

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context, "Etudiant ajouté avec succès", Toast.LENGTH_SHORT).show();
        }
    }  */

