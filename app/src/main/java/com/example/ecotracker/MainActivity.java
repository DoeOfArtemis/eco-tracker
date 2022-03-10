package com.example.ecotracker;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ecotracker.databinding.ActivityMainBinding;
import com.example.ecotracker.ui.courses.CoursesFragment;
import com.example.ecotracker.ui.home.HomeFragment;
import com.example.ecotracker.ui.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Button firstCourseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
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
    }

    //method to replace current fragment
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    }
