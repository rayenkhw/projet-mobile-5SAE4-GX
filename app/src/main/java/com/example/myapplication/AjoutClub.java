package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Entite.Club;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.ClasseDao;
import com.example.myapplication.Interface.ClubDao;

public class AjoutClub extends AppCompatActivity {
private EditText nomclub ,description,president,vicep;
  private Button  button;
    MyDatabase appDatabase;
    ClubDao clubDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_club);

   //initialiser
        nomclub = findViewById(R.id.nomclub);
        president = findViewById(R.id.president);
        vicep = findViewById(R.id.vicep);
        description =findViewById(R.id.description);
   button= findViewById(R.id.button);

  button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
new Thread(
        ()->{accesDatabase();
            Club c  = new Club();
            c.setNom(nomclub.getText().toString());
            c.setPresident(president.getText().toString());
            c.setDescription(description.getText().toString());
            c.setVicep(vicep.getText().toString());
            clubDao.insert(c);
            Intent i = new Intent(getApplicationContext(),ListeClubs.class);
            startActivity(i);

        }
).start();
      }
  });


    }
    public void accesDatabase(){

        MyDatabase appDatabase=MyDatabase.getInstance(AjoutClub.this);
        clubDao=appDatabase.clubDao();
    }
}