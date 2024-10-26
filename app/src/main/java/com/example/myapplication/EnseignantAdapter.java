package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entite.Enseignant;

import java.util.ArrayList;
import java.util.List;

public class EnseignantAdapter extends RecyclerView.Adapter<EnseignantAdapter.EnseignantViewHolder> {

    private List<Enseignant> enseignants;
    private int selectedItem = RecyclerView.NO_POSITION;

    private List<Enseignant> filteredEnseignants;  // Add this line

    private OnItemClickListener onItemClickListener;
    private Context context;

    public EnseignantAdapter(List<Enseignant> enseignants, Context context) {
        this.enseignants = enseignants;
        this.filteredEnseignants = new ArrayList<>(enseignants);  // Add this line

        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
        this.filteredEnseignants = new ArrayList<>(enseignants);  // Update this line

        notifyDataSetChanged();
    }

    public void filterEnseignants(String query) {
        filteredEnseignants.clear();

        // If the query is empty, show all items
        if (query.isEmpty()) {
            filteredEnseignants.addAll(enseignants);
        } else {
            // Filter the list based on the query
            String lowerCaseQuery = query.toLowerCase();
            for (Enseignant enseignant : enseignants) {
                if (enseignant.getNom().toLowerCase().contains(lowerCaseQuery))
                       {
                    filteredEnseignants.add(enseignant);
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
    public EnseignantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enseignant, parent, false);
        return new EnseignantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EnseignantViewHolder holder, int position) {
        Log.d("Adapter", "onBindViewHolder called for position: " + position);
        Enseignant enseignant = enseignants.get(position);

        holder.nomTextView.setText(enseignant.getNom());
        holder.prenomTextView.setText(enseignant.getPrenom());
        holder.emailTextView.setText(enseignant.getEmail());
        holder.matiereTextView.setText(enseignant.getMatiere());

        // Highlight the selected item
        holder.itemView.setActivated(position == selectedItem);

        // Set the click listener for the entire itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the clicked enseignant
                Enseignant enseignant = enseignants.get(position);

                // Launch EnseignantDetail activity
                Intent intent = new Intent(context, EnseignantDetail.class);
                intent.putExtra("ENSEIGNANT_ID", enseignant.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        int itemCount = enseignants != null ? enseignants.size() : 0;
        Log.d("Adapter", "getItemCount: " + itemCount);
        return itemCount;
    }

    static class EnseignantViewHolder extends RecyclerView.ViewHolder {

        TextView nomTextView;
        TextView prenomTextView;
        TextView emailTextView;
        TextView matiereTextView;

        public EnseignantViewHolder(@NonNull View itemView) {
            super(itemView);

            nomTextView = itemView.findViewById(R.id.nomTextView);
            prenomTextView = itemView.findViewById(R.id.prenomTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            matiereTextView = itemView.findViewById(R.id.matiereTextView);
        }
    }
}