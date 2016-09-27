package com.phantom.painttogether.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phantom.painttogether.R;
import com.phantom.painttogether.utils.Ultils;

public abstract class ChooseBackgroundAdapter extends RecyclerView.Adapter<ChooseBackgroundAdapter.ChooseBackgroundHolder> {
    Context context;
    int idLayout;
    String[] arrName;
    int[] arrBkg;

    public ChooseBackgroundAdapter(Context context, int idLayout, String[] arrName, int[] arrBkg) {
        this.context = context;
        this.idLayout = idLayout;
        this.arrBkg = arrBkg;
        this.arrName = arrName;
    }

    @Override
    public ChooseBackgroundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(idLayout, null);
        return new ChooseBackgroundHolder(view);
    }

    @Override
    public void onBindViewHolder(ChooseBackgroundHolder holder, int position) {
        populateViewHolder(holder, position, Ultils.arrNameBackground[position], position);
    }

    @Override
    public int getItemCount() {
        return arrBkg.length;
    }

    public abstract void populateViewHolder(ChooseBackgroundHolder holder, int resID, String name, int position);

    public class ChooseBackgroundHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView name;

        public ChooseBackgroundHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_imv_background);
            name = (TextView) itemView.findViewById(R.id.item_tv_name_background);
        }

        public void setOnHolderClickListenner(final int resIndex, final OnHolderClickListenner listenner) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenner.onClick(resIndex);
                }
            });
        }

    }

    public interface OnHolderClickListenner {
        void onClick(int resIndex);
    }
}