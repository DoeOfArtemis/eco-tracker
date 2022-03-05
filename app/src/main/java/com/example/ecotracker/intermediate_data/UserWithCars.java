package com.example.ecotracker.intermediate_data;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.ecotracker.model.Car;
import com.example.ecotracker.model.User;

import java.util.List;

public class UserWithCars {

    @Embedded
    private User user;

    @Relation(parentColumn = "userName", entityColumn = "userName")
    public List<Car> carList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }


}
