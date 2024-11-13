package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entite.Classe;

import java.util.List;

public class ClasseAdapter extends RecyclerView.Adapter<ClasseAdapter.ClasseViewHolder> {

    private List<Classe> classes;
    private int selectedItem = RecyclerView.NO_POSITION;
    private OnItemClickListener onItemClickListener;

    public ClasseAdapter(List<Classe> classes) {
        this.classes = classes;
    }

    // Interface for click listener
    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);  // Added delete click listener
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ClasseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club, parent, false);
        return new ClasseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClasseViewHolder holder, int position) {
        Classe classe = classes.get(position);
        holder.nomTextView.setText(classe.getNom());
        holder.specialiteTextView.setText(classe.getSpecialite());
        holder.numeroTextView.setText(classe.getNumero());

        // Highlight the selected item
        holder.itemView.setActivated(position == selectedItem);

        // Set click listener for the item view
        holder.itemView.setOnClickListener(v -> {
            selectedItem = holder.getAdapterPosition();
            notifyDataSetChanged();

            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(selectedItem);
            }
        });

        // Set delete button click listener
        holder.deleteButton.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onDeleteClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return classes != null ? classes.size() : 0;
    }

    static class ClasseViewHolder extends RecyclerView.ViewHolder {

        TextView nomTextView;
        TextView specialiteTextView;
        TextView numeroTextView;
        View deleteButton; // Button or icon for delete

        public ClasseViewHolder(@NonNull View itemView) {
            super(itemView);

            nomTextView = itemView.findViewById(R.id.nomTextView);
            specialiteTextView = itemView.findViewById(R.id.presidentTextView);
            numeroTextView = itemView.findViewById(R.id.vicepTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton); // Assuming there's a delete button in your layout
        }
    }
}
