package com.sovietcity.Presenter;

import android.graphics.Canvas;
import android.graphics.Color;

import java.io.Serializable;

/**
 * Created by Серёга on 09.04.2016.
 */
public class Camera implements Serializable {
    private int countScrolling;
    private boolean isScrolling;
    private float scrollX;
    private float scrollY;
    private float coordinateX;
    private float coordinateY;

    public Camera() {
        this.countScrolling = 1;
    }

    @Deprecated
    public boolean changePosition(float scrollX, float scrollY) {
        this.scrollX = scrollX;
        this.scrollY = scrollY;
        isScrolling = true;
        return true;
    }

    public void move(float dx, float dy) {
        coordinateX -= dx;
        coordinateY -= dy;
        isScrolling = true;
    }

    public boolean setCoordinates(float x, float y) {
        this.coordinateY = y;
        this.coordinateX = x;
        return true;
    }

    public  void synchronizeCanvas(DrawerManager drawerManager, Drawer drawer, Canvas canvas) {
        if (isScrolling) {
            if (countScrolling == 10) {
                countScrolling = 0;
                isScrolling = false;
                scrollY = 0;
                scrollX = 0;
            }
            this.coordinateY = coordinateY - scrollY;
            this.coordinateX = coordinateX - scrollX;
            drawer.translate(coordinateX, coordinateY);
            canvas.drawColor(Color.BLACK);
            drawerManager.drawMap(canvas);
            countScrolling++;
        } else {
            canvas.drawColor(Color.BLACK);
            drawerManager.drawMap(canvas);

        }
    }
    public synchronized void startDrawCanvas(DrawerManager drawerManager, Drawer drawer) {
        drawer.translate(coordinateX, coordinateY);
    }
    public int getX(float coordinateX, int width) {
        float x = Math.abs(this.coordinateX) + coordinateX;
        return (int) (x / width);
    }

    public int getY(float coordinateY, int height) {
        float y = Math.abs(this.coordinateY) + coordinateY;
        return (int) (y / height);
    }

    public float getCoordinateX() {
        return coordinateX;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public boolean isScrolling() {
        return isScrolling;
    }
}
