package com.example.ecotracker.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ecotracker.MainActivity;
import com.example.ecotracker.R;
import com.example.ecotracker.dao.UserDao;
import com.example.ecotracker.database.EcoTrackerDatabase;
import com.example.ecotracker.model.Task;
import com.example.ecotracker.ui.neringosui.FirstNeringaActivity;
import com.example.ecotracker.ui.neringosui.SecondNeringaActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    EcoTrackerDatabase db;
    TextView textView;
    List<Task> tasks;
    ListView listView;
    private CustomAdapter adapter;

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

        listView = (ListView) view.findViewById(R.id.listView);
        tasks = db.taskDao().getAll();
        adapter = new CustomAdapter((ArrayList) tasks, this.getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Task task = (Task) tasks.get(position);
                task.setCompleted(!task.isCompleted());
                adapter.notifyDataSetChanged();
            }
        });


        return view;


    }
}