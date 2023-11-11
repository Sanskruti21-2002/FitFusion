package com.sanskruti.myapplication;

public class ExerciseRVModal {
    private String exerciseName;
    private String execiseDescription;
    //    private String imgUrl;
    private int calories,time,id;

    public ExerciseRVModal(String exerciseName, String execiseDescription, int id, int calories, int time) {
        this.exerciseName = exerciseName;
        this.execiseDescription = execiseDescription;
//        this.imgUrl = imgUrl;
        this.id=id;
        this.calories = calories;
        this.time = time;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExeciseDescription() {
        return execiseDescription;
    }

    public void setExeciseDescription(String execiseDescription) {
        this.execiseDescription = execiseDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}