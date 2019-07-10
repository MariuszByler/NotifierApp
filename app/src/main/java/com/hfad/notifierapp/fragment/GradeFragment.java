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
import com.hfad.notifierapp.adapter.GradeAdapter;
import com.hfad.notifierapp.entity.Grade;
import com.hfad.notifierapp.retrofit.RetrofitClient;
import com.hfad.notifierapp.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GradeFragment extends Fragment {
    private ListView gradeLV;
    private GradeAdapter gradeAdapter;
    private ArrayList<Grade> gradesList;
    private ArrayList<Grade> grades;
    private static GradeFragment instance;
    private Button searchButton;
    private EditText searchET;

    public static GradeFragment getInstance() {
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grade,container,false);
        getActivity().setTitle(R.string.gradesNotification);
        instance = this;
        searchET = view.findViewById(R.id.fragmentGradeET);
        searchButton = view.findViewById(R.id.fragmentGradeSearchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSearchClick();
            }
        });
        gradeLV = view.findViewById(R.id.fragmentGradeLV);
        gradesList = new ArrayList<>();
        grades = new ArrayList<>();
        gradeAdapter = new GradeAdapter(view.getContext(),gradesList);
        gradeLV.setAdapter(gradeAdapter);
        getGrades();
        return view;
    }

    private void onSearchClick() {
        String searchText = searchET.getText().toString().trim();

        Pattern pattern = Pattern.compile(searchText.toLowerCase());

        gradesList.clear();
        for(Grade grade : grades) {
            Matcher matcher = pattern.matcher(grade.getSubject().toLowerCase());
            if (matcher.find()) {
                gradesList.add(grade);
            }
        }
            gradeAdapter.notifyDataSetChanged();
            searchET.onEditorAction(EditorInfo.IME_ACTION_DONE);
            searchET.setText("");
    }


    public void getGrades(){
        Call<ArrayList<Grade>> call = RetrofitClient.getInstance().getApi().getGrades(SharedPrefManager.getInstance(getActivity()).getToken());

        call.enqueue(new Callback<ArrayList<Grade>>() {
            @Override
            public void onResponse(Call<ArrayList<Grade>> call, Response<ArrayList<Grade>> response) {
                if(response.isSuccessful()){
                    grades = response.body();
                    gradesList.clear();
                    for (Grade grade : grades){
                        gradesList.add(grade);
                    }
                    gradeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Grade>> call, Throwable t) {

            }
        });
    }
}
