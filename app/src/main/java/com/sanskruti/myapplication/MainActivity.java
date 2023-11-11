package com.sanskruti.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.airbnb.lottie.LottieAnimationView;


public class MainActivity extends AppCompatActivity {

    private LinearLayout exerciseLL,stepCounterLL,reminderLL,waterLL,musicPlayerLL,dietLL;
    private LottieAnimationView exerciseLAV,counterLAV,reminderLAV,LAVWater,musicLAV,dietLAV;
    private Button Reminder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exerciseLL = findViewById(R.id.idLLExercise);
        stepCounterLL = findViewById(R.id.idLLStepCounter);
        reminderLL = findViewById(R.id.idLLReminder);
        waterLL = findViewById(R.id.idLLWater);
        musicPlayerLL = findViewById(R.id.idLLMusicPlayer);
        dietLL = findViewById(R.id.idLLDiet);
//        Reminder=findViewById(R.id.button);


        exerciseLAV  = findViewById(R.id.idLAVExercise);
        exerciseLAV.setAnimation(R.raw.exercise);
        exerciseLAV.loop(true);
        exerciseLAV.playAnimation();

        counterLAV = findViewById(R.id.idLAVStepCounter);
        counterLAV.setAnimation(R.raw.step);
        counterLAV.loop(true);
        counterLAV.playAnimation();

        reminderLAV = findViewById(R.id.idLAVReminder);
        reminderLAV.setAnimation(R.raw.reminder);
        reminderLAV.loop(true);
        reminderLAV.playAnimation();

        LAVWater = findViewById(R.id.idLAVWater);
        LAVWater.setAnimation(R.raw.water);
        LAVWater.loop(true);
        LAVWater.playAnimation();

        musicLAV = findViewById(R.id.idLAVPlayer);
        musicLAV.setAnimation(R.raw.headphones);
        musicLAV.loop(true);
        musicLAV.playAnimation();

        dietLAV = findViewById(R.id.idLAVDiet);
        dietLAV.setAnimation(R.raw.diet);
        dietLAV.loop(true);
        dietLAV.playAnimation();


        ImageView profileIcon = findViewById(R.id.idProfileIcon);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for the profile icon here
                // You can open a login dialog or a new login activity
                // For example, open a login activity
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });


//        exerciseLAV.setAnimationFromUrl("https://lottie.host/?file=07a53775-7a18-47c4-bc57-091722fd273e/WPzD0ArX7t.json");
//        counterLAV.setAnimationFromUrl("https://lottie.host/?file=07a53775-7a18-47c4-bc57-091722fd273e/WPzD0ArX7t.json");


//        LottieAnimationView exercise = findViewById(R.id.idLAVExercise);
//        exercise.setAnimation(R.raw.anime);
//        exercise.playAnimation();
//
//        LottieAnimationView counter = findViewById(R.id.idLAVStepCounter);
//        counter.setAnimation(R.raw.abs_crunches);
//        counter.playAnimation();

        exerciseLL.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Intent i= new Intent(MainActivity.this, ExerciseActivity.class);
                startActivity(i);
            }
        });
        stepCounterLL.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Intent i2 = new Intent(MainActivity.this, StepCounterActivity.class);
                startActivity(i2);
            }
        });

        waterLL.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Intent i3=new Intent(MainActivity.this,WaterReminder.class);
                startActivity(i3);
            }
        });

        reminderLL.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Intent i3=new Intent(MainActivity.this,ReminderPageActivity.class);
                startActivity(i3);
            }
        });

        musicPlayerLL.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Intent i3=new Intent(MainActivity.this,MusicPlayer.class);
                startActivity(i3);
            }
        });

        dietLL.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Intent i3=new Intent(MainActivity.this,DietActivityPlan.class);
                startActivity(i3);
            }
        });
    }
}