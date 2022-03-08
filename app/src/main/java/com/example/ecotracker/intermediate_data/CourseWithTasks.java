package com.example.ecotracker.intermediate_data;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.ecotracker.model.Course;
import com.example.ecotracker.model.Task;

import java.util.List;

public class CourseWithTasks {

    @Embedded
    private Course course;

    @Relation(parentColumn = "id", entityColumn = "courseId",  entity = Task.class)
    public List<Task> taskList;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
