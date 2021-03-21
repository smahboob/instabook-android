package com.android.instabook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class LauncherActivity extends AppCompatActivity {

    Button login,signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        signUp = findViewById(R.id.signUpLauncherButton);
        login = findViewById(R.id.loginLauncherButton);

        login.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
        });

        signUp.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), SignUp.class);
            startActivity(i);
        });

    }



}