package com.example.ecotracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecotracker.database.EcoTrackerDatabase;
import com.example.ecotracker.databinding.ActivityMainBinding;
import com.example.ecotracker.ui.courses.CoursesFragment;
import com.example.ecotracker.ui.home.HomeFragment;
import com.example.ecotracker.ui.profile.ProfileFragment;

public class LoginActivity extends AppCompatActivity {

    EcoTrackerDatabase db;
    EditText userName, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = EcoTrackerDatabase.getDatabase(getApplicationContext());

        // Introducing references from LOGIN page.
        userName = findViewById(R.id.inputUsername);
        password = findViewById(R.id.inputPassword);
        Button loginButton = (Button) findViewById(R.id.login_button);
        Button registerButton = (Button) findViewById(R.id.register_button);



        // go to Register page
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((db.userDao().findByUserName(userName.getText().toString()) != null) &&
                        (password.getText().toString().equals(db.userDao().findByUserName(userName.getText().toString()).getPassword()))) {
                    userName = findViewById(R.id.inputUsername);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Bundle userNameBundle = new Bundle();
                    userNameBundle.putString("userName", userName.getText().toString());
                    intent.putExtras(userNameBundle);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, "Login unsuccessful!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}