package com.sanskruti.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Time;
import java.util.List;

public class ReminderPageActivity extends AppCompatActivity implements AlarmRVAdapter.OnSwitchChangeListener{

    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    ImageButton addAlarmButton;
    private List<ReminderRVModal> alarms;
    private EditText description;
    private RecyclerView recyclerView;
    private AlarmRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_page);

        alarmTimePicker = findViewById(R.id.idTimePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        addAlarmButton = findViewById(R.id.idAdd);
        description = findViewById(R.id.idDescription);

        alarms = new ArrayList<ReminderRVModal>();
        recyclerView = findViewById(R.id.idRecyclerView);
        adapter = new AlarmRVAdapter(alarms,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        addAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewAlarm();
                description.setText("");
            }
        });
    }

    public void addNewAlarm() {
        long time;

        Toast.makeText(ReminderPageActivity.this, "ALARM ON", Toast.LENGTH_SHORT).show();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

        Intent intent = new Intent(this, AlarmReciever.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));

        if (System.currentTimeMillis() > time) {
            if (Calendar.AM_PM == 0)
                time = time + (1000 * 60 * 60 * 12);
            else
                time = time + (1000 * 60 * 60 * 24);
        }

        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);

        String alarmDescription = description.getText().toString();
        int hour = alarmTimePicker.getCurrentHour();
        int minute = alarmTimePicker.getCurrentMinute();
        ReminderRVModal newAlarm = new ReminderRVModal(formatTime(hour, minute), alarmDescription, true);
        alarms.add(newAlarm);
        adapter.notifyDataSetChanged();
    }

    private String formatTime(int hour, int minute) {
        String time = "";

        if (hour < 10) {
            time += "0";
        }
        time += hour + ":";

        if (minute < 10) {
            time += "0";
        }
        time += minute;

        return time;
    }

    @Override
    public void onSwitchChanged(int position, boolean isChecked) {
        ReminderRVModal alarm = alarms.get(position);
        alarm.setActive(isChecked);

        if (!isChecked) {

            Toast.makeText(this, "Alarm switched off: " + alarm.getTime(), Toast.LENGTH_SHORT).show();
        } else {

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}