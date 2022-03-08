package com.example.ecotracker.intermediate_data;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.ecotracker.model.Course;
import com.example.ecotracker.model.Reward;
import com.example.ecotracker.model.User;

import java.util.List;

public class UserWithRewards {
    @Embedded
    private User user;

    @Relation(parentColumn = "userName", entityColumn = "userName", entity = Reward.class)
    public List<Reward> rewardList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Reward> getRewardList() {
        return rewardList;
    }

    public void setRewardList(List<Reward> rewardList) {
        this.rewardList = rewardList;
    }
}
