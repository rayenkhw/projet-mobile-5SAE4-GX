package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Entite.Club;
import java.util.ArrayList;
import java.util.List;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewHolder> {

    private List<Club> clubs;
    private List<Club> filteredClubs; // Liste filtrée pour la recherche
    private int selectedItem = RecyclerView.NO_POSITION;
    private OnItemClickListener onItemClickListener;

    public ClubAdapter(List<Club> clubs) {
        this.clubs = clubs;
        this.filteredClubs = new ArrayList<>(clubs);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onUpdateClick(int position);  // Callback pour la mise à jour
        void onDeleteClick(int position);  // Callback pour la suppression
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
        this.filteredClubs = new ArrayList<>(clubs);
        notifyDataSetChanged();
    }

    public void filterClubs(String query) {
        filteredClubs.clear();
        if (query.isEmpty()) {
            filteredClubs.addAll(clubs);
        } else {
            for (Club club : clubs) {
                if (club.getNom().toLowerCase().contains(query.toLowerCase()) ||
                        club.getPresident().toLowerCase().contains(query.toLowerCase())) {
                    filteredClubs.add(club);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ClubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club, parent, false);
        return new ClubViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubViewHolder holder, int position) {
        Club club = filteredClubs.get(position);
        holder.nomTextView.setText(club.getNom());
        holder.presidentTextView.setText(club.getPresident());
        holder.vicepTextView.setText(club.getVicep());
        holder.descriptionTextView.setText(club.getDescription());

        // Mettre en évidence l'élément sélectionné
        holder.itemView.setActivated(position == selectedItem);

        // Gérer le clic pour l'élément
        holder.itemView.setOnClickListener(v -> {
            selectedItem = holder.getAdapterPosition();
            notifyDataSetChanged();
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(selectedItem);
            }
        });

        // Gérer le clic pour le bouton "Mise à jour"
        holder.updateButton.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onUpdateClick(position);
            }
        });

        // Gérer le clic pour le bouton "Supprimer"
        holder.deleteButton.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onDeleteClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredClubs != null ? filteredClubs.size() : 0;
    }

    public void updateItem(int position, Club updatedClub) {
        clubs.set(position, updatedClub);
        notifyItemChanged(position);
    }

    public void removeItem(int position) {
        clubs.remove(position);
        notifyItemRemoved(position);
    }

    static class ClubViewHolder extends RecyclerView.ViewHolder {

        TextView nomTextView;
        TextView presidentTextView;
        TextView vicepTextView;
        TextView descriptionTextView;
        Button updateButton;
        Button deleteButton;

        public ClubViewHolder(@NonNull View itemView) {
            super(itemView);
            nomTextView = itemView.findViewById(R.id.nomTextView);
            presidentTextView = itemView.findViewById(R.id.presidentTextView);
            vicepTextView = itemView.findViewById(R.id.vicepTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            updateButton = itemView.findViewById(R.id.updateButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
