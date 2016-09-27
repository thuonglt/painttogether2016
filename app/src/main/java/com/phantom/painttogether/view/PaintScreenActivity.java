package com.phantom.painttogether.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.phantom.painttogether.R;
import com.phantom.painttogether.data.ProjectPaint;
import com.phantom.painttogether.databinding.PaintScreenActivityBinding;
import com.phantom.painttogether.viewmodel.PaintScreenViewModel;

public class PaintScreenActivity extends AppCompatActivity {
    PaintView paintView;
    private PaintScreenViewModel paintScreenViewModel;
    PaintScreenActivityBinding binding;
    public static String KEY_NODE;
    public ProjectPaint model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            model = (ProjectPaint) bundle.getSerializable("MODEL");
            KEY_NODE = bundle.getString("KEY_NODE");
        }

        binding = DataBindingUtil.setContentView(this, R.layout.paint_screen_activity);
        paintScreenViewModel = new PaintScreenViewModel(this, model);
        binding.setViewModel(paintScreenViewModel);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public ProjectPaint getModel() {
        return model;
    }

}
