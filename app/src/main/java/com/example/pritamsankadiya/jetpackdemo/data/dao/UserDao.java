package com.example.pritamsankadiya.jetpackdemo.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.pritamsankadiya.jetpackdemo.data.model.User;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    //Insert User
    @Insert(onConflict = REPLACE)
    void addUser(User user);

    //Get All Users
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();

    //Delete single users
    @Delete
    void deleteUser(User user);

    //Delete All Users
    @Query("DELETE FROM User")
    void deleteAll();

    //Update data
    @Update
    void updateTask(User user);
}
