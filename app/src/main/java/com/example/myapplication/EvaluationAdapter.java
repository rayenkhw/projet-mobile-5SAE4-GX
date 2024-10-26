package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entite.Evaluation;

import java.util.List;

public class EvaluationAdapter extends RecyclerView.Adapter<EvaluationAdapter.EvaluationViewHolder> {

    public List<Evaluation> evaluations;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private int selectedItem = RecyclerView.NO_POSITION;

    // Constructor
    public EvaluationAdapter(List<Evaluation> evaluations, Context context) {
        this.evaluations = evaluations;
        this.context = context;

    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    // ViewHolder class
    public class EvaluationViewHolder extends RecyclerView.ViewHolder {
        public TextView noteExamenTextView;
        public TextView noteCcTextView;

        public EvaluationViewHolder(@NonNull View itemView) {
            super(itemView);
            noteExamenTextView = itemView.findViewById(R.id.noteExamenTextView);
            noteCcTextView = itemView.findViewById(R.id.noteCcTextView);

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
        Evaluation currentEvaluation = evaluations.get(position);

        // Set the data to the views
        holder.noteExamenTextView.setText(currentEvaluation.noteExamen);
        holder.noteCcTextView.setText(currentEvaluation.noteCc);
    }

    @Override
    public int getItemCount() {
        return evaluations.size();
    }

    // Helper method to update the list of evaluations
    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
        notifyDataSetChanged();
    }

    public Evaluation getEvaluation(int position) {
        if (position >= 0 && position < evaluations.size()) {
            return evaluations.get(position);
        }
        return null;
    }
}
