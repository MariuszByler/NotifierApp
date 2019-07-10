package com.hfad.notifierapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.hfad.notifierapp.R;
import com.hfad.notifierapp.entity.User;
import com.hfad.notifierapp.retrofit.RetrofitClient;
import com.hfad.notifierapp.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private EditText loginUsernameET, loginPasswordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsernameET = findViewById(R.id.loginUsernameET);
        loginPasswordET = findViewById(R.id.loginPasswordET);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            User user = SharedPrefManager.getInstance(this).getUser();
            FirebaseMessaging.getInstance().subscribeToTopic(String.valueOf(user.getIndexNumber()));
            if(user.getGroupNumber() != null){
                FirebaseMessaging.getInstance().subscribeToTopic(user.getGroupNumber());
            }
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void onLoginClick(View view) {
        login();
    }



    public void onSignUpClick(View view) {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    private void login() {
        String username = loginUsernameET.getText().toString().trim();
        String password = loginPasswordET.getText().toString().trim();

        if(username.isEmpty()){
            loginUsernameET.setError("Enter username");
            loginUsernameET.requestFocus();
            return;
        }
        if(password.isEmpty()){
            loginPasswordET.setError("Enter password");
            loginPasswordET.requestFocus();
            return;
        }

        String base = username +":"+password;

        final String autthHead = "Basic "+ Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);

        Call<User> call = RetrofitClient.getInstance().getApi().login(autthHead);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if(user != null){
                    SharedPrefManager.getInstance(LoginActivity.this).saveUser(user);
                    SharedPrefManager.getInstance(LoginActivity.this).saveToken(autthHead);
                    FirebaseMessaging.getInstance().subscribeToTopic(String.valueOf(user.getIndexNumber()));
                    if(user.getGroupNumber() != null){
                        FirebaseMessaging.getInstance().subscribeToTopic(user.getGroupNumber());
                    }
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else{
                    Toast.makeText(LoginActivity.this,"Login failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }
}
