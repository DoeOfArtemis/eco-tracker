package com.example.ecotracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.ecotracker.intermediate_data.CourseWithTasks;
import com.example.ecotracker.intermediate_data.UserWithCourses;
import com.example.ecotracker.model.Car;
import com.example.ecotracker.model.Course;
import com.example.ecotracker.model.User;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert
    void insert(Course course);

    @Delete
    void delete(Course course);

    @Update
    void update(Course course);

    @Query("SELECT * FROM courses")
    List<Course> getAll();

    @Query("SELECT * FROM courses WHERE id LIKE :courseId")
    Course findCourseById(int courseId);

    @Query("SELECT * FROM courses WHERE userName LIKE :userName")
    Course findCourseByUser(String userName);

    @Transaction
    @Query("SELECT * FROM courses")
    List<CourseWithTasks> getCourseWithTasks();
}
