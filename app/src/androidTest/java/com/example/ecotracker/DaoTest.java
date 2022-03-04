package com.example.ecotracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.ecotracker.dao.UserDao;
import com.example.ecotracker.database.EcoTrackerDatabase;
import com.example.ecotracker.model.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class DaoTest {

    private UserDao userDao;
    private EcoTrackerDatabase db;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, EcoTrackerDatabase.class).build();
        userDao = db.userDao();
        user1 = new User();
        user1.setUserName("Lukas");
        user1.setPassword("Poi");
        user2 = new User();
        user2.setUserName("Luka");
        user2.setPassword("Pol");
        user3 = new User();
        user3.setUserName("Luke");
        user3.setPassword("Pok");
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
}

