package com.example.ecotracker.ui.neringosui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ecotracker.R;
import com.example.ecotracker.dao.UserDao;
import com.example.ecotracker.database.EcoTrackerDatabase;
import com.example.ecotracker.model.User;

public class FirstNeringaActivity extends AppCompatActivity {

    EcoTrackerDatabase db;
    UserDao userDao;
    Button button;
    EditText editText;
    String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        db = EcoTrackerDatabase.getDatabase(this);
        userDao = db.userDao();
        editText = findViewById(R.id.input);
        button = findViewById(R.id.button_add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstNeringaActivity.this, SecondNeringaActivity.class);
                string = editText.getText().toString();
                User user = new User();
                user.setUserName(string);
                user.setPassword("password");
                userDao.insert(user);
                startActivity(intent);
            }
        });

    }
}
