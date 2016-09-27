package com.phantom.painttogether.utils;

import android.databinding.BindingAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.phantom.painttogether.R;
import com.phantom.painttogether.view.PaintView;

/**
 * Created by sev_user on 9/16/2016.
 */

public class Ultils {
    public static int arrBackground[] = {R.drawable.bg_rainbow, R.drawable.bg_doremon, R.drawable.bg_plance, R.drawable.bg_bus, R.drawable.bg_dog, R.drawable.bg_giraffe};
    public static int arrBackgroundThumb[] = {R.drawable.bg_rainbow_thumb, R.drawable.bg_doremon_thumb, R.drawable.bg_plance_thumb, R.drawable.bg_bus_thumb, R.drawable.bg_dog_thumb, R.drawable.bg_giraffe_thumb};
    public static String arrNameBackground[] = {"Rainbow", "Doremon", "PLane", "Bus", "Dog", "Giraffe"};

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter({"android:background"})
    public static void setBackgroundResource(ImageView imageView, int resource) {
        imageView.setBackgroundResource(resource);
    }

    @BindingAdapter({"android:background"})
    public static void setBackgroundResource(PaintView view, int resource) {
        view.setBackgroundResource(resource);
    }
}
