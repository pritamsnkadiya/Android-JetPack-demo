package com.example.pritamsankadiya.jetpackdemo.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.pritamsankadiya.jetpackdemo.data.Database;
import com.example.pritamsankadiya.jetpackdemo.data.model.User;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Database appDatabase;
    private final LiveData<List<User>> userList;

    public MainViewModel(@NonNull Application application) {
        super(application);
        appDatabase = Database.getDatabase(this.getApplication());
        //Initialize User List
        userList = appDatabase.userDao().getAllUsers();
    }

    //Add New User
    public void addUser(User user) {
        new addAsyncTask(appDatabase).execute(user);
    }

    // Get Users
    public LiveData<List<User>> getUserList() {
        return userList;
    }

    // Delete Users
    public void deleteUser(User user) {
        new deleteAsyncTask(appDatabase).execute(user);
    }

    // Delete All Users from DB
    public void deleteAll() {
        new deleteAllAsyncTask(appDatabase).execute();
    }
    // Delete Users
    public void updateUser(User user) {
        new updateAsyncTask(appDatabase).execute(user);
    }

    private static class addAsyncTask extends AsyncTask<User, Void, Void> {

        private Database db;

        addAsyncTask(Database appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final User... params) {
            db.userDao().addUser(params[0]);
            return null;

        }
    }

    private static class deleteAsyncTask extends AsyncTask<User, Void, Void> {

        private Database db;

        deleteAsyncTask(Database appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final User... params) {
            try {
                db.userDao().deleteUser(params[0]);
                Log.e("Deleted", "deleted");

            } catch (Exception e) {
                Log.d("Error ", "is " + e.getMessage());
            }
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<User, Void, Void> {

        private Database db;

        deleteAllAsyncTask(Database appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final User... params) {
            try {
                db.userDao().deleteAll();
                Log.e("Deleted All", "deleted ");

            } catch (Exception e) {
                Log.d("Error ", "is " + e.getMessage());
            }
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<User, Void, Void> {

        private Database db;

        updateAsyncTask(Database appDatabase) {
            db = appDatabase;
        }
        @Override
        protected Void doInBackground(final User... params) {
            try {
                db.userDao().updateTask(params[0]);
            } catch (Exception e) {
                Log.d("Error ", "is " + e.getMessage());
            }
            return null;
        }
    }
}
