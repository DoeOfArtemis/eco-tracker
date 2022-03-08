package com.example.ecotracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ecotracker.model.Car;
import com.example.ecotracker.model.User;

import java.util.List;

@Dao
public interface CarDao {

    @Insert
    void insert(Car car);

    @Delete
    void delete(Car car);

    @Update
    void update(Car car);

    @Query("SELECT * FROM cars")
    List<Car> getAll();

    @Query("SELECT * FROM cars WHERE id LIKE :carId")
    Car findCarById(int carId);
}
