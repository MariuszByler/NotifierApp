package com.hfad.notifierapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.hfad.notifierapp.R;
import com.hfad.notifierapp.entity.Body;
import com.hfad.notifierapp.entity.User;
import com.hfad.notifierapp.retrofit.RetrofitClient;
import com.hfad.notifierapp.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText registerUsernameET, registerPasswordET, registerEmailET, registerNameET, registerSurnameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUsernameET = findViewById(R.id.registerUsernameET);
        registerPasswordET = findViewById(R.id.registerPasswordET);
        registerEmailET = findViewById(R.id.registerEmailET);
        registerNameET = findViewById(R.id.registerNameET);
        registerSurnameET = findViewById(R.id.registerSurnameET);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            User user = SharedPrefManager.getInstance(this).getUser();
            FirebaseMessaging.getInstance().subscribeToTopic(String.valueOf(user.getIndexNumber()));
            FirebaseMessaging.getInstance().subscribeToTopic(user.getGroupNumber());
            Intent intent = new Intent(RegisterActivity.this,HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void onSignUpClick(View view) {
        register();
    }

    private void register() {
        String username = registerUsernameET.getText().toString().trim();
        String password = registerPasswordET.getText().toString().trim();
        String email = registerEmailET.getText().toString().trim();
        String name = registerNameET.getText().toString().trim();
        String surname = registerSurnameET.getText().toString().trim();

        if(username.isEmpty()){
            registerUsernameET.setError("Enter username");
            registerUsernameET.requestFocus();
            return;
        }
        if(password.isEmpty()){
            registerPasswordET.setError("Enter password");
            registerPasswordET.requestFocus();
            return;
        }
        if(email.isEmpty()){
            registerEmailET.setError("Enter E-mail");
            registerEmailET.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            registerEmailET.setError("Enter correct E-mail");
            registerEmailET.requestFocus();
            return;
        }
        if(name.isEmpty()){
            registerNameET.setError("Enter Name");
            registerNameET.requestFocus();
            return;
        }
        if(surname.isEmpty()){
            registerSurnameET.setError("Enter Surname");
            registerSurnameET.requestFocus();
            return;
        }

        User user = new User(username,password,email,name,surname,null,0,null);
        Call<Body> call = RetrofitClient.getInstance().getApi().addUser(user);
        call.enqueue(new Callback<Body>() {
            @Override
            public void onResponse(Call<Body> call, Response<Body> response) {
                Body msg = response.body();
                if(msg != null){
                    Toast.makeText(RegisterActivity.this,msg.getMessage(),Toast.LENGTH_SHORT).show();
                    if(msg.getNumber() == 0){
                        finish();
                    }
                    if(msg.getNumber() == 1){
                        registerUsernameET.setError(msg.getMessage());
                        registerUsernameET.requestFocus();
                    }
                    if(msg.getNumber() == 2){
                        registerEmailET.setError(msg.getMessage());
                        registerEmailET.requestFocus();
                    }

                }
                else{
                    Toast.makeText(RegisterActivity.this,response.errorBody().toString(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Body> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}
