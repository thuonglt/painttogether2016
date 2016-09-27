package com.phantom.painttogether.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phantom.painttogether.R;
import com.phantom.painttogether.data.ProjectPaint;
import com.phantom.painttogether.utils.Ultils;
import com.phantom.painttogether.view.ChooseBackgroundAcitivity;
import com.phantom.painttogether.view.CreateProjectPaintActivity;

/**
 * Created by sev_user on 9/23/2016.
 */

public class CreateProjectPaintViewModel {
    public ObservableInt idBackgroundProject, resIDBackground;
    public ObservableField<String> nameProject;
    public Context context;
    public Activity activity;
    public DatabaseReference databaseReference;
    public FirebaseAuth firebaseAuth;

    public CreateProjectPaintViewModel(Context context) {
        this.context = context;
        activity = (Activity) context;
        idBackgroundProject = new ObservableInt();
        resIDBackground = new ObservableInt(R.drawable.bg_rainbow);
        nameProject = new ObservableField<>("");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void onClickSubmitProject(View view) {
        Log.d("phantom", "onClickSubmitProject  name: " + nameProject.get() + " idBkg: " + idBackgroundProject.get());
        String mAuthor = firebaseAuth.getCurrentUser().getEmail();
        ProjectPaint projectPaint = new ProjectPaint(mAuthor, idBackgroundProject.get(), nameProject.get());
        databaseReference.child("projects").push().setValue(projectPaint);
        activity.finish();
    }

    public void onClickChooseImage(View view) {
        Intent intent = new Intent(activity, ChooseBackgroundAcitivity.class);
        activity.startActivityForResult(intent, CreateProjectPaintActivity.REQUEST_CODE);
    }

    public void setIdBackgroundProject(int idBackgroundProject) {
        this.idBackgroundProject.set(idBackgroundProject);
        resIDBackground.set(Ultils.arrBackground[idBackgroundProject]);
    }
}
