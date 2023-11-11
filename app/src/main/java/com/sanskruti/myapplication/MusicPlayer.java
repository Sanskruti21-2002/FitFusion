package com.sanskruti.myapplication;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.SeekBar;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UnstableApi;

public class MusicPlayer extends AppCompatActivity {
    private VideoView videoView;
    private TextView songTitle, singerName;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    private ImageButton previousButton, playPauseButton, nextButton;
    private int currentSongIndex = 0;   //starting from 1st song
    private boolean isPlaying = false;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable updateSeekBar;

    private final String[] songTitles = {"Unstoppable", "Believer", "Who Says", "Drag Me Down", "Wavin Flag"};
    private final String[] singerNames = {"Sia", "Imagine Dragons", "Selena Gomez", "One Direction", "K'NAAN"};
    private final int[] audioFiles = {R.raw.song1, R.raw.song2,R.raw.song3,R.raw.song4,R.raw.song5};
    private final int[] videoFiles = {R.raw.video1, R.raw.video2,R.raw.video3,R.raw.video4,R.raw.video5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        videoView = findViewById(R.id.idvideoView);   //for the video
        songTitle = findViewById(R.id.idsongTitle);
        singerName = findViewById(R.id.idsingerName);
        previousButton = findViewById(R.id.idpreviousButton);
        playPauseButton = findViewById(R.id.idplayPauseButton);
        nextButton = findViewById(R.id.idnextButton);

        mediaPlayer =  MediaPlayer.create(this,audioFiles[currentSongIndex]);
//        videoView.setMediaController((new MediaController(this)));

        //initialize the seekbar
        seekBar = findViewById(R.id.idSeekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        updateSeekBar = new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null && isPlaying)
                {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    seekBar.setProgress(currentPosition);
                }
                handler.postDelayed(this,1000);
            }
        };

        setSong(currentSongIndex);
        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPlaying)
                {
                    mediaPlayer.start();
                    videoView.start();
                    playPauseButton.setImageResource(R.drawable.ic_pause);
                    isPlaying=true;

                    //start the SeekBar update
                    handler.post(updateSeekBar);
                }
                else
                {
                    mediaPlayer.pause();
                    videoView.pause();
                    playPauseButton.setImageResource(R.drawable.ic_play);
                    isPlaying=false;
                    //stop the SeekBar uopdate
                    handler.removeCallbacks(updateSeekBar);
                }
            }
        });
//        setSong(currentSongIndex);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playNextSong();
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playPreviousSong();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            //after completion of song, next will play
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                playNextSong();
            }
        });

        //seekbar functionlaity
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser)
            {
                if(fromUser)
                {
                    mediaPlayer.seekTo(progress);
                    videoView.seekTo(progress);
                }
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar){}
        });

    }

    private void  playNextSong() {
        if (currentSongIndex <= songTitles.length-1) {
            currentSongIndex++;
        }
        else {
            currentSongIndex=0;   //loop

        }


//        Log.d("MusicPlayer", "Playing next song: " + songTitles[currentSongIndex]);
        setSong(currentSongIndex);
        videoView.start();
        if(mediaPlayer!=null)
        {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, audioFiles[currentSongIndex]);
        mediaPlayer.start();
        isPlaying=true;
        playPauseButton.setImageResource((R.drawable.ic_pause));
    }

    private void playPreviousSong() {
        if (currentSongIndex > 0) {
            currentSongIndex--;
        }
        else
        {
            currentSongIndex=songTitles.length-1;
        }
        setSong(currentSongIndex);
        videoView.start();
        if(mediaPlayer!=null)
        {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, audioFiles[currentSongIndex]);
        mediaPlayer.start();
        isPlaying=true;
        playPauseButton.setImageResource((R.drawable.ic_pause));
    }

    private void setSong(int index) {
        songTitle.setText(songTitles[index]);     //sets title
        singerName.setText(singerNames[index]);   //seta name
        videoView.stopPlayback();                 //stops earlier video

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + videoFiles[index]);
        videoView.setVideoURI(videoUri);
        videoView.requestFocus();

        if(isPlaying)
        {
            videoView.start();                   //starts current video
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
        playPauseButton.setImageResource(R.drawable.ic_play);
        isPlaying=false;
    }
}