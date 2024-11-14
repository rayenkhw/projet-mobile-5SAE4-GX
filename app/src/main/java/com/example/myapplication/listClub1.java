/*package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.ClubDao;

public class listClub1 extends AppCompatActivity {
//ListView lst;
//ClubDao clubDAO;
// MyDatabase appDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_club1);

    lst = findViewById(R.id.lst);
    new Thread(()->{
       accesDatabase();
        Cursor c = (Cursor) clubDAO.getAllClubs();

        runOnUiThread(()->{
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(listClub1.this,R.layout.item_club,c,
                   new String[]{c.getColumnName(0),c.getColumnName(1)},
                   new int[]{R.id.nom,R.id.president},1 );
            lst.setAdapter(adapter);
        });
    } ).start();

    }
    public void accesDatabase(){

     appDatabase=MyDatabase.getInstance(listClub1.this);
       clubDAO=appDatabase.clubDao();
    }
}
*/