package com.example.myapplication;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entite.Club;
import com.example.myapplication.Entite.Etudiant;
import com.example.myapplication.Entite.Evaluation;

import java.util.List;

public class EtudiantAdapter extends RecyclerView.Adapter<EtudiantAdapter.EtudiantViewHolder> {

    private List<Etudiant> etudiants;
  //  private Context context;
    private OnItemClickListener onItemClickListener;
    private int selectedItem = RecyclerView.NO_POSITION;

    // Constructor
    public EtudiantAdapter(List<Etudiant> etudiants/*, Context context*/) {
        this.etudiants = etudiants;
     //   this.context = context;

    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public EtudiantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_etudiant, parent, false);
        return new EtudiantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull EtudiantViewHolder holder, int position) {
        // Enseignant enseignant = enseignants.get(position);
        Etudiant e = etudiants.get(position);
        holder.IdentifiantTextView.setText(e.getIdentifiant());
        holder.prenomTextView.setText(e.getPrenom());
        holder.NomTextView.setText(e.getNom());
        holder.niveauTextView.setText(e.getNiveau());

        // Highlight the selected item
        holder.itemView.setActivated(position == selectedItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update selected item and notify the adapter
                selectedItem = holder.getAdapterPosition();
                notifyDataSetChanged();

                // Notify the activity/fragment about the item click
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(selectedItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return etudiants != null ? etudiants.size() : 0;
    }

    static class EtudiantViewHolder extends RecyclerView.ViewHolder {

        TextView IdentifiantTextView;
        TextView prenomTextView;
        TextView NomTextView;
        TextView niveauTextView;


        public EtudiantViewHolder(@NonNull View itemView) {
            super(itemView);

            IdentifiantTextView = itemView.findViewById(R.id.IdentifiantTextView);
            prenomTextView = itemView.findViewById(R.id.prenomTextView);
            NomTextView = itemView.findViewById(R.id.NomTextView);
            niveauTextView = itemView.findViewById(R.id.niveauTextView);
        }
    }
















/*
    // ViewHolder class
    public class EvaluationViewHolder extends RecyclerView.ViewHolder {
        TextView identifiantTextView;
        TextView nomTextView;

        TextView prenomTextView;

        TextView NiveauTextView;

        public EvaluationViewHolder(@NonNull View itemView) {
            super(itemView);
            identifiantTextView = itemView.findViewById(R.id.identifiantTextView);
            nomTextView = itemView.findViewById(R.id.nomTextView);
            prenomTextView = itemView.findViewById(R.id.prenomTextView);
            NiveauTextView = itemView.findViewById(R.id.niveauTextView);


            // Set up the click listener for the item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public EvaluationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.evaluation_item, parent, false);
        return new EvaluationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvaluationViewHolder holder, int position) {
        Etudiant currentEvaluation = etudiants.get(position);

        // Set the data to the views
        holder.identifiantTextView.setText(currentEvaluation.identifiant);
        holder.nomTextView.setText(currentEvaluation.nom);
        holder.prenomTextView.setText(currentEvaluation.prenom);
        holder.NiveauTextView.setText(currentEvaluation.niveau);

    }

    @Override
    public int getItemCount() {
        return etudiants.size();
    }

    // Helper method to update the list of evaluations
    public void setEtudiants(List<Etudiant> evaluations) {
        this.etudiants = etudiants;
        notifyDataSetChanged();
    }

    public Etudiant getEtudiant(int position) {
        if (position >= 0 && position < etudiants.size()) {
            return etudiants.get(position);
        }
        return null;
    }*/
}
