package com.example.ecotracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.ecotracker.dao.CarDao;
import com.example.ecotracker.dao.CourseDao;
import com.example.ecotracker.dao.RewardDao;
import com.example.ecotracker.dao.TaskDao;
import com.example.ecotracker.dao.UserDao;
import com.example.ecotracker.database.EcoTrackerDatabase;
import com.example.ecotracker.intermediate_data.UserWithCars;
import com.example.ecotracker.model.Car;
import com.example.ecotracker.model.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DaoTest {

    private EcoTrackerDatabase db;
    private UserDao userDao;
    private CarDao carDao;
    private User user1;
    private User user2;
    private User user3;
    private Car car1;
    private Car car2;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, EcoTrackerDatabase.class).build();

        userDao = db.userDao();
        carDao = db.carDao();

        user1 = new User();
        user1.setUserName("Lukas");
        user1.setPassword("Poi");
        user2 = new User();
        user2.setUserName("Luka");
        user2.setPassword("Pol");
        user3 = new User();
        user3.setUserName("Luke");
        user3.setPassword("Pok");

        car1 = new Car();
        car1.setModel("Toyota");
        car2 = new Car();
        car2.setModel("Tesla");
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void insertToDatabase() {
        userDao.insert(user1);
        userDao.insert(user2);
        userDao.insert(user3);
        int actual = userDao.getAll().size();
        Assert.assertEquals(3, actual);
    }

    @Test
    public void deleteFromDatabase() {
        userDao.insert(user1);
        userDao.insert(user2);
        userDao.insert(user3);
        userDao.delete(userDao.findByUserName("Luke"));
        int result = userDao.getAll().size();
        Assert.assertEquals(2, result);
    }

    @Test
    public void readFromDatabase() {
        userDao.insert(user1);
        User foundUser = userDao.findByUserName("Lukas");
        Assert.assertEquals("Poi", foundUser.getPassword());
    }

    @Test
    public void relationshipBetweenUserAndCars() {
        userDao.insert(user1);
        car1.setUserName(user1.getUserName());
        car2.setUserName(user1.getUserName());
        carDao.insert(car1);
        carDao.insert(car2);

        List<UserWithCars> userWithCarsList = userDao.getUserWithCars();

        List<Car> carList = null;
        for (UserWithCars userWithCars : userWithCarsList) {
            if(userWithCars.getUser().getUserName().equals("Lukas")) {
                carList = userWithCars.getCarList();
            }
        }
        int result = carList.size();
        Assert.assertEquals(2, result);

    }

}

