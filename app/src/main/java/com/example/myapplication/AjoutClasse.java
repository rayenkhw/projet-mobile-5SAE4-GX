package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Entite.Classe;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.ClasseDao;

public class AjoutClasse extends AppCompatActivity {

    private EditText nom ,specialite,numero;
    private Button button;
    MyDatabase appDatabase;
    ClasseDao classeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_classe);

        //initialiser
        nom = findViewById(R.id.editTextText2);
        specialite = findViewById(R.id.editTextText3);
        numero = findViewById(R.id.numero);
        button= findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(
                        ()->{accesDatabase();
                            Classe c  = new Classe();
                            c.setNom(nom.getText().toString());
                            c.setSpecialite(specialite.getText().toString());
                            c.setNumero(numero.getText().toString());
                            classeDao.insert(c);
                            Intent i = new Intent(getApplicationContext(),ListeClasse.class);
                            startActivity(i);

                        }
                ).start();
            }
        });


    }

    public void accesDatabase(){

        MyDatabase appDatabase=MyDatabase.getInstance(AjoutClasse.this);
        classeDao=appDatabase.classeDao();
    }
}