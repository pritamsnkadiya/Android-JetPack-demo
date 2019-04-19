package com.example.pritamsankadiya.jetpackdemo.data;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.pritamsankadiya.jetpackdemo.data.dao.UserDao;
import com.example.pritamsankadiya.jetpackdemo.data.model.User;

@android.arch.persistence.room.Database(
        entities = {User.class},
        version = 1
)

public abstract class Database extends RoomDatabase {

    private static Database INSTANCE;

    public static Database getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, "jetpack_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();
}
