package com.phantom.painttogether.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.phantom.painttogether.R;
import com.phantom.painttogether.databinding.LoginActivityBinding;
import com.phantom.painttogether.viewmodel.LoginViewModel;
import com.phantom.painttogether.viewmodel.PaintScreenViewModel;

/**
 * Created by sev_user on 9/21/2016.
 */

public class LoginActivity extends AppCompatActivity {
    public LoginActivityBinding binding;
    public LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.login_activity);
        loginViewModel = new LoginViewModel(this);
        binding.setViewModel(loginViewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
