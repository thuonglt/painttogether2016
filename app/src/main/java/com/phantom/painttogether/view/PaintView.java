package com.phantom.painttogether.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phantom.painttogether.data.Point;
import com.phantom.painttogether.viewmodel.PaintScreenViewModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sev_user on 9/16/2016.
 */

public class PaintView extends View {
    // setup initial color
    public static int[] arrayColor = {0xffff0000, 0xff838b8b, 0xff4876ff, 0xff94ffd1, 0xffff0000, 0xffffff40};
    private final int paintColor = arrayColor[1];
    // defines paint and canvas
    private Paint drawMyPaint, drawOtherPaint;
    private Bitmap cacheMyBitmap, cacheOtherBitmap;
    // stores next circle
    private int typePen = 0;
    private int indexColor = 0;
    private Path path = new Path();
    private Path pathOtherUser = new Path();
    private Bitmap bitmap;
    private Canvas canvasOfBitmap, cacheMyCanvas, cacheOtherCanvas;
    private ArrayList<Path> graphcis = new ArrayList<>();
    private int viewWidth, viewHeight;
    private DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
    Point point = new Point();
    private static String USER_ID;
    private HashMap<String, Path> mapsPath = new HashMap<>();
    public String CHILD_NODE;

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        setFocusable(true);
        setFocusableInTouchMode(true);

        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    viewWidth = getWidth();
                    viewHeight = getHeight();
                    init();
                }
            });
        }
        USER_ID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        CHILD_NODE = "paints/" + PaintScreenActivity.KEY_NODE;
        Log.d("phantom", CHILD_NODE);
    }


    private void init() {
        bitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
        cacheMyBitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
        cacheOtherBitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);

        cacheOtherCanvas = new Canvas(cacheOtherBitmap);
        cacheMyCanvas = new Canvas(cacheMyBitmap);
        canvasOfBitmap = new Canvas(bitmap);
        // Setup paint with color and stroke styles

        drawMyPaint = new Paint();
        drawMyPaint.setColor(paintColor);
        drawMyPaint.setAntiAlias(true);
        drawMyPaint.setStrokeWidth(20);
        drawMyPaint.setStyle(Paint.Style.STROKE);
        drawMyPaint.setStrokeJoin(Paint.Join.ROUND);
        drawMyPaint.setStrokeCap(Paint.Cap.ROUND);
        drawOtherPaint = new Paint(drawMyPaint);

        mFirebaseDatabaseReference.child(CHILD_NODE).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("phantom", "key child: " + dataSnapshot.getKey());
                if (mapsPath.containsKey(USER_ID) == false) {
                    Log.d("phantom", "content of maps is null! ");
                    Path path = new Path();
                    mapsPath.put(USER_ID, path);
                }
                pathOtherUser = mapsPath.get(USER_ID);
                Point newPoint = dataSnapshot.getValue(Point.class);
                if (newPoint.getUserID().equalsIgnoreCase(USER_ID)) {
                    Log.d("phantom", "this data belong this user :" + newPoint.getUserID());
                    return;
                }
                switch (newPoint.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        pathOtherUser.reset();
                        pathOtherUser.moveTo(newPoint.getX(), newPoint.getY());
                        pathOtherUser.lineTo(newPoint.getX(), newPoint.getY());

                        drawOtherPaint.setColor(arrayColor[newPoint.getColor()]);
                        drawOtherPaint.setStrokeWidth(newPoint.getSize());
                        if (newPoint.getType() == 1) {
                            drawOtherPaint.setXfermode(new PorterDuffXfermode(
                                    PorterDuff.Mode.CLEAR));
                        } else {
                            drawOtherPaint.setXfermode(null);
                        }
                        //setup paint

                        Log.d("phantom", "ACTION_DOWN ");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        pathOtherUser.lineTo(newPoint.getX(), newPoint.getY());
                        Log.d("phantom", "ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        pathOtherUser.lineTo(newPoint.getX(), newPoint.getY());
                        canvasOfBitmap.drawPath(pathOtherUser, drawOtherPaint);
                        Log.d("phantom", "ACTION_UP");
                        break;
                }
                mapsPath.put(USER_ID, pathOtherUser);
                postInvalidate();
                Log.d("phantom", "PostInvalidate!");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawPreviousBitmap(canvas);
        drawCurrentPath();
        canvas.drawBitmap(cacheMyBitmap, 0, 0, null);
        canvas.drawBitmap(cacheOtherBitmap, 0, 0, null);
    }

    private void drawCurrentPath() {
        cacheMyCanvas.drawPath(path, drawMyPaint);
        cacheOtherCanvas.drawPath(pathOtherUser, drawOtherPaint);
    }

    private void drawPreviousBitmap(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();
        // Checks for the event that occurs
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.reset();
                path.moveTo(pointX, pointY);
                path.lineTo(pointX, pointY);
                mFirebaseDatabaseReference.child(CHILD_NODE).push().setValue(makePoint(pointX, pointY, typePen, USER_ID, MotionEvent.ACTION_DOWN, drawMyPaint.getStrokeWidth()));
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                mFirebaseDatabaseReference.child(CHILD_NODE).push().setValue(makePoint(pointX, pointY, typePen, USER_ID, MotionEvent.ACTION_MOVE, drawMyPaint.getStrokeWidth()));
                break;
            case MotionEvent.ACTION_UP:
                canvasOfBitmap.drawPath(path, drawMyPaint);
                mFirebaseDatabaseReference.child(CHILD_NODE).push().setValue(makePoint(pointX, pointY, typePen, USER_ID, MotionEvent.ACTION_UP, drawMyPaint.getStrokeWidth()));
                break;
            default:
                return false;
        }
        // Force a view to draw again

        postInvalidate();
        return true;
    }

    private Point makePoint(float x, float y, int type, String userID, int action, float sizePen) {
        point.setAction(action);
        point.setX(x);
        point.setY(y);
        point.setUserID(userID);
        point.setWidthView(viewWidth);
        point.setHeightView(viewHeight);
        point.setType(type);
        point.setSize(sizePen);
        point.setColor(indexColor);
        return point;
    }

    public void setPenColor(int indexColor) {
        drawMyPaint.setColor(arrayColor[indexColor]);
        this.indexColor = indexColor;
    }

    public void setPenType(int penType) {
        if (penType == 1) {
            drawMyPaint.setXfermode(new PorterDuffXfermode(
                    PorterDuff.Mode.CLEAR));
        } else {
            drawMyPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            drawMyPaint.setColor(arrayColor[indexColor]);
        }
        typePen = penType;
    }

    public void setPenSize(int penSize) {
        drawMyPaint.setStrokeWidth(penSize);
    }

    public Paint getDrawMyPaint() {
        return drawMyPaint;
    }

    public void setDrawMyPaint(Paint drawMyPaint) {
        this.drawMyPaint = drawMyPaint;
    }
}
