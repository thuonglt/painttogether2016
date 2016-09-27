package com.phantom.painttogether.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.phantom.painttogether.R;
import com.phantom.painttogether.data.ProjectPaint;

/**
 * Created by sev_user on 9/21/2016.
 */

public class PaintProjectViewHolder extends RecyclerView.ViewHolder {
    public TextView txtNameProject, txtAuthor;
    public ImageView imageView;
    public View itemView;


    public PaintProjectViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        txtNameProject = (TextView) itemView.findViewById(R.id.item_name_project);
        txtAuthor = (TextView) itemView.findViewById(R.id.item_author);
        imageView = (ImageView) itemView.findViewById(R.id.item_image_bg);
    }

    public void setOnViewClickListenner(final ProjectPaint projectPaint, final String keyNode, final ViewClickListenner listenner) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenner.onClick(projectPaint, keyNode);
            }
        });
    }

    public interface ViewClickListenner {
        public void onClick(ProjectPaint projectPaint, String keyNode);
    }
}
