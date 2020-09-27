package com.sovietcity.Presenter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.sovietcity.Model.GameMap;
import com.sovietcity.View.SurfaceFragment;

/**
 * Created by Серёга on 15.12.2015.
 */
public class SovietView extends SurfaceView implements SurfaceHolder.Callback {
    private World world;
    private SovietThread sovietThread;
    private Paint paint;
    private float imX;
    private float imY;
    private SurfaceHolder holder;
    private boolean hasSurface;
    private SurfaceFragment surfaceFragment;
    private final GestureDetector gestureDetector;

    public SovietView(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paint = new Paint();
        paint.setTextSize(30f);
        paint.setColor(Color.RED);
        paint.setLinearText(true);
        gestureDetector = new GestureDetector(context, new SurfaceGesture());
        init();
    }

    @Override
    public synchronized boolean onTouchEvent(MotionEvent event) {
        System.out.println(event);
        gestureDetector.onTouchEvent(event);
        return true;
    }

    private boolean processMapScrolling(MotionEvent event) {
        return false;
    }

    public synchronized void setSurfaceFragment(SurfaceFragment surfaceFragment) {
        this.surfaceFragment = surfaceFragment;
    }

    public synchronized SovietThread getSovietThread() {
        return this.sovietThread;
    }

    public synchronized void setWorld(World world) {
        this.world = world;
        this.world.getThreadManager().setSovietThread(this.sovietThread);

    }


    private synchronized void init() {
        this.holder = getHolder();
        this.holder.addCallback(this);
        this.hasSurface = false;
        this.sovietThread = new SovietThread(this);

    }

    @Override
    public synchronized void surfaceCreated(SurfaceHolder holder) {

        this.hasSurface = true;
        if ((sovietThread != null) && (world != null)) {
            this.world.getThreadManager().synchronize();
        } else resume();
    }

    public synchronized void resume() {
        if (sovietThread == null) {
            this.paint = new Paint();
            sovietThread = new SovietThread(this);
            this.world.getThreadManager().setSovietThread(this.sovietThread);
            if (this.hasSurface) {
                if (world != null) {
                    this.world.getThreadManager().synchronize();
                }
            }
        }
    }

    private synchronized SurfaceView getSovietView() {
        return this;
    }

