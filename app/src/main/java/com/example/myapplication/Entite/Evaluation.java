package com.example.myapplication.Entite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Evaluation {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "noteExamen")
    public String noteExamen;

    @ColumnInfo(name = "noteCc")
    public String noteCc;

    @ColumnInfo(name = "remarque")
    public String remarque;

    // Constructors

    public Evaluation() {
        // Default constructor
    }

    public Evaluation(String noteExamen, String noteCc, String remarque) {
        this.noteExamen = noteExamen;
        this.noteCc = noteCc;
        this.remarque = remarque;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNoteExamen() {
        return noteExamen;
    }

    public void setNoteExamen(String noteExamen) {
        this.noteExamen = noteExamen;
    }

    public String getNoteCc() {
        return noteCc;
    }

    public void setNoteCc(String noteCc) {
        this.noteCc = noteCc;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }
}
