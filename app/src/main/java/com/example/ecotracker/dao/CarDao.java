package com.example.ecotracker.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ecotracker.model.Car;
import com.example.ecotracker.model.User;

import java.util.List;

@Dao
public interface CarDao {

    @Insert
    void insert(Car car);

    @Query("SELECT * FROM cars")
    List<Car> getAll();
}
