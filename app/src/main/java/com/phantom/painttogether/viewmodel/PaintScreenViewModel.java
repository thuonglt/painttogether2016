package com.phantom.painttogether.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.phantom.painttogether.R;
import com.phantom.painttogether.data.ProjectPaint;
import com.phantom.painttogether.utils.Ultils;
import com.phantom.painttogether.view.PaintView;

/**
 * Created by sev_user on 9/19/2016.
 */

public class PaintScreenViewModel {
    private Context context;
    public ObservableField<String> seekBarValue = new ObservableField<>("Size: 20");
    public ObservableBoolean color1Clickable, color2Clickable, color3Clickable, color4Clickable, color5Clickable, penClickable, eraserClickable, seekbarClickable;
    public ObservableInt drawableColor1;
    public ObservableInt resIDColor1, resIDColor2, resIDColor3, resIDColor4, resIDColor5, resIDBackground;
    private PaintView paintView;
    private Activity activity;
    private ProjectPaint model;

    public PaintScreenViewModel() {

    }

    public PaintScreenViewModel(Context context, ProjectPaint model) {
        this.context = context;
        color1Clickable = new ObservableBoolean(true);
        color2Clickable = new ObservableBoolean(true);
        color3Clickable = new ObservableBoolean(true);
        color4Clickable = new ObservableBoolean(true);
        color5Clickable = new ObservableBoolean(true);
        penClickable = new ObservableBoolean(true);
        eraserClickable = new ObservableBoolean(true);
        seekbarClickable = new ObservableBoolean(true);
        drawableColor1 = new ObservableInt(R.drawable.circle_color1);

        resIDColor1 = new ObservableInt(R.drawable.ok_circle_color1);
        resIDColor2 = new ObservableInt(R.drawable.circle_color2);
        resIDColor3 = new ObservableInt(R.drawable.circle_color3);
        resIDColor4 = new ObservableInt(R.drawable.circle_color4);
        resIDColor5 = new ObservableInt(R.drawable.circle_color5);
        resIDBackground = new ObservableInt(R.drawable.bg_rainbow);

        activity = (Activity) context;
        this.model = model;
        paintView = (PaintView) activity.findViewById(R.id.main_paint_view);
        if (model != null) {
            resIDBackground.set(Ultils.arrBackground[model.getIndexBackground()]);
//            paintView.setBackgroundResource(Ultils.arrBackground[model.getIndexBackground()]);
        }
    }

    public void onValueChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
        seekBarValue.set("Size: " + progresValue);
        paintView.setPenSize(progresValue);
    }

    public void onClickColor1(View view) {
        resetAllColorButton();
        paintView.setPenColor(1);
        resIDColor1.set(R.drawable.ok_circle_color1);
        Log.d("phantom", "Color1 has clicked!");
    }

    public void onClickColor2(View view) {
        resetAllColorButton();
        paintView.setPenColor(2);
        resIDColor2.set(R.drawable.ok_circle_color2);
        Log.d("phantom", "Color2 has clicked!");
    }

    public void onClickColor3(View view) {
        resetAllColorButton();
        paintView.setPenColor(3);
        resIDColor3.set(R.drawable.ok_circle_color3);
        Log.d("phantom", "Color3 has clicked!");
    }

    public void onClickColor4(View view) {
        resetAllColorButton();
        paintView.setPenColor(4);
        resIDColor4.set(R.drawable.ok_circle_color4);
        Log.d("phantom", "Color4 has clicked!");
    }

    public void onClickColor5(View view) {
        resetAllColorButton();
        paintView.setPenColor(5);
        resIDColor5.set(R.drawable.ok_circle_color5);
        Log.d("phantom", "Color5 has clicked!");
    }

    public void onClickEraser(View view) {
        paintView.setPenType(1);
        setStatusAllButton(false);
        penClickable.set(true);
        Log.d("phantom", "Eraser has clicked!");
    }

    public void onClickPen(View view) {
        Log.d("phantom", "Pen has clicked!");
        paintView.setPenType(0);
        setStatusAllButton(true);
    }

    public void setStatusAllButton(boolean status) {
        color1Clickable.set(status);
        color2Clickable.set(status);
        color3Clickable.set(status);
        color4Clickable.set(status);
        color5Clickable.set(status);
        penClickable.set(status);
        eraserClickable.set(status);
    }

    public void resetAllColorButton() {
        resIDColor1.set(R.drawable.circle_color1);
        resIDColor2.set(R.drawable.circle_color2);
        resIDColor3.set(R.drawable.circle_color3);
        resIDColor4.set(R.drawable.circle_color4);
        resIDColor5.set(R.drawable.circle_color5);
    }

}

