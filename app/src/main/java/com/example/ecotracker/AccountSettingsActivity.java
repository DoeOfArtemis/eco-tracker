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
import com.example.ecotracker.ui.home.HomeFragment;
import com.example.ecotracker.ui.profile.ProfileFragment;

public class AccountSettingsActivity extends AppCompatActivity {

    EcoTrackerDatabase db;
    TextView userName;
    EditText password, name, email;
    Button saveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        db = EcoTrackerDatabase.getDatabase(getApplicationContext());

        Intent intent = getIntent();
        String userNameString = intent.getStringExtra("user");
        User user = db.userDao().findByUserName(userNameString);

        userName = findViewById(R.id.account_settings_userName);
        userName.setText(user.getUserName());
        password = findViewById(R.id.account_settings_password);
        password.setText(user.getPassword());
        name = findViewById(R.id.account_settings_name);
        name.setText(user.getName());
        email = findViewById(R.id.account_settings_email);
        email.setText(user.getEmail());

        saveChanges = findViewById(R.id.save_account_changes_button);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user.setPassword(password.getText().toString());
                user.setName(name.getText().toString());
                user.setEmail(email.getText().toString());
                db.userDao().update(user);
                finish();

            }
        });
    }
}