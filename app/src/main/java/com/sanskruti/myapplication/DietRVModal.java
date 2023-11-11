package com.sanskruti.myapplication;
public class DietRVModal {
    private String planName;
    private String description;
    private int calories;

    public DietRVModal(String planName, String description, int calories) {
        this.planName = planName;
        this.description = description;
        this.calories = calories;
    }

    public String getPlanName() {
        return planName;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }
}
