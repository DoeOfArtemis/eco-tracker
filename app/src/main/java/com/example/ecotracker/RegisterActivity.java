package com.example.ecotracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ecotracker.database.EcoTrackerDatabase;
import com.example.ecotracker.model.User;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText name;
    EditText email;
    EditText password;
    Button registerNewUser, goBackToLogin;
    EcoTrackerDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        db = EcoTrackerDatabase.getDatabase(getApplicationContext());
        username = findViewById(R.id.input_new_username);
        name = findViewById(R.id.input_new_name);
        email = findViewById(R.id.input_new_email);
        password = findViewById(R.id.input_new_password);
        registerNewUser = findViewById(R.id.add_new_user);
        goBackToLogin = findViewById(R.id.goBackToLogin);

        goBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });


        registerNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setUserName(username.getText().toString());
                user.setName(name.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                user.setLevel("Little green sprout");
                db.userDao().insert(user);

                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        });
    }
}