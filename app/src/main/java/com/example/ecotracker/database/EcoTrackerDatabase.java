package com.example.ecotracker.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.ecotracker.dao.CarDao;
import com.example.ecotracker.dao.CourseDao;
import com.example.ecotracker.dao.RewardDao;
import com.example.ecotracker.dao.TaskDao;
import com.example.ecotracker.dao.UserDao;
import com.example.ecotracker.model.Car;
import com.example.ecotracker.model.Course;
import com.example.ecotracker.model.Reward;
import com.example.ecotracker.model.Task;
import com.example.ecotracker.model.User;
import com.example.ecotracker.ui.home.HomeFragment;

@Database(entities = {User.class, Car.class, Reward.class, Course.class, Task.class}, exportSchema = false, version = 1)
public abstract class EcoTrackerDatabase extends RoomDatabase {

    private static final String DB_NAME = "eco_tracker_database";
    private static  EcoTrackerDatabase ecoTrackerDatabase;

    public static synchronized EcoTrackerDatabase getDatabase(Context context) {
        if(ecoTrackerDatabase == null) {
            ecoTrackerDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    EcoTrackerDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    //.fallbackToDestructiveMigration()
                    .createFromAsset("database/eco_tracker.db")
                    .build();
        }
        return ecoTrackerDatabase;
    }

    public abstract UserDao userDao();
    public abstract CarDao carDao();
    public abstract RewardDao rewardDao();
    public abstract CourseDao courseDao();
    public abstract TaskDao taskDao();
}
