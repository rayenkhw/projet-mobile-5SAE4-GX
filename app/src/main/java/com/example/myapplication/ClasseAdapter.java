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
    private ClubAdapter.OnItemClickListener onItemClickListener;

    public ClasseAdapter(List<Classe> classes) {
        this.classes = classes;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setClasses(List<Classe> classes) {
        this.classes = classes;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(ClubAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @androidx.annotation.NonNull
    @Override
    public ClasseAdapter.ClasseViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club, parent, false);
        return new ClasseAdapter.ClasseViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull ClasseAdapter.ClasseViewHolder holder, int position) {
        Classe classe = classes.get(position);
        holder.nomTextView.setText(classe.getNom());
        holder.specialiteTextView.setText(classe.getSpecialite());
        holder.numeroTextViw.setText(classe.getNumero());

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
        return classes != null ? classes.size() : 0;
    }

    static class ClasseViewHolder extends RecyclerView.ViewHolder {

        TextView nomTextView;
        TextView specialiteTextView;
        TextView numeroTextViw;


        public ClasseViewHolder(@NonNull View itemView) {
            super(itemView);

            nomTextView = itemView.findViewById(R.id.nomTextView);
            specialiteTextView = itemView.findViewById(R.id.presidentTextView);
            numeroTextViw = itemView.findViewById(R.id.vicepTextView);
        }
    }
}
