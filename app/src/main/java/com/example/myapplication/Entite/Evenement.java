package com.example.myapplication.Entite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Evenement")
public class Evenement {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "datt")
    public String datt;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "clubId")
    public long clubId; // Foreign key to link the event to a club

    @Ignore
    public Evenement() {
        // Constructor without arguments annotated with @Ignore
        // Room will ignore this constructor during database creation
    }

    public Evenement(Long id, String datt, String description, long clubId) {
        this.id = id;
        this.datt = datt;
        this.description = description;
        this.clubId = clubId;
    }
}
