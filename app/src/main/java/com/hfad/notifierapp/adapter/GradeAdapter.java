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
import com.hfad.notifierapp.entity.Grade;

import java.util.ArrayList;

public class GradeAdapter extends ArrayAdapter<Grade> {

    public GradeAdapter(Context context, ArrayList<Grade> grades) {
        super(context, R.layout.grade_row, grades);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.grade_row,parent,false);

        Grade grade = getItem(position);
        TextView gradeSubjectTV = view.findViewById(R.id.gradeSubjectTV);
        TextView gradeGradeTV = view.findViewById(R.id.gradeGradeTV);
        TextView gradeTypeTV = view.findViewById(R.id.gradeTypeTV);
        TextView gradeTeacherTV = view.findViewById(R.id.gradeTeacherTV);

        gradeSubjectTV.setText(grade.getSubject());
        gradeGradeTV.setText(String.valueOf(grade.getGrade()));
        gradeTypeTV.setText(grade.getType());
        gradeTeacherTV.setText(grade.getTeacherName()+ " "+grade.getTeacherSurname());

        return view;
    }
}
