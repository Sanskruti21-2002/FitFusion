package com.sanskruti.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Locale;

public class ExerciseDetailActivity extends AppCompatActivity {

    private TextView exerciseNameTV, caloriesTV, timeTV, descTV;
    private LottieAnimationView exerciseLAV;
    private String exerciseName, desc;
    private int calories, time, id;
    private CountDownTimer exerciseTimer;
    TextView countdownTimerTV;
    MediaPlayer alarmMediaPlayer;
    private boolean isTimerRunning = false;
    private long timeRemaining ;
    FloatingActionButton fab;
    VideoView videoView;


    private final int[] videoFiles = {R.raw.push_up_tut, R.raw.lunges_tut, R.raw.video3, R.raw.abs_crunches, R.raw.push_up_tut};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);

        exerciseNameTV = findViewById(R.id.idTVExerciseName);
        caloriesTV = findViewById(R.id.idTVCalories);
        timeTV = findViewById(R.id.idTVTime);
        descTV = findViewById(R.id.idTVDescription);
        exerciseLAV = findViewById(R.id.idExerciseLAV);
        countdownTimerTV = findViewById(R.id.idCounter);
        fab = findViewById(R.id.idFAB);
        videoView = findViewById(R.id.videoView);

        exerciseName = getIntent().getStringExtra("exerciseName");
        desc = getIntent().getStringExtra("desc");
        id = getIntent().getIntExtra("Exerciseid", 0);
        calories = getIntent().getIntExtra("calories", 0);
        time = getIntent().getIntExtra("time", 0);

        exerciseNameTV.setText(exerciseName);
        caloriesTV.setText("Calories: " + calories);
        timeTV.setText("Time: " + time + " Min");
        descTV.setText(desc);
        exerciseLAV.setAnimation(id);
        exerciseLAV.loop(true);
        exerciseLAV.playAnimation();
        alarmMediaPlayer = MediaPlayer.create(this, R.raw.song1);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!isTimerRunning) {
                    startCountdownTimer(isTimerRunning ? timeRemaining : time * 60 * 1000);
                    countdownTimerTV.setText("Time Left: " + formatTime(timeRemaining));
                    isTimerRunning = true;
                    fab.setImageResource(R.drawable.ic_pause);
                    videoView.start();
                    videoView.setVisibility(View.VISIBLE);
                } else {
                    isTimerRunning = false;
                    exerciseTimer.cancel();
                    fab.setImageResource(R.drawable.ic_play);
                    videoView.setVisibility(View.GONE);
                    videoView.pause();
                }
            }
        });

        int videoIndex = getIntent().getIntExtra("videoIndex", 0);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + videoFiles[videoIndex]);
        videoView.setVideoURI(videoUri);
        videoView.requestFocus();
    }

    private void startCountdownTimer(long millisInFuture) {
        exerciseTimer = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;
                countdownTimerTV.setText("Time Left: " + formatTime(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                countdownTimerTV.setText("Time's up!");
                alarmMediaPlayer.start();
                fab.setImageResource(R.drawable.ic_play);
                isTimerRunning = false;
            }
        }.start();
    }

    private String formatTime(long millis) {
        long secondsUntilFinished = millis / 1000;
        long minutes = secondsUntilFinished / 60;
        long seconds = secondsUntilFinished % 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (alarmMediaPlayer != null) {
            alarmMediaPlayer.release();
        }
        if (exerciseTimer != null) {
            exerciseTimer.cancel();
        }
    }
}
