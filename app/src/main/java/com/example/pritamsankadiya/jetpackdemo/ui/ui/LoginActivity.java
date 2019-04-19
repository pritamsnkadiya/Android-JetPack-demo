package com.example.pritamsankadiya.jetpackdemo.ui.ui;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pritamsankadiya.jetpackdemo.MyApplication;
import com.example.pritamsankadiya.jetpackdemo.R;
import com.example.pritamsankadiya.jetpackdemo.data.model.MultiViewResponse;
import com.example.pritamsankadiya.jetpackdemo.data.utils.ApiResponse;
import com.example.pritamsankadiya.jetpackdemo.data.utils.ApiResponseImages;
import com.example.pritamsankadiya.jetpackdemo.data.utils.Constant;
import com.example.pritamsankadiya.jetpackdemo.data.utils.ViewModelFactory;
import com.example.pritamsankadiya.jetpackdemo.ui.viewmodel.LoginViewModel;
import com.google.gson.JsonElement;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.phone_no)
    EditText phoneNo;
    @BindView(R.id.password)
    EditText password;

    LoginViewModel viewModel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = Constant.getProgressDialog(this, "Please wait...");

        ButterKnife.bind(this);

        ((MyApplication) getApplication()).getAppComponent().doInjection(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

        viewModel.loginResponse().observe(this, this::consumeResponse);

        // viewModel.imageResponse().observe(this, this::consumeImageResponse);
    }

    @OnClick(R.id.login)
    void onLoginClicked() {
        if (isValid()) {
            if (!Constant.checkInternetConnection(this)) {
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
            } else {
                viewModel.hitLoginApi();
            }
        }
    }

    @OnClick(R.id.click_me)
    void onClickeMe() {
        if (true) {
            if (!Constant.checkInternetConnection(this)) {
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
            } else {
                //  viewModel.hitImagesApi();
                Intent i = new Intent();
                i.setClass(LoginActivity.this, ShowImages.class);
                this.startActivity(i);
            }
        }
    }

    @OnClick(R.id.click_me_sign)
    void OnclickSign() {
        Intent i = new Intent();
        i.setClass(LoginActivity.this, DigitalSignature.class);
        this.startActivity(i);
    }

    //method to validate $(mobile number) and $(password)
    private boolean isValid() {

        if (phoneNo.getText().toString().trim().isEmpty()) {
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.enter_valid_mobile), Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.getText().toString().trim().isEmpty()) {
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.enter_valid_password), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //method to handle response

    private void consumeResponse(ApiResponse apiResponse) {

        switch (apiResponse.status) {

            case LOADING:
                progressDialog.show();
                break;

            case SUCCESS:
                progressDialog.dismiss();
                renderSuccessResponse(apiResponse.data);
                break;

            case ERROR:
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
/*
    private void consumeImageResponse(ApiResponseImages apiResponse) {

        switch (apiResponse.status) {

            case LOADING:
                progressDialog.show();
                break;

            case SUCCESS:
                progressDialog.dismiss();
                renderImageSuccessResponse(apiResponse.data);
                break;

            case ERROR:
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }*/

    // method to handle success response
    private void renderSuccessResponse(JsonElement response) {
        if (!response.isJsonNull()) {
            Log.d("response=", response.toString());
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.successString), Toast.LENGTH_SHORT).show();
            Intent i = new Intent();
            i.setClass(LoginActivity.this, MainActivity.class);
            this.startActivity(i);
        } else {
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
        }
    }

   /* private void renderImageSuccessResponse(MultiViewResponse response) {
        if (response.getMultiView().size() > 0) {
            Log.d("Image response=", response.toString());
            Intent i = new Intent();
            i.setClass(LoginActivity.this, ShowImages.class);
            this.startActivity(i);
        } else {
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
        }
    }*/
}
