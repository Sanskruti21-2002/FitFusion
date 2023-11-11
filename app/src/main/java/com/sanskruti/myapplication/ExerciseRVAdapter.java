package com.sanskruti.myapplication;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.content.Context;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;

public class ExerciseRVAdapter extends RecyclerView.Adapter<ExerciseRVAdapter.ExerciseViewHolder> {

    private ArrayList<ExerciseRVModal> exerciseRVModalArrayList;
    private Context context;
    private ExerciseClickInterface exerciseClickInterface;

    public ExerciseRVAdapter(ArrayList<ExerciseRVModal> exerciseRVModalArrayList, Context context, ExerciseClickInterface exerciseClickInterface) {
        this.exerciseRVModalArrayList = exerciseRVModalArrayList;
        this.context = context;
        this.exerciseClickInterface = exerciseClickInterface;
    }

    @NonNull
    @Override
    public ExerciseRVAdapter.ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_rv_item,parent,false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseRVAdapter.ExerciseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.exerciseTV.setText(exerciseRVModalArrayList.get(position).getExerciseName());
        holder.exerciseLAV.setAnimation(exerciseRVModalArrayList.get(position).getId());

        String time = String.valueOf(exerciseRVModalArrayList.get(position).getTime())+" MIN";
        holder.timeTV.setText(time);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseClickInterface.onExerciseClick(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return exerciseRVModalArrayList.size();
        //returns number of items in the list, that is number of exercised inside out RecyclerView
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder{
        private TextView exerciseTV,timeTV;
        private LottieAnimationView exerciseLAV;
        public ExerciseViewHolder(@NonNull View itemView)
        {
            super(itemView);
            exerciseTV = itemView.findViewById(R.id.idTVExerciseName);
            timeTV = itemView.findViewById(R.id.idTVExerciseTime);
            exerciseLAV = itemView.findViewById(R.id.idExerciseLAV);
        }
    }
    public interface ExerciseClickInterface
    {
        void onExerciseClick(int position);
    }
}