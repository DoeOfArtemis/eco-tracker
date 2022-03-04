package com.example.ecotracker.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.ecotracker.dao.UserDao;
import com.example.ecotracker.model.User;

@Database(entities = {User.class}, exportSchema = false, version = 1)
public abstract class EcoTrackerDatabase extends RoomDatabase {

    private static final String DB_NAME = "eco_tracker_database";
    private static  EcoTrackerDatabase ecoTrackerDatabase;

    public static synchronized EcoTrackerDatabase getDatabase(Context context) {
        if(ecoTrackerDatabase == null) {
            ecoTrackerDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    EcoTrackerDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return ecoTrackerDatabase;
    }

    public abstract UserDao userDao();
}
