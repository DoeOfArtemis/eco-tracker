package com.example.ecotracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.ecotracker.intermediate_data.UserWithCars;
import com.example.ecotracker.model.User;
import java.util.List;


@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE userName LIKE :userName")
    User findByUserName(String userName);

    @Transaction
    @Query("SELECT * FROM users")
    List<UserWithCars> getUserWithCars();


}
