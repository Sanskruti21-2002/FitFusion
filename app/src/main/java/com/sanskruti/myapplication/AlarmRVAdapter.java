package com.sanskruti.myapplication;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AlarmRVAdapter extends RecyclerView.Adapter<AlarmRVAdapter.AlarmViewHolder> {
    private List<ReminderRVModal> alarms;
    private OnSwitchChangeListener switchChangeListener;

    public interface OnSwitchChangeListener {
        void onSwitchChanged(int position, boolean isChecked);
    }

    public AlarmRVAdapter(List<ReminderRVModal> alarms, OnSwitchChangeListener switchChangeListener) {
        this.alarms = alarms;
        this.switchChangeListener = switchChangeListener;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_item, parent, false);
        return new AlarmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        ReminderRVModal alarm = alarms.get(position);
        holder.timeTextView.setText(alarm.getTime());
        holder.descriptionTextView.setText(alarm.getDescription());
        holder.isActiveSwitch.setChecked(alarm.isActive());

        // Handle switch state changes
        holder.isActiveSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switchChangeListener.onSwitchChanged(position, isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }

    public void removeAlarm(int position) {
        alarms.remove(position);
        notifyItemRemoved(position);
    }

    public static class AlarmViewHolder extends RecyclerView.ViewHolder {
        TextView timeTextView;
        TextView descriptionTextView;
        Switch isActiveSwitch;

        public AlarmViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.idAlarmTime);
            descriptionTextView = itemView.findViewById(R.id.idAlarmDescription);
            isActiveSwitch = itemView.findViewById(R.id.idAlarmSwitch);
        }
    }
}