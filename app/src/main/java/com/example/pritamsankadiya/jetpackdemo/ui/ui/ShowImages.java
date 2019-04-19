package com.example.pritamsankadiya.jetpackdemo.ui.ui;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.pritamsankadiya.jetpackdemo.MyApplication;
import com.example.pritamsankadiya.jetpackdemo.R;
import com.example.pritamsankadiya.jetpackdemo.data.model.MultiView;
import com.example.pritamsankadiya.jetpackdemo.data.model.MultiViewResponse;
import com.example.pritamsankadiya.jetpackdemo.data.utils.ApiResponseImages;
import com.example.pritamsankadiya.jetpackdemo.data.utils.Constant;
import com.example.pritamsankadiya.jetpackdemo.data.utils.ViewModelFactory;
import com.example.pritamsankadiya.jetpackdemo.databinding.ActivityShowImagesBinding;
import com.example.pritamsankadiya.jetpackdemo.ui.adapter.ShowImageAdapter;
import com.example.pritamsankadiya.jetpackdemo.ui.viewmodel.LoginViewModel;

import javax.inject.Inject;

public class ShowImages extends AppCompatActivity implements ShowImageAdapter.PostsAdapterListener {

    @Inject
    ViewModelFactory viewModelFactory;

    ActivityShowImagesBinding binding;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager linearLayoutManager;
    LoginViewModel viewModel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_images);

        progressDialog = Constant.getProgressDialog(this, "Please wait...");

        ((MyApplication) getApplication()).getAppComponent().doInjection(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        viewModel.imageResponse().observe(this, this::consumeImageResponse);
        viewModel.hitImagesApi();

        initRecyclerView();
    }

    private void initRecyclerView() {

        recyclerView = binding.recyclerView;
        linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
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
                Toast.makeText(ShowImages.this, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
    private void renderImageSuccessResponse(MultiViewResponse response) {
        if (response.getMultiView().size() > 0) {
            Log.d("Image response=", response.toString());
            adapter = new ShowImageAdapter(response.getMultiView(), this);
            recyclerView.setAdapter(adapter);
          /*  Intent i = new Intent();
            i.setClass(LoginActivity.this, ShowImages.class);
            this.startActivity(i);*/
        } else {
            Toast.makeText(ShowImages.this, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPostClicked(MultiView post,int position) {
        Toast.makeText(getApplicationContext(), "Post clicked! " + position, Toast.LENGTH_SHORT).show();
    }
}
