package com.android.instabook;

import androidx.appcompat.app.AppCompatActivity;
import com.parse.ParseUser;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    EditText usernameText, passwordText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null){
            goToMainActivity();
        }

        usernameText = findViewById(R.id.login_username);
        passwordText = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String username = usernameText.getText().toString();
            String password = passwordText.getText().toString();
            loginUser(username, password);
        });
    }

    private void loginUser(String username, String password) {
        ParseUser.logInInBackground(username, password, (user, e) -> {
            if(e != null){
                Toast.makeText(this, "Error Logging in!", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Logged in Successfully!", Toast.LENGTH_LONG).show();
            goToMainActivity();
        });
    }

    private void goToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}