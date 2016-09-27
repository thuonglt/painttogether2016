package com.phantom.painttogether.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.phantom.painttogether.view.CreateProjectPaintActivity;
import com.phantom.painttogether.view.LoginActivity;
import com.phantom.painttogether.view.MainActivity;

/**
 * Created by sev_user on 9/23/2016.
 */

public class MainViewModel {
    public static String TAG = MainViewModel.class.getSimpleName();
    public Context context;
    public Activity activity;

    public MainViewModel(Context context) {
        this.context = context;
        activity = (Activity) context;
    }

    public void onClickAddProject(View view) {
        Intent intent = new Intent(activity, CreateProjectPaintActivity.class);
        activity.startActivity(intent);
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
