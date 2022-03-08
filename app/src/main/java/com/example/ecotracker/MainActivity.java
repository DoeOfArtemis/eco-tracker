package com.example.ecotracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.ecotracker.databinding.ActivityMainBinding;
import com.example.ecotracker.ui.courses.CoursesFragment;
import com.example.ecotracker.ui.home.HomeFragment;
import com.example.ecotracker.ui.profile.ProfileFragment;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        // Introducing references from LOGIN page.
        TextView userName =(TextView) findViewById(R.id.inputUsername);
        TextView password = (TextView) findViewById(R.id.inputPassword);
        Button loginButton = (Button) findViewById(R.id.login_button);
        Button registerButton = (Button) findViewById(R.id.register_button);

        // password setting - username (admin) and password (admin)
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userName.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
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



        // Start of NAVIGATION part after user successfully logged in

    }

    //method to replace current fragment
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    }
