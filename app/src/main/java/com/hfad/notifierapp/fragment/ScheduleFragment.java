package com.hfad.notifierapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.hfad.notifierapp.R;
import com.hfad.notifierapp.adapter.ScheduleAdapter;
import com.hfad.notifierapp.entity.Schedule;
import com.hfad.notifierapp.retrofit.RetrofitClient;
import com.hfad.notifierapp.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFragment extends Fragment {
    private ListView scheduleLV;
    private ScheduleAdapter scheduleAdapter;
    private ArrayList<Schedule> scheduleList;
    private ArrayList<Schedule> schedules;
    private static ScheduleFragment instance;
    private Button searchButton;
    private EditText searchET;

    public static ScheduleFragment getInstance() {
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule,container,false);
        getActivity().setTitle(R.string.scheduleNotification);
        instance = this;
        searchET = view.findViewById(R.id.fragmentScheduleET);
        searchButton = view.findViewById(R.id.fragmentScheduleSearchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSearchClick();
            }
        });
        scheduleLV = view.findViewById(R.id.fragmentScheduleLV);
        scheduleList = new ArrayList<>();
        schedules = new ArrayList<>();
        scheduleAdapter = new ScheduleAdapter(view.getContext(),scheduleList);
        scheduleLV.setAdapter(scheduleAdapter);
        getSchedules();
        return view;
    }

    private void onSearchClick() {
        String searchText = searchET.getText().toString().trim();

        Pattern pattern = Pattern.compile(searchText.toLowerCase());

        scheduleList.clear();
        for(Schedule schedule: schedules) {
            Matcher matcher = pattern.matcher(schedule.getSubject().toLowerCase());
            if (matcher.find()) {
                scheduleList.add(schedule);
            }
        }
        scheduleAdapter.notifyDataSetChanged();
        searchET.onEditorAction(EditorInfo.IME_ACTION_DONE);
        searchET.setText("");
    }

    public void getSchedules() {
        Call<ArrayList<Schedule>> call = RetrofitClient.getInstance().getApi().getSchedules(SharedPrefManager.getInstance(getActivity()).getToken());

        call.enqueue(new Callback<ArrayList<Schedule>>() {
            @Override
            public void onResponse(Call<ArrayList<Schedule>> call, Response<ArrayList<Schedule>> response) {
                if(response.isSuccessful()){
                    schedules = response.body();
                    scheduleList.clear();
                    for (Schedule schedule : schedules){
                        scheduleList.add(schedule);
                    }
                    scheduleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Schedule>> call, Throwable t) {

            }
        });
    }
}
