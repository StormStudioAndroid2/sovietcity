package com.sovietcity.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Display;
import android.view.WindowManager;

import com.sovietcity.Model.Observer;

//класс, который управляет перерисовкой поля
public class DrawerManager implements Observer {
    private Drawer drawer;
    private int width;
    private int height;
    private Camera camera;
    private DrawEvent drawEvent;
    private float dispWidth;
    private float dispHeight;
    private boolean isPhysicMap;
    public float getDispWidth() {
        return dispWidth;
    }

    public float getDispHeight() {
        return dispHeight;
    }

    public DrawerManager(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        this.isPhysicMap = true;
        this.width = display.getWidth() / 8;
        this.dispHeight = display.getHeight();
        this.dispWidth = display.getWidth();
        this.height = display.getHeight() / 5;
        this.drawer = new Drawer(context, width, height);

        this.drawEvent = new DrawEvent();
        this.drawEvent.setNeedUpdate(true);
    }

    public DrawerManager(Context context, Drawer drawer) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        this.isPhysicMap = true;
        Paint paint = new Paint();
        this.camera = new Camera();
        this.width = display.getWidth() / 8;
        this.dispHeight = display.getHeight();
        this.dispWidth = display.getWidth();
        this.height = display.getHeight() / 5;
        this.drawer = new Drawer(context, width, height, drawer);
        this.drawEvent = new DrawEvent();
        this.drawEvent.setNeedUpdate(true);
    }

    public synchronized void drawMap(Canvas canvas) {
        if (isPhysicMap) {
            drawer.drawPhysicMap(canvas, width, height);

        } else {
            drawer.drawPowerMap(canvas, width, height);

        }
    }

    public synchronized void drawCell(Canvas canvas, int y, int x) {
        if (isPhysicMap) {
            drawer.drawPhysicCell(canvas, y, x, width, height);
        } else {
            drawer.drawPowerCell(canvas, y, x, width, height);
        }
    }

    public void synchronizeSurfaceView(Canvas canvas) {
        if (camera == null) {
            initializeCamera();
        }
        camera.synchronizeCanvas(this, this.drawer, canvas);

    }

    @Deprecated
    public synchronized void changePosition(float scrollX, float scrollY) {
        if (camera == null) {
            initializeCamera();
        }
        if (scrollX != this.width / 2) {
            scrollX = scrollX % (width / 2);
        }
        if (scrollY != this.height / 2) {
            scrollY = scrollY % (height / 2);
        }
        if (!camera.isScrolling()) {
            camera.changePosition(scrollX, scrollY);
        }
    }

    public synchronized void moveCamera(float dx, float dy) {
        if (camera == null) {
            initializeCamera();
        }
        camera.move(dx, dy);
    }

    public boolean isPhysicMap() {
        return isPhysicMap;
    }

    public void setPhysicMap(boolean physicMap) {
        isPhysicMap = physicMap;
    }


    public void scroll() {
        this.drawEvent = new DrawEvent();
        this.drawEvent.setScroll(true);
    }

    public void setCell(int y, int x) {
        this.drawEvent = new DrawEvent();
        this.drawEvent.setDrawCell(true);
        this.drawEvent.setCoordinate(y, x);
    }

    @Override
    public void needUpdate() {
        this.drawEvent = new DrawEvent();
        this.drawEvent.setNeedUpdate(true);
    }

    public void deleteEvent() {
        this.drawEvent = new DrawEvent();
    }

    public DrawEvent getDrawEvent() {
        return drawEvent;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Bitmap setSize(Bitmap b) {
        return b.createScaledBitmap(b, this.width, this.height, false);
    }

    public int getX(float coordinateX) {
        return camera.getX(coordinateX, width);
    }

    public int getY(float coordinateY) {
        return camera.getY(coordinateY, height);
    }

    private void initializeCamera() {
        camera = new Camera();
        camera.setCoordinates(-width * 50, -height * 50);
    }

    public synchronized void startSynchronizeCamera() {
        if (this.camera == null) {
            initializeCamera();
        }
        camera.startDrawCanvas(this, drawer);
    }

    public Drawer getDrawer() {
        return drawer;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }


}