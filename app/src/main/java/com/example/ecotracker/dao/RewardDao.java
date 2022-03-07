package com.example.ecotracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ecotracker.model.Course;
import com.example.ecotracker.model.Reward;
import com.example.ecotracker.model.User;

import java.util.List;

@Dao
public interface RewardDao {

    @Insert
    void insert(Reward reward);

    @Delete
    void delete(Reward reward);

    @Update
    void update(Reward reward);

    @Query("SELECT * FROM rewards")
    List<Reward> getAll();

    @Query("SELECT * FROM rewards WHERE id LIKE :rewardId")
    Reward findRewardById(int rewardId);
}
