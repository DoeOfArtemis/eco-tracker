package com.example.ecotracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.ecotracker.database.EcoTrackerDatabase;
import com.example.ecotracker.databinding.ActivityMainBinding;
import com.example.ecotracker.ui.courses.CoursesFragment;
import com.example.ecotracker.ui.courses.FirstCourseFragment;
import com.example.ecotracker.ui.courses.SecondCourseFragment;
import com.example.ecotracker.ui.home.HomeFragment;
import com.example.ecotracker.ui.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    EcoTrackerDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = EcoTrackerDatabase.getDatabase(getApplicationContext());

        // Introducing references from LOGIN page.
        TextView userName =(TextView) findViewById(R.id.inputUsername);
        TextView password = (TextView) findViewById(R.id.inputPassword);
        Button loginButton = (Button) findViewById(R.id.login_button);
        Button registerButton = (Button) findViewById(R.id.register_button);


        // Introducing references from REGISTER page
        TextView newUserName = (TextView) findViewById(R.id.inputUsername);
        TextView newName = (TextView) findViewById(R.id.input_new_name);
        TextView newEmail = (TextView) findViewById(R.id.input_new_email);
        TextView newPassword = (TextView) findViewById(R.id.inputPassword);
        Button registerNewUser = (Button) findViewById(R.id.add_new_user);

        // go to Register page
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_register);
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });

        // password setting - username (admin) and password (admin)
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((db.userDao().findByUserName(userName.getText().toString()) != null) &&
                        (password.getText().toString().equals(db.userDao().findByUserName(userName.getText().toString()).getPassword()))) {
                    setContentView(R.layout.activity_main);
                    binding = ActivityMainBinding.inflate(getLayoutInflater());
                    setContentView(binding.getRoot());
                    replaceFragment(new ProfileFragment());
                    binding.bottomNavigationView.setOnItemSelectedListener(item ->{

                        switch (item.getItemId()) {

                            case R.id.profile:
                                replaceFragment(new ProfileFragment());
                                break;

                            case R.id.home:
                                replaceFragment(new HomeFragment());
                                break;

                            case R.id.courses:
                                replaceFragment(new CoursesFragment());
                                break;

                        }

                        return true;
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Login unsuccessful!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //method to replace current fragment from navigation bar
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    //methods to click on a course to view it
    public void viewCourse(View view) {
        switch (view.getId()) {
            case R.id.how_to_recycle:
                replaceFragment(new FirstCourseFragment());
                break;
            case R.id.zero_waste_basics:
                replaceFragment(new SecondCourseFragment());
                break;
        }
    }

}
