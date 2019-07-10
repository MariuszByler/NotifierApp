package com.hfad.notifierapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hfad.notifierapp.R;
import com.hfad.notifierapp.entity.Schedule;

import java.util.ArrayList;

public class ScheduleAdapter extends ArrayAdapter<Schedule> {

    public ScheduleAdapter(@NonNull Context context, ArrayList<Schedule> schedules) {
        super(context, R.layout.schedule_row, schedules);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.schedule_row,parent,false);

        Schedule schedule = getItem(position);
        TextView scheduleSubjectTV = view.findViewById(R.id.scheduleSubjectTV);
        TextView scheduleGroupTV = view.findViewById(R.id.scheduleGroupTV);
        TextView scheduleNewDateTV = view.findViewById(R.id.scheduleNewDateTV);
        TextView scheduleOldDateTV = view.findViewById(R.id.scheduleOldDateTV);

        scheduleSubjectTV.setText(schedule.getSubject());
        scheduleGroupTV.setText(schedule.getGroupNumber());
        scheduleNewDateTV.setText(schedule.getNewDate());
        scheduleOldDateTV.setText(schedule.getOldDate());

        return view;
    }
}
