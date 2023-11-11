package com.sanskruti.myapplication;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WaterReminder extends AppCompatActivity {
    private ProgressBar totalProgress;
    private TextView tvIntook;
    private int goal = 2000; // Default goal, you can change it as needed
    private int totalIntake = 0;
    private static final String CHANNEL_ID = "WaterReminderChannel";
    private static final int NOTIFICATION_ID = 1;
    private boolean showNotificationMessage = true;
//    FloatingActionButton btnNotific1;
    private int selectedOption = 0; // 0 for no selection, you can choose other default values

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_reminder);
        totalProgress = findViewById(R.id.totalProgress);
        tvIntook = findViewById(R.id.tvIntook);
        updateProgress();

        // Set an OnClickListener for the FAB (Add button)
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event for the Add button

                // Update the total intake and progress
                totalIntake += getCurrentSelectedValue();
                updateProgress();

                // Check if the goal is reached and show a notification
                if (totalIntake >= goal) {
                    showNotification();
                }
            }
        });
    }
    public void op50ml(View view) {
        setSelectedOption(50); // Set the selected option to 50ml
    }

    public void op100ml(View view) {
        setSelectedOption(100); // Set the selected option to 100ml
    }
    public void op150ml(View view) {
        setSelectedOption(150); // Set the selected option to 100ml
    }
    public void op200ml(View view) {
        setSelectedOption(200); // Set the selected option to 100ml
    }
    public void op250ml(View view) {
        setSelectedOption(250); // Set the selected option to 100ml
    }


    private void setSelectedOption(int optionValue) {
        selectedOption = optionValue;
    }
    private int getCurrentSelectedValue() {
        return selectedOption;
    }
    private void updateProgress() {
        tvIntook.setText(String.valueOf(totalIntake));
        totalProgress.setProgress((totalIntake * 100) / goal);
    }
//    btnNotific1 = findViewById(R.id.btnNotific);
//    val btnNotific = findViewById<FloatingActionButton>(R.id.btnNotific)
//
//    btnNotific1.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            // Toggle the showNotificationMessage variable
//            showNotificationMessage = !showNotificationMessage;
//
//            if (showNotificationMessage) {
//                // Check if the goal is reached and show a notification
//                if (totalIntake >= goal) {
//                    showNotification();
//                }
//            } else {
//                // Update the button text to display "/"
//                btnNotific1.setText("/");
//            }
//        }
//    });


    private void showNotification() {
        createNotificationChannel();

        Intent intent = new Intent(this, WaterReminder.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Goal Completed")
                .setContentText("Congratulations! You have completed your daily water goal.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Check if the goal is reached before showing the notification

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "WaterReminderChannel";
            String description = "Channel for Water Reminder Notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
