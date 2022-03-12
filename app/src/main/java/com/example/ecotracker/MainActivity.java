package com.example.ecotracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ecotracker.database.EcoTrackerDatabase;
import com.example.ecotracker.databinding.ActivityMainBinding;
import com.example.ecotracker.model.User;
import com.example.ecotracker.ui.courses.CoursesFragment;
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

                    Bundle userNameBundle = new Bundle();
                    userNameBundle.putString("userName", userName.getText().toString());

                    ProfileFragment profileFragmentFirst = new ProfileFragment();
                    profileFragmentFirst.setArguments(userNameBundle);
                    replaceFragment(profileFragmentFirst);

                    binding.bottomNavigationView.setOnItemSelectedListener(item ->{

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

                } else {
                    Toast.makeText(MainActivity.this, "Login unsuccessful!", Toast.LENGTH_SHORT).show();
                }
            }
        });



        // Start of NAVIGATION part after user successfully logged in

    }

    //method to replace current fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}
