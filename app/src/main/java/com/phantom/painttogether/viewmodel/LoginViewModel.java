package com.phantom.painttogether.viewmodel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phantom.painttogether.view.LoginActivity;
import com.phantom.painttogether.view.MainActivity;
import com.phantom.painttogether.view.PaintScreenActivity;

/**
 * Created by sev_user on 9/21/2016.
 */

public class LoginViewModel {
    private Context context;
    public ObservableField<String> txtEmail;
    public ObservableField<String> txtPassword;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabaseReference;
    private Activity mainActivity;
    private ProgressDialog mProgressDialog;
    public static String TAG = "Phantom";

    public LoginViewModel(Context context) {
        this.context = context;
        mainActivity = (Activity) context;
        initialized();
    }

    public void initialized() {
        txtEmail = new ObservableField<>("");
        txtPassword = new ObservableField<>("");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser != null) {
            startActivityMain();
        }
    }

    public void onClickSignin(View view) {
        if (!validateForm()) {
            return;
        }
        showProgressDialog();
        String email = txtEmail.get();
        String password = txtPassword.get();
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signIn:onComplete:" + task.isSuccessful());
                        hideProgressDialog();

                        if (task.isSuccessful()) {
                            startActivityMain();
                        } else {
                            showToast("Sign In Failed");
                        }
                    }
                });
    }

    public void onClickSignup(View view) {
        if (!validateForm()) {
            return;
        }
        showProgressDialog();
        String email = txtEmail.get();
        String password = txtPassword.get();
        Log.d(TAG, "data: " + email + "-" + password);
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                        hideProgressDialog();

                        if (task.isSuccessful()) {
                            startActivityMain();
                        } else {
                            showToast("Sign Up Failed");
                        }
                    }
                });
    }

    public void startActivityMain() {
        Intent intent = new Intent(mainActivity, MainActivity.class);
        context.startActivity(intent);
        mainActivity.finish();
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mainActivity);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(txtEmail.get())) {
            showToast("Email not null!");
            result = false;
        }
        if (TextUtils.isEmpty(txtPassword.get())) {
            showToast("Password not null!");
            result = false;
        }
        if (txtPassword.get().trim().length() < 6) {
            showToast("Password must be at least 6 characters!");
            result = false;
        }
        return result;
    }

    private void showToast(String mess) {
        Toast.makeText(mainActivity, mess,
                Toast.LENGTH_SHORT).show();
    }
}
