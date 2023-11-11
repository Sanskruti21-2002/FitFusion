package com.sanskruti.myapplication;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;

public class DietRVAdapter extends RecyclerView.Adapter<DietRVAdapter.ViewHolder> {
    private List<DietRVModal> dietPlans;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(DietRVModal item);
    }

    public DietRVAdapter(List<DietRVModal> dietPlans, OnItemClickListener listener) {
        this.dietPlans = dietPlans;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diet_plan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DietRVModal dietPlan = dietPlans.get(position);
        holder.planName.setText(dietPlan.getPlanName());
        holder.description.setText(dietPlan.getDescription());
        holder.calories.setText("Calories: " + dietPlan.getCalories());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(dietPlan));
    }

    @Override
    public int getItemCount() {
        return dietPlans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView planName;
        public TextView description;
        public TextView calories;

        public ViewHolder(View itemView) {
            super(itemView);
            planName = itemView.findViewById(R.id.idTVPlanName);
            description = itemView.findViewById(R.id.idDescriptionTV);
            calories = itemView.findViewById(R.id.idCaloriesTV);
        }
    }
}
