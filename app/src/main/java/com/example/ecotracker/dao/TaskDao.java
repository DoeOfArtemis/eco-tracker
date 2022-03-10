package com.example.ecotracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ecotracker.model.Reward;
import com.example.ecotracker.model.Task;
import com.example.ecotracker.model.User;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Update
    void update(Task task);

    @Query("SELECT * FROM tasks")
    List<Task> getAll();

    @Query("SELECT * FROM tasks WHERE courseId LIKE :courseId")
    List<Task> getAllByCourseId(int courseId);

    @Query("SELECT * FROM tasks WHERE courseId LIKE :courseId AND isCompleted = 0")
    List<Task> getAllNotCompletedByCourseId(int courseId);

    @Query("SELECT * FROM tasks WHERE id LIKE :taskId")
    Task findTaskById(int taskId);
}
