package com.example.ecotracker.ui.home;

import android.app.Activity;
import android.content.Context;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

    static EcoTrackerDatabase db;
    TextView textView;
    static ArrayList<Task> tasksNotCompleted;
    List<Task> allTasksInCourse;
    static ListView listView;
    static CustomAdapter adapter;
    static ProgressBar progressBar;
    static int totalPoints;
    int courseId = 1;

    Context context = this.getContext();

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
        textView.setText(db.courseDao().findCourseById(courseId).getName() + " course");


        tasksNotCompleted = (ArrayList) db.taskDao().getAllNotCompletedByCourseId(courseId);

        int totalPointsOfCourse = 0;
        allTasksInCourse = db.taskDao().getAllByCourseId(courseId);
        for (Task task : allTasksInCourse) {
            totalPointsOfCourse += task.getPoints();
        }

        progressBar = (ProgressBar) view.findViewById(R.id.progressBarOfCourse);
        progressBar.setMax(totalPointsOfCourse);
        int progress = 0;

        adapter = new CustomAdapter(tasksNotCompleted, this.getContext());
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               Task task = (Task) tasksNotCompleted.get(position);
               task.setCompleted(!task.isCompleted());

                String clickedItem = (String) listView.getItemAtPosition(position);
                Toast.makeText(context, clickedItem, Toast.LENGTH_SHORT).show();

                db.taskDao().findTaskById(task.getId()).setCompleted(true);
                db.taskDao().update(task);

                adapter.notifyDataSetChanged();


            }
        });
        return view;
    }

    public static void removeItem(int position) {
        tasksNotCompleted.remove(position);
    }
    public static void updateItem(int position) {
        Task task = tasksNotCompleted.get(position);
        task.setCompleted(true);
        db.taskDao().update(task);
    }

    public static void getPoints(int position) {
        int points = tasksNotCompleted.get(position).getPoints();
        totalPoints += points;
        progressBar.setProgress(totalPoints);
        listView.setAdapter(adapter);
    }
}