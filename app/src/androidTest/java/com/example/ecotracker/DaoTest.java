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
import com.example.ecotracker.intermediate_data.CourseWithTasks;
import com.example.ecotracker.intermediate_data.UserWithCars;
import com.example.ecotracker.model.Car;
import com.example.ecotracker.model.Course;
import com.example.ecotracker.model.Task;
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
    private CourseDao courseDao;
    private TaskDao taskDao;
    private User user1;
    private User user2;
    private User user3;
    private Car car1;
    private Car car2;
    private Course course1;
    private Task task1;
    private Task task2;
    private Task task3;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, EcoTrackerDatabase.class).build();

        userDao = db.userDao();
        carDao = db.carDao();
        courseDao = db.courseDao();
        taskDao = db.taskDao();

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

        course1 = new Course();
        course1.setId(1);

        task1 = new Task();
        task1.setPoints(5);
        task2 = new Task();
        task2.setPoints(10);
        task3 = new Task();
        task3.setPoints(15);
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

    @Test
    public void relationshipBetweenCourseAndTask() {
        courseDao.insert(course1);
        task1.setCourseId(course1.getId());
        task2.setCourseId(course1.getId());
        task3.setCourseId(course1.getId());
        taskDao.insert(task1);
        taskDao.insert(task2);
        taskDao.insert(task3);

        List<CourseWithTasks> courseWithTasksList = courseDao.getCourseWithTasks();

        List<Task> taskList = null;
        for (CourseWithTasks courseWithTasks : courseWithTasksList) {
            if(courseWithTasks.getCourse().getId() == 1) {
                taskList = courseWithTasks.getTaskList();
            }
        }
        int result = taskList.size();
        Assert.assertEquals(3, result);

    }

}

