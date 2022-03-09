package com.example.ecotracker.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.ecotracker.MainActivity;
import com.example.ecotracker.R;
import com.example.ecotracker.dao.UserDao;
import com.example.ecotracker.database.EcoTrackerDatabase;
import com.example.ecotracker.ui.neringosui.FirstNeringaActivity;
import com.example.ecotracker.ui.neringosui.SecondNeringaActivity;

public class HomeFragment extends Fragment {

    EcoTrackerDatabase db;
    TextView textView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        db = EcoTrackerDatabase.getDatabase(this.getContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        textView = (TextView) view.findViewById(R.id.courseTitle);
        textView.setText(db.courseDao().findCourseById(1).getName() + " course");

        return view;


    }
}