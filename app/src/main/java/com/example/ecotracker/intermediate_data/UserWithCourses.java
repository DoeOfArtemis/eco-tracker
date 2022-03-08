package com.example.ecotracker.intermediate_data;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.ecotracker.model.Course;
import com.example.ecotracker.model.User;

import java.util.List;

public class UserWithCourses {

    @Embedded
    private User user;

    @Relation(parentColumn = "userName", entityColumn = "userName", entity = Course.class)
    public List<Course> courseList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
