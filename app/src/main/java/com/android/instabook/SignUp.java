package com.android.instabook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity {

    EditText username, email, password;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.signUp_username);
        password = findViewById(R.id.signUp_password);
        email = findViewById(R.id.signUp_email);
        signUp = findViewById(R.id.signUpButton);

        signUp.setOnClickListener(v -> {
            ParseUser user = new ParseUser();
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            user.setEmail(email.getText().toString());

            user.signUpInBackground(e -> {
                if(e != null){
                    Toast.makeText(this, "Error in registering user!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, LauncherActivity.class);
                startActivity(i);
                finish();
            });

        });
    }
}