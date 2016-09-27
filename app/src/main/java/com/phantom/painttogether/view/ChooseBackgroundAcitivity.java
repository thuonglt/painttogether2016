package com.phantom.painttogether.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.phantom.painttogether.R;
import com.phantom.painttogether.utils.Ultils;
import com.phantom.painttogether.view.adapter.ChooseBackgroundAdapter;

public class ChooseBackgroundAcitivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ChooseBackgroundAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_background_acitivity);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_choose_bg);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        final ChooseBackgroundAdapter.OnHolderClickListenner clickHolderListenner = new ChooseBackgroundAdapter.OnHolderClickListenner() {
            @Override
            public void onClick(int resIndex) {
                sentResultToParrent(resIndex);
            }
        };
        adapter = new ChooseBackgroundAdapter(this, R.layout.item_list_choose_bg, Ultils.arrNameBackground, Ultils.arrBackground) {

            @Override
            public void populateViewHolder(ChooseBackgroundHolder holder, int resIndex, String name, int position) {
                holder.imageView.setImageResource(Ultils.arrBackgroundThumb[resIndex]);
                holder.name.setText(name);
                holder.setOnHolderClickListenner(position, clickHolderListenner);
            }
        };
        recyclerView.setAdapter(adapter);
    }


    void sentResultToParrent(int resIndex) {
        Intent intent = getIntent();
        intent.putExtra("data", resIndex);
        setResult(CreateProjectPaintActivity.RESULT_CODE, intent);
        finish();
    }
}

