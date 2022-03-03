package com.example.ecotracker;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/* The issue seems to stem from here. Inactive icons and labels were fine.
    I tried looking for issues with step 7 of tutorial, because it was full of errors in the beginning, so
    I had to adjust my code. Good luck fishing for the bug :)
* */

//the OnItemSelectedListener was changed from the tutorial, android said it was deprecated
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener{

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //the method from the implementation
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.profile);
    }

    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();

    /* This is where it gets really confusing. Remember that deprecated method?
    Android didn't like it so it reverted to inherit the method from the tutorial
    But then also I had to replace the R.id.container from GFG to R.id.frameLayout because
    it had no idea what container was. As I understand, the replaced object has to be the same
    so it SHOULD work...theoretically :)
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, firstFragment).commit();
                return true;

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, secondFragment).commit();
                return true;

            case R.id.courses:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, thirdFragment).commit();
                return true;
        }
        return false;
    }
}