package com.example.ecotracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.ecotracker.database.EcoTrackerDatabase;
import com.example.ecotracker.databinding.ActivityMainBinding;
import com.example.ecotracker.model.Task;
import com.example.ecotracker.ui.courses.CoursesFragment;
import com.example.ecotracker.ui.courses.FirstCourseFragment;
import com.example.ecotracker.ui.courses.SecondCourseFragment;
import com.example.ecotracker.ui.home.HomeFragment;
import com.example.ecotracker.ui.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    EcoTrackerDatabase db;
    Bundle userNameBundle;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get info from Login page
        userNameBundle = getIntent().getExtras();
        userNameBundle.getString("userName", "Default");

        db = EcoTrackerDatabase.getDatabase(getApplicationContext());

        // Start of NAVIGATION part after user successfully logged in
        ProfileFragment profileFragmentFirst = new ProfileFragment();
        profileFragmentFirst.setArguments(userNameBundle);
        replaceFragment(profileFragmentFirst);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.profile:
                    ProfileFragment profileFragment = new ProfileFragment();
                    profileFragment.setArguments(userNameBundle);
                    replaceFragment(profileFragment);
                    break;

                case R.id.home:
                    HomeFragment homeFragment = new HomeFragment();
                    homeFragment.setArguments(userNameBundle);
                    replaceFragment(homeFragment);

                    break;

                case R.id.courses:
                    replaceFragment(new CoursesFragment());
                    break;

            }

            return true;
        });

    }

    //method to replace current fragment from navigation bar
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @SuppressLint("NonConstantResourceId")
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

    public void addTasks(View view) {
        for (Task task: db.taskDao().getAllByCourseId(1)) {
            task.setCompleted(false);
            task.setInProgress(true);
            db.taskDao().update(task);
        }
    }

}
