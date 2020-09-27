package com.sovietcity.Model;

import com.sovietcity.Presenter.SovietView;
import com.sovietcity.Presenter.World;
import com.sovietcity.View.MainActivity;

import java.io.Serializable;

//менеджер управления потоками
public class ThreadManager implements Serializable {
    private SovietView.SovietThread sovietThread;
    private TimeThread timeThread;
    private World world;
    private boolean flag;
    private State state;
    private MainActivity mainActivity;



    public void setTimeThread(TimeThread timeThread) {
        this.timeThread = timeThread;
    }

    public synchronized boolean synchronize() {


        if ((this.sovietThread!=null)&&(sovietThread.isAlive())&&(!state.runSurfaceView())) {
            sovietThread.setDone(true);
            sovietThread=null;
        }
        if ((this.timeThread!=null)&&(timeThread.isAlive())&&(!state.runTimeThread())) {
            timeThread.setDone();
            timeThread = null;
        }
        if (state.runTimeThread() && (timeNotStarted())) {
            this.timeThread = new TimeThread(world);
            timeThread.setMainActivity(mainActivity);
            timeThread.setTime(1000);
            timeThread.setDaemon(true);
            timeThread.start();
        }
        if ((this.sovietThread != null) && (!this.sovietThread.isAlive()) && (state.runSurfaceView())&&(!sovietThread.isAlive())) {
            this.sovietThread.start();
        }
        return true;
    }

    public boolean timeNotStarted() {
        if ((timeThread == null) || (!timeThread.isAlive())) {
            return true;
        }
        return false;
    }

    public void setSovietThread(SovietView.SovietThread sovietThread) {
        this.sovietThread = sovietThread;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public boolean isWorking() {
        if (sovietThread != null) {
            if (sovietThread.isAlive()) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void setFaster() {
        this.state = state.play();
        this.synchronize();
        if (state.runTimeThread()) {
            this.timeThread.setTime(100);
        }
    }

    public void setSlow() {
        this.state = state.play();
        this.synchronize();
        if (state.runTimeThread()) {
            this.timeThread.setTime(1000);
        }
    }


    public ThreadManager(World world, boolean flag) {
        this.world = world;
        this.flag = flag;
    }


    public void setState(State state) {
        this.state = state;
    }

    public void pause() {
        this.state = state.pause();
        synchronize();
    }

    public void openMenu() {
        this.state = state.openMenu();
        synchronize();

    }
    public void stop() {
        if ((this.sovietThread!=null)&&(sovietThread.isAlive())) {
            sovietThread.setDone(true);
            sovietThread=null;
        }
        if ((this.timeThread!=null)&&(timeThread.isAlive())) {
            timeThread.setDone();
            timeThread = null;
        }
    }
    public void closeMenu() {
        this.state = state.closeMenu();

    }
    public boolean sovietThreadIsOnce() {
        if (this.state==State.RUN_SURFACE_ONCE) {
            return true;
        }
        return false;
    }
}
