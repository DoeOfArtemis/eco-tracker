package com.example.ecotracker.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rewards")
public class Reward {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String message;
    private String userName;

    public Reward() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
