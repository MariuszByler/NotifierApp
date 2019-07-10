package com.hfad.notifierapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.messaging.FirebaseMessaging;
import com.hfad.notifierapp.R;
import com.hfad.notifierapp.entity.User;
import com.hfad.notifierapp.fragment.GradeFragment;
import com.hfad.notifierapp.fragment.ChatFragment;
import com.hfad.notifierapp.fragment.ProfileFragment;
import com.hfad.notifierapp.fragment.ScheduleFragment;
import com.hfad.notifierapp.storage.SharedPrefManager;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChatFragment()).commit();

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_message:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChatFragment()).commit();
                    break;
                case R.id.nav_grade:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GradeFragment()).commit();
                    break;
                case R.id.nav_schedule:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ScheduleFragment()).commit();
                    break;
                case R.id.nav_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                    break;
                case R.id.nav_logout:
                    logout();
            }
            return true;
        }
    };

    private void logout() {
        User user = SharedPrefManager.getInstance(this).getUser();
        FirebaseMessaging.getInstance().unsubscribeFromTopic(String.valueOf(user.getIndexNumber()));
        if(user.getGroupNumber() != null){
            FirebaseMessaging.getInstance().subscribeToTopic(user.getGroupNumber());
        }
        SharedPrefManager.getInstance(this).clear();
        Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}