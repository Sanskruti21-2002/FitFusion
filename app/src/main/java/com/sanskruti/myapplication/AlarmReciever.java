package com.sanskruti.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.os.Handler;
import android.widget.Toast;
public class AlarmReciever extends BroadcastReceiver {
    private Vibrator vibrator;
    @Override
    public void onReceive(Context context, Intent intent) {
// Display a toast message
        Toast.makeText(context, "Wake up! Alarm triggered",
                Toast.LENGTH_LONG).show();
// Vibrate the device
        vibrator = (Vibrator)
                context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(new long[]{1000, 1000, 1000, 1000}, 0); // Vibrate


// Stop vibration after 10 seconds (10000 milliseconds)
            stopVibrationAfterDelay(10000);
        }
// Play the default alarm sound

        Uri alarmUri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri =

                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        if (ringtone != null) {
            ringtone.play();
        }
    }
    // Method to stop vibration after a specified delay
    private void stopVibrationAfterDelay(int delay) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (vibrator != null) {
                    vibrator.cancel(); // Stop the ongoing vibration
                }
            }
        }, delay);
    }
}
