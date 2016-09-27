package com.phantom.painttogether.data;

import java.io.Serializable;

/**
 * Created by sev_user on 9/19/2016.
 */

public class ProjectPaint implements Serializable {
    private String author;
    private int indexBackground;
    private String nameProject;


    public ProjectPaint() {
    }

    public ProjectPaint(String author, int indexBackground, String nameProject) {
        this.author = author;
        this.indexBackground = indexBackground;
        this.nameProject = nameProject;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIndexBackground() {
        return indexBackground;
    }

    public void setIndexBackground(int indexBackground) {
        this.indexBackground = indexBackground;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }
}
