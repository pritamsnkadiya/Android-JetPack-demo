package com.example.pritamsankadiya.jetpackdemo.ui.adapter;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pritamsankadiya.jetpackdemo.R;
import com.example.pritamsankadiya.jetpackdemo.data.model.User;
import com.example.pritamsankadiya.jetpackdemo.databinding.RowUserBinding;
import com.example.pritamsankadiya.jetpackdemo.ui.ui.MainActivity;
import com.example.pritamsankadiya.jetpackdemo.ui.viewmodel.MainViewModel;

import java.util.List;

import timber.log.Timber;

public class MainUserAdapter extends RecyclerView.Adapter<MainUserAdapter.ViewHolder> {

    private List<User> userList;
    private Context context;
    private User user;
    private MainViewModel mainViewModel;

    public MainUserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
        mainViewModel = ViewModelProviders.of((FragmentActivity) context).get(MainViewModel.class);
    }

    @Override
    public MainUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_user, parent,
                false);

        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.rowUserBinding.setUser(user);
    }

    @Override
    public int getItemCount() {
        Timber.d("Users are : " + userList.size());
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RowUserBinding rowUserBinding;

        ViewHolder(RowUserBinding rowUserBinding) {
            super(rowUserBinding.getRoot());
            this.rowUserBinding = rowUserBinding;

            rowUserBinding.getRoot().findViewById(R.id.delete).setOnClickListener(v -> {
                Toast.makeText(context, "Deleted ID " + userList.get(getAdapterPosition()).getId(), Toast.LENGTH_SHORT).show();
                mainViewModel.deleteUser(userList.get(getAdapterPosition()));
                Intent i = new Intent();
                i.setClass(context, MainActivity.class);
                context.startActivity(i);

            });

            rowUserBinding.getRoot().findViewById(R.id.update).setOnClickListener(v -> {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.layout_update_data, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                EditText fName = (EditText) promptsView.findViewById(R.id.firstName);
                EditText lName = (EditText) promptsView.findViewById(R.id.lastName);
                EditText emaill = (EditText) promptsView.findViewById(R.id.email);
                EditText agee = (EditText) promptsView.findViewById(R.id.age);
                EditText mobilee = (EditText) promptsView.findViewById(R.id.mobile);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Update",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        String firstName = fName.getText().toString().trim();
                                        String lastName = lName.getText().toString().trim();
                                        String email = emaill.getText().toString().trim();
                                        String mobile = mobilee.getText().toString().trim();
                                        String age = agee.getText().toString().trim();

                                        user = new User(firstName, lastName, email, mobile, age);
                                        mainViewModel.updateUser(user);
                                        Toast.makeText(context, "Update ID " + userList.get(getAdapterPosition()).getId(), Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent();
                                        i.setClass(context, MainActivity.class);
                                        context.startActivity(i);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
            });
        }
    }
}