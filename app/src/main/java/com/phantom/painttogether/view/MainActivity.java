package com.phantom.painttogether.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phantom.painttogether.R;
import com.phantom.painttogether.data.ProjectPaint;
import com.phantom.painttogether.databinding.ActivityMainBinding;
import com.phantom.painttogether.utils.Ultils;
import com.phantom.painttogether.view.viewholder.PaintProjectViewHolder;
import com.phantom.painttogether.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<ProjectPaint, PaintProjectViewHolder> mFirebaseAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String PROJECT_PAINT_CHILD = "projects";
    final PaintProjectViewHolder.ViewClickListenner itemClickListenner = new PaintProjectViewHolder.ViewClickListenner() {
        @Override
        public void onClick(ProjectPaint projectPaint, String keyNode) {
            startPaintScreenActivity(projectPaint, keyNode);
        }
    };

    ActivityMainBinding binding;
    MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        model = new MainViewModel(this);
        binding.setViewModel(model);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = (RecyclerView) findViewById(R.id.list_project);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mFirebaseAdapter = new FirebaseRecyclerAdapter<ProjectPaint, PaintProjectViewHolder>(ProjectPaint.class, R.layout.item_list_project, PaintProjectViewHolder.class, mDatabaseReference.child(PROJECT_PAINT_CHILD)) {
            @Override
            protected void populateViewHolder(PaintProjectViewHolder viewHolder, ProjectPaint
                    model, int position) {
                Glide.with(MainActivity.this).load(Ultils.arrBackgroundThumb[model.getIndexBackground()])
                        .into(viewHolder.imageView);
                viewHolder.txtAuthor.setText(model.getAuthor());
                viewHolder.txtNameProject.setText(model.getNameProject());
                viewHolder.setOnViewClickListenner(model, getRef(position).getKey(), itemClickListenner);

                Log.d("phantom", "Hello " + model.getIndexBackground() + " Keynode: " + getRef(position).getKey());
            }
        };
        recyclerView.setAdapter(mFirebaseAdapter);
        Log.d("phantom", "OnCreate");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("phantom", "OnResume");
    }


    public void startPaintScreenActivity(ProjectPaint projectPaint, String keyNode) {
        Intent intent = new Intent(MainActivity.this, PaintScreenActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("MODEL", projectPaint);
        bundle.putString("KEY_NODE", keyNode);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            model.signOut();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
