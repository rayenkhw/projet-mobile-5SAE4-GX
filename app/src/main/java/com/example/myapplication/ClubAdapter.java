package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Entite.Club;
import java.util.List;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewHolder> {

    private List<Club> clubs;
    private int selectedItem = RecyclerView.NO_POSITION;
    private OnItemClickListener onItemClickListener;

    public ClubAdapter(List<Club> clubs) {
        this.clubs = clubs;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onUpdateClick(int position);  // Callback for the update action
        void onDeleteClick(int position);  // Callback for the delete action
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
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
        Club club = clubs.get(position);
        holder.nomTextView.setText(club.getNom());
        holder.presidentTextView.setText(club.getPresident());
        holder.vicepTextView.setText(club.getVicep());
        holder.descriptionTextView.setText(club.getDescription());

        // Highlight the selected item
        holder.itemView.setActivated(position == selectedItem);

        // Set onClickListener for the item
        holder.itemView.setOnClickListener(v -> {
            selectedItem = holder.getAdapterPosition();
            notifyDataSetChanged();

            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(selectedItem);
            }
        });

        // Set onClickListener for the "Update" button
        holder.updateButton.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onUpdateClick(position);
            }
        });

        // Set onClickListener for the "Delete" button
        holder.deleteButton.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onDeleteClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clubs != null ? clubs.size() : 0;
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
        Button updateButton;  // Button for updating a club
        Button deleteButton;  // Button for deleting a club

        public ClubViewHolder(@NonNull View itemView) {
            super(itemView);

            nomTextView = itemView.findViewById(R.id.nomTextView);
            presidentTextView = itemView.findViewById(R.id.presidentTextView);
            vicepTextView = itemView.findViewById(R.id.vicepTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            updateButton = itemView.findViewById(R.id.updateButton);  // Assuming you have a button in item_club.xml
            deleteButton = itemView.findViewById(R.id.deleteButton);  // Assuming you have a button in item_club.xml
        }
    }
}
