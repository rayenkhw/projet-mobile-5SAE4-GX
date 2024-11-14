package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entite.Classe;
import com.example.myapplication.Entite.Enseignant;

import java.util.ArrayList;
import java.util.List;

public class ClasseAdapter extends RecyclerView.Adapter<ClasseAdapter.ClasseViewHolder> {

    private List<Classe> classes;
    private int selectedItem = RecyclerView.NO_POSITION;
    private OnItemClickListener Listener;

    private List<Classe> filteredClasses;


    private OnItemClickListener onItemClickListener;
    private Context context;

    public ClasseAdapter(List<Classe> classes,Context context) {

        this.classes = classes;
        this.filteredClasses = new ArrayList<>(classes);  // Add this line

        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
        this.filteredClasses = new ArrayList<>(classes);
        notifyDataSetChanged();
    }

    public void filterClasses(String query) {
        filteredClasses.clear();

        // If the query is empty, show all items
        if (query.isEmpty()) {
            filteredClasses.addAll(classes);
        } else {
            // Filter the list based on the query
            String lowerCaseQuery = query.toLowerCase();
            for (Classe classe : classes) {
                if (classe.getNom().toLowerCase().contains(lowerCaseQuery))
                {
                    filteredClasses.add(classe);
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
    public ClasseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classe, parent, false);
        return new ClasseViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ClasseAdapter.ClasseViewHolder holder, int position) {
        Classe classe = filteredClasses.get(position);  // Utilisez filteredClasses ici
        holder.nomTextView.setText(classe.getNom());
        holder.specialiteTextView.setText(classe.getSpecialite());
        holder.numeroTextViw.setText(classe.getNumero());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ClasseModification.class);
                intent.putExtra("CLASSE_ID", classe.getId());  // Transmettez bien l'ID
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        int itemCount = classes != null ? classes.size() : 0;
        Log.d("Adapter", "getItemCount: " + itemCount);
        return itemCount;
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
