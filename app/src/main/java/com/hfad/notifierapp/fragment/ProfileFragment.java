package com.hfad.notifierapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfad.notifierapp.R;
import com.hfad.notifierapp.entity.User;
import com.hfad.notifierapp.storage.SharedPrefManager;

public class ProfileFragment extends Fragment {
    private TextView nameTV;
    private TextView surnameTV;
    private TextView emailTV;
    private TextView roleTV;
    private TextView indexTV;
    private TextView groupTV;
    private ImageView indexIV;
    private ImageView groupIV;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        getActivity().setTitle(R.string.profile);
        nameTV = view.findViewById(R.id.fragmentProfileNameTV);
        surnameTV = view.findViewById(R.id.fragmentProfileSurnameTV);
        emailTV = view.findViewById(R.id.fragmentProfileEmailTV);
        roleTV = view.findViewById(R.id.fragmentProfileRoleTV);
        indexTV = view.findViewById(R.id.fragmentProfileIndexTV);
        groupTV = view.findViewById(R.id.fragmentProfileGroupTV);
        indexIV = view.findViewById(R.id.fragmentProfileIndexIV);
        groupIV = view.findViewById(R.id.fragmentProfileGroupIV);


        user = SharedPrefManager.getInstance(getActivity()).getUser();

        nameTV.setText(user.getName());
        surnameTV.setText(user.getSurname());
        emailTV.setText(user.getEmail());
        if(user.getRole().equals("ROLE_TEACHER")){
            roleTV.setText(R.string.teacher);
            indexTV.setVisibility(View.INVISIBLE);
            groupTV.setVisibility(View.INVISIBLE);
            indexIV.setVisibility(View.INVISIBLE);
            groupIV.setVisibility(View.INVISIBLE);
        }else{
            roleTV.setText(R.string.student);
            indexTV.setVisibility(View.VISIBLE);
            indexTV.setText(String.valueOf(user.getIndexNumber()));
            groupTV.setVisibility(View.VISIBLE);
            groupTV.setText(user.getGroupNumber());
            indexIV.setVisibility(View.VISIBLE);
            groupIV.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
