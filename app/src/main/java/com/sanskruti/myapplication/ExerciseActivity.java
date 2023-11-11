package com.sanskruti.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity implements ExerciseRVAdapter.ExerciseClickInterface{


    private RecyclerView exerciseRV;
    private ArrayList<ExerciseRVModal> exerciseRVModalArrayList;
    private ExerciseRVAdapter exerciseRVAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        exerciseRV = findViewById(R.id.idRVExercise);
        exerciseRVModalArrayList = new ArrayList<>();
        exerciseRVAdpater = new ExerciseRVAdapter(exerciseRVModalArrayList,this,this::onExerciseClick);

        // to manage layout of items within a recyclerView
        LinearLayoutManager manager = new LinearLayoutManager(this);
        exerciseRV.setLayoutManager(manager);
        exerciseRV.setAdapter(exerciseRVAdpater);
        addData();
    }

    private void addData()
    {
        exerciseRVModalArrayList.add(new ExerciseRVModal("Side Plank",getResources().getString(R.string.side_plank),R.raw.side_plank,20,10));
        exerciseRVModalArrayList.add(new ExerciseRVModal("Lunges",getResources().getString(R.string.lunges),R.raw.lunges,30,10));
        exerciseRVModalArrayList.add(new ExerciseRVModal("High Stepping",getResources().getString(R.string.stepping),R.raw.stepping,40,10));
        exerciseRVModalArrayList.add(new ExerciseRVModal("Abs Crunches",getResources().getString(R.string.abs_crunches),R.raw.abs_crunches,30,20));
        exerciseRVModalArrayList.add(new ExerciseRVModal("Push Ups",getResources().getString(R.string.push_ups),R.raw.push_ups,10,5));
        exerciseRVAdpater.notifyDataSetChanged();
        //will be called when user will click on any of the exercise


    }


    public void onExerciseClick(int position) {
       Intent i = new Intent(ExerciseActivity.this,ExerciseDetailActivity.class);
        i.putExtra("exerciseName",exerciseRVModalArrayList.get(position).getExerciseName());
        i.putExtra("Exerciseid",exerciseRVModalArrayList.get(position).getId());
        i.putExtra("time",exerciseRVModalArrayList.get(position).getTime());
        i.putExtra("calories",exerciseRVModalArrayList.get(position).getCalories());
        i.putExtra("desc",exerciseRVModalArrayList.get(position).getExeciseDescription());
        startActivity(i);
    }
}