package com.example.ecotracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnGoRegister = findViewById(R.id.register_button);
        btnGoRegister.setOnClickListener(view ->
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class)));
    }
}