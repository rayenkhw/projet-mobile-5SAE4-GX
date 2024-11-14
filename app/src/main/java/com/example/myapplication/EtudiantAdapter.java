package com.example.myapplication;



import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entite.Club;
import com.example.myapplication.Entite.Enseignant;
import com.example.myapplication.Entite.Etudiant;
import com.example.myapplication.Entite.Evaluation;

import java.util.ArrayList;
import java.util.List;

public class EtudiantAdapter extends RecyclerView.Adapter<EtudiantAdapter.EtudiantViewHolder> {

    private List<Etudiant> etudiants;
  //  private Context context;
    private OnItemClickListener listener;
    private int selectedItem = RecyclerView.NO_POSITION;

    private List<Etudiant> filteredEtudiants;  // Add this line

    private EtudiantAdapter.OnItemClickListener onItemClickListener;
    private Context context;
    // Constructor
    public EtudiantAdapter(List<Etudiant> etudiants, Context context) {
        this.etudiants = etudiants;
        this.filteredEtudiants = new ArrayList<>(etudiants);
        this.context = context;

    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
        this.filteredEtudiants = new ArrayList<>(etudiants);  // Update this line

        notifyDataSetChanged();
    }
    public void filterEtudiants(String query) {
        filteredEtudiants.clear();

        // If the query is empty, show all items
        if (query.isEmpty()) {
            filteredEtudiants.addAll(etudiants);
        } else {
            // Filter the list based on the query
            String lowerCaseQuery = query.toLowerCase();
            for (Etudiant etudiant : etudiants) {
                if (etudiant.getNom().toLowerCase().contains(lowerCaseQuery)) {
                    filteredEtudiants.add(etudiant);
                }
            }
        }
        notifyDataSetChanged(); // Notify the adapter of the dataset change
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
    public void onBindViewHolder(@NonNull EtudiantViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d("Adapter", "onBindViewHolder called for position: " + position);

        Etudiant etudiant = etudiants.get(position);

        holder.nomTextView.setText(etudiant.getNom());
        holder.prenomTextView.setText(etudiant.getPrenom());
        holder.emailTextView.setText(etudiant.getEmail());
        holder.niveauTextView.setText(etudiant.getNiveau());
        holder.identifiantTextView.setText(etudiant.getIdentifiant());


        // Highlight the selected item
        holder.itemView.setActivated(position == selectedItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the clicked enseignant
                Etudiant etudiant = etudiants.get(position);

                // Launch EtudiantDetail activity
                Intent intent = new Intent(context, EtudiantDetail.class);
                intent.putExtra("ETUDIANT_ID", etudiant.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        int itemCount = etudiants != null ? etudiants.size() : 0;
        Log.d("Adapter", "getItemCount: " + itemCount);
        return itemCount;
    }

    static class EtudiantViewHolder extends RecyclerView.ViewHolder {

        TextView nomTextView;
        TextView prenomTextView;
        TextView emailTextView;
        TextView niveauTextView;
        TextView identifiantTextView;



        public EtudiantViewHolder(@NonNull View itemView) {
            super(itemView);

            nomTextView = itemView.findViewById(R.id.NomTextView);
            prenomTextView = itemView.findViewById(R.id.prenomTextView);

            emailTextView = itemView.findViewById(R.id.emailTextView);
            niveauTextView = itemView.findViewById(R.id.niveauTextView);
            identifiantTextView = itemView.findViewById(R.id.IdentifiantTextView);

        }
    }

}
