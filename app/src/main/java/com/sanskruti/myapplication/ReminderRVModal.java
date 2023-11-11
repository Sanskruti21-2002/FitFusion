package com.sanskruti.myapplication;


public class ReminderRVModal {
    private String time;
    private String description;
    private boolean isActive;

    public ReminderRVModal(String time, String description, boolean isActive) {
        this.time = time;
        this.description = description;
        this.isActive = isActive;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }


}