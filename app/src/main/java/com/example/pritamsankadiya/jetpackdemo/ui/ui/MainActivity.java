package com.example.pritamsankadiya.jetpackdemo.ui.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pritamsankadiya.jetpackdemo.R;
import com.example.pritamsankadiya.jetpackdemo.data.model.User;
import com.example.pritamsankadiya.jetpackdemo.databinding.ActivityMainBinding;
import com.example.pritamsankadiya.jetpackdemo.ui.adapter.MainUserAdapter;
import com.example.pritamsankadiya.jetpackdemo.ui.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*  @BindView(R.id.firstName)
      EditText etfirstName;
      @BindView(R.id.lastName)
      EditText etlastName;
      @BindView(R.id.email)
      EditText etemail;
      @BindView(R.id.mobile)
      EditText etmobile;
      @BindView(R.id.age)
      EditText etAge;
  */
    MainViewModel mainViewModel;
    private List<User> userList;
    private ActivityMainBinding activityMainBinding;
    public MyClickHandlers handlers;
    User userEditext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        userEditext = new User();
        handlers = new MyClickHandlers(this);
        activityMainBinding.setHandlers(handlers);
        activityMainBinding.setUser(userEditext);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

     //   ButterKnife.bind(this);

        activityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //Initialize the View Model in your layout
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getUserList().observe(this, users -> {
            if (MainActivity.this.userList == null) {
                setListData(users);
            }
        });
    }

    private void saveUser(String firstName, String lastName, String email, String mobile, String age) {
        mainViewModel.addUser(new User(firstName, lastName, email, mobile, age));
    }

    public void setListData(final List<User> userList) {
        this.userList = userList;
        if (userList.isEmpty()) {
            //Handle No User here - Like show an empty view
        } else {
            MainUserAdapter adapter =
                    new MainUserAdapter(userList, this);
            activityMainBinding.recyclerView.setAdapter(adapter);
        }
    }

    public class MyClickHandlers {
        Context context;

        public MyClickHandlers(Context context) {
            this.context = context;
        }
        //Save a new User
        public void onButtonClick(View view) {
            String firstName = userEditext.getFirstName();
            String lastName = userEditext.getLastName();
            String email = userEditext.getEmail();
            String mobile = userEditext.getMobile();
            String age = userEditext.getAge();

            if (firstName.isEmpty()) {
                activityMainBinding.firstName.setError("Please Enter First Name");
                return;
            }

            if (lastName.isEmpty()) {
                activityMainBinding.lastName.setError("Please Enter Last Name");
                return;
            }

            if (email.isEmpty()) {
                activityMainBinding.email.setError("Please Enter Email ID");
                return;
            }

            if (mobile.isEmpty()) {
                activityMainBinding.mobile.setError("Please Enter Mobile No.");
                return;
            }

            if (age.isEmpty()) {
                activityMainBinding.age.setError("Please Enter Your Age");
                return;
            }

            saveUser(firstName, lastName, email, mobile, age);
            Toast.makeText(getApplicationContext(), "Items Inserted in DB!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        //Delete All Users
        public void onButtonClickDeleteAll(View view) {
            mainViewModel.deleteAll();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
