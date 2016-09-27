package com.phantom.painttogether.data;

/**
 * Created by sev_user on 9/16/2016.
 */

public class Point {
    /*coordinate info*/
    float x;
    float y;
    /* size of view */
    int widthView;
    int heightView;

    int type;// 1 eraser or 0 pen
    int action;// down,up,move
    int color;
    float size;

    String userID;

    public Point() {
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidthView() {
        return widthView;
    }

    public void setWidthView(int widthView) {
        this.widthView = widthView;
    }

    public int getHeightView() {
        return heightView;
    }

    public void setHeightView(int heightView) {
        this.heightView = heightView;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
