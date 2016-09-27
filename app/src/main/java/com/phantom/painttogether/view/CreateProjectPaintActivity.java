package com.phantom.painttogether.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.phantom.painttogether.R;
import com.phantom.painttogether.databinding.ActivityCreateProjectPaintBinding;
import com.phantom.painttogether.utils.Ultils;
import com.phantom.painttogether.viewmodel.CreateProjectPaintViewModel;

public class CreateProjectPaintActivity extends AppCompatActivity {
    public static int RESULT_CODE = 111;
    public static int REQUEST_CODE = 100;
    public ImageView imageView;
    public CreateProjectPaintViewModel viewModel;
    public ActivityCreateProjectPaintBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_project_paint);
        viewModel = new CreateProjectPaintViewModel(this);
        binding.setViewModel(viewModel);
        imageView = (ImageView) findViewById(R.id.imv_background);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_CODE) {
            int idBkg = data.getIntExtra("data", 0);
            viewModel.setIdBackgroundProject(idBkg);
        }
    }
}
