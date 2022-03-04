package com.example.ecotracker.ui.neringosui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ecotracker.R;
import com.example.ecotracker.dao.UserDao;
import com.example.ecotracker.database.EcoTrackerDatabase;
import com.example.ecotracker.model.User;

import java.util.List;

public class SecondNeringaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    EcoTrackerDatabase db;
    UserDao userDao;
    List<User> userList;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_neringa);

        db = EcoTrackerDatabase.getDatabase(this);
        userDao = db.userDao();
        userList = userDao.getAll();

        String[] names = new String[userList.size()];
        int index = 0;
        for (User user : userList) {
            names[index] = user.getUserName();
            index++;
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(this, names);
        recyclerView.setAdapter(recyclerAdapter);

        button = findViewById(R.id.button_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondNeringaActivity.this, FirstNeringaActivity.class);
                startActivity(intent);
            }
        });
    }
}
