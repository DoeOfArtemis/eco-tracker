package com.example.ecotracker.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ecotracker.R;
import com.example.ecotracker.database.EcoTrackerDatabase;
import com.example.ecotracker.model.Task;
import com.example.ecotracker.model.User;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    static ListView listView;
    static CustomAdapter adapter;
    static EcoTrackerDatabase db;
    static ArrayList<Task> tasks;
    static ProgressBar progressBar;
    static int currentPoints;
    static User user;
    int courseId;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        db = EcoTrackerDatabase.getDatabase(this.getContext());
        String userNameFromLogin = getArguments().getString("userName");
        user = db.userDao().findByUserName(userNameFromLogin);
        courseId = db.courseDao().findCourseByUser(user.getUserName()).getId();

        setCourseTitle(view);
        getTasksListFromDB();
        int totalPointsOfCourse = getTotalPointsOfCourse();
        setProgressBarMax(view, totalPointsOfCourse);
        currentPoints = db.userDao().findByUserName(userNameFromLogin).getTotalPoints();
        progressBar.setProgress(currentPoints);

        adapter = new CustomAdapter(tasks, this.getContext());
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            }
        });
         */
        return view;
    }

    private void setProgressBarMax(View view, int totalPointsOfCourse) {
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarOfCourse);
        progressBar.setMax(totalPointsOfCourse);
    }

    private int getTotalPointsOfCourse() {
        int totalPointsOfCourse = 0;
        tasks = (ArrayList<Task>) db.taskDao().getAllByCourseId(courseId);
        for (Task task : tasks) {
            totalPointsOfCourse += task.getPoints();
        }
        return totalPointsOfCourse;
    }

    private void getTasksListFromDB() {
        tasks = (ArrayList) db.taskDao().getAllByCourseId(courseId);
    }

    private void setCourseTitle(View view) {
        TextView textView = (TextView) view.findViewById(R.id.courseTitle);

        textView.setText(db.courseDao().findCourseById(courseId).getName() + " course");
    }

    public static void removeItem(int position) {
        tasks.remove(position);
    }
    public static void updateItem(int position) {
        Task task = tasks.get(position);
        task.setCompleted(true);
        db.taskDao().update(task);
    }

    public static void getPoints(int position) {
        int points = tasks.get(position).getPoints();
        currentPoints += points;
        progressBar.setProgress(currentPoints);
        user.setTotalPoints(currentPoints);
        db.userDao().update(user);
        listView.setAdapter(adapter);
    }
}