    @Override
    public synchronized void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (sovietThread != null) {

        }
    }

    @Override
    public synchronized void surfaceDestroyed(SurfaceHolder holder) {
        this.hasSurface = false;
        pause();
    }

    public synchronized void pause() {

        world.getThreadManager().stop();

    }


    public class SovietThread extends Thread {
        private boolean done;
        private SovietView view;


        public SovietThread(SovietView view) {
            super();
            done = false;
            this.view = view;
        }

        @Override
        public void run() {
            SurfaceHolder holder1 = holder;
            Canvas c = null;
            update();
            if (world.getThreadManager().sovietThreadIsOnce()) {
                setDone(true);
            }
            while (!done) {
                //обновляет интерфейс, когда необходимо
                synchronized (this) {
                    if (world.isWin()) {
                        surfaceFragment.getMainActivity().winTransaction();
                    }
                    if (world.isLose()) {
                        surfaceFragment.getMainActivity().loseTransaction();
                    }
                    if (world.getEventManager().hasEvent()) {
                        surfaceFragment.getMainActivity().eventFragmentTransaction();
                    }
                    if (world.getDrawerManager().getDrawEvent().isNeedUpdate()) {
                        update();
                    }

                }
                scrolling();


                if (world.getDrawerManager().getDrawEvent().isDrawCell()) {
                    try {
                        c = holder1.lockCanvas();
                        if (c != null) {
                            world.getDrawerManager().drawCell(c, world.getDrawerManager().getDrawEvent().getY(), world.getDrawerManager().getDrawEvent().getX());
                            world.getDrawerManager().synchronizeSurfaceView(c);
                        }
                    } finally {
                        if (c != null) {
                            holder1.unlockCanvasAndPost(c);
                        }
                    }

                }
                try {
                    sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }


        public void setDone(boolean b) {
            this.done = b;
        }


    }

    public void scrolling() {
        Canvas c = null;

        for (int i = 1; i <= 10; i++) {
            try {
                c = holder.lockCanvas();
                if (c != null) {
                    world.getDrawerManager().synchronizeSurfaceView(c);
                }

            } finally {
                if (c != null) {
                    holder.unlockCanvasAndPost(c);
                }
            }
        }
        world.getDrawerManager().deleteEvent();

    }

    public synchronized void update() {
        Canvas c = null;
        try {
            c = holder.lockCanvas();
            if (c != null) {
                world.getDrawerManager().synchronizeSurfaceView(c);
            }
        } finally {
            if (c != null) {
                holder.unlockCanvasAndPost(c);
            }
        }
        world.getDrawerManager().deleteEvent();

    }

    class SurfaceGesture extends GestureDetector.SimpleOnGestureListener {
        private float scrollstartX1, scrollStartY1;

        @Deprecated // механика скролла карты изменена
        @Override
        public synchronized boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            //if (true) return false;
            if (!world.getDrawerManager().getDrawEvent().isScroll()) {
                if (scrollstartX1 != e1.getX() || scrollStartY1 != e1.getY()) {
                    scrollstartX1 = e1.getX();
                    scrollStartY1 = e1.getY();
                    // world.getDrawerManager().changePosition(distanceX, distanceY);
                    System.out.println(distanceX + " " + distanceY);
                    world.getDrawerManager().moveCamera(distanceX, distanceY);
                    world.getDrawerManager().scroll();
                }
            }
            System.out.println("onScrollEvent");
            world.getDrawerManager().moveCamera(distanceX, distanceY);
            Camera camera = world.getDrawerManager().getCamera();
            world.getDrawerManager().getDrawer().translate(camera.getCoordinateX(), camera.getCoordinateY());
            return true;

        }

//        @Override
//        public synchronized void onLongPress(MotionEvent e) {
//            SovietView.this.playSoundEffect(android.view.SoundEffectConstants.CLICK);
//
//            float thisX = e.getX();
//            float thisY = e.getY();
//            int sx = world.getDrawerManager().getX(thisX);
//            int sy = world.getDrawerManager().getY(thisY);
//            if ((GameMap.getGameMapCell(sy, sx).getFunctionTranslator() != null) && (!GameMap.getGameMapCell(sy, sx).isFocused())) {
//                GameMap.getGameMapCell(sy, sx).getFunctionTranslator().translateFunctionComponent().about(surfaceFragment.getMainActivity(), sy, sx);
//            }
//        }

        @Override
        public synchronized boolean onSingleTapConfirmed(MotionEvent e) {
            SovietView.this.playSoundEffect(android.view.SoundEffectConstants.CLICK);
            float thisX = e.getX();
            float thisY = e.getY();
            int sx = world.getDrawerManager().getX(thisX);
            int sy = world.getDrawerManager().getY(thisY);
            boolean b = false;
            if (world.getDrawerManager().isPhysicMap()) {
                b = world.getBuildManager().buildCell(world, sy, sx, getContext());
                if (b) {
                    world.getDrawerManager().needUpdate();
                } else {
                    if ((GameMap.getGameMapCell(sy, sx).getFunctionTranslator() != null) && (!GameMap.getGameMapCell(sy, sx).isFocused())) {
                        GameMap.getGameMapCell(sy, sx).getFunctionTranslator().translateFunctionComponent().about(surfaceFragment.getMainActivity(), sy, sx);
                    }
                }
            } else {

            }
            Log.i("flag", Boolean.toString(b));
            if (b) world.getDrawerManager().needUpdate();

            return true;
        }

        @Override
        public synchronized boolean onDoubleTap(MotionEvent e) {
            float thisX = e.getX();
            float thisY = e.getY();
            int sx = world.getDrawerManager().getX(thisX);
            int sy = world.getDrawerManager().getY(thisY);
            GameMap.getGameMapCell(sy,sx).deleteRoad(world.getMapManager().getTerrainModelFabric());

            if ((GameMap.getGameMapCell(sy, sx).hasiRoadFunction())) {
                GameMap.getGameMapCell(sy,sx).deleteRoad(world.getMapManager().getTerrainModelFabric());
            }
            return true;
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            return super.onContextClick(e);
        }


    }

}