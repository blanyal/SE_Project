package com.se_project.arlingtonparking;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface  UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE username IN (:userNames)")
    List<User> loadAllByIds(int[] userNames);

    @Query("SELECT * FROM user WHERE username LIKE :first LIMIT 1")
    User getUser(String first);

    @Query("SELECT * FROM user WHERE lastn LIKE :first")
    List<User> searchUser(String first);

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User user);
}
