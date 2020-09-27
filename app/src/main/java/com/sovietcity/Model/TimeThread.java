package com.sovietcity.Model;

import com.sovietcity.Presenter.World;
import com.sovietcity.View.MainActivity;

import java.util.Calendar;

//поток, управляющий временем
public class TimeThread extends Thread {
    private World world;
    private boolean flag;
    private MainActivity mainActivity;
    private int time;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public synchronized void run() {
        while (flag) {
            mainActivity.updateUI();

            if (world.getTimeManager().getDate().get(Calendar.YEAR) == 1987) {
                world.getAuthorisationWorker().addRecord(world.getCashManager().getCash(), world.getPopulationManager().getPopulation().getAdultGroup().getWealth() + world.getPopulationManager().getPopulation().getPensionerGroup().getWealth() + world.getPopulationManager().getPopulation().getChildrenGroup().getWealth());
                world.setWin(true);
            }
            world.setLose(world.getTaskManager().manage(world.getTimeManager().getDate().get(Calendar.YEAR)));
            if (world.isLose()) {
                world.getAuthorisationWorker().addRecord(world.getCashManager().getCash(), world.getPopulationManager().getPopulation().getAdultGroup().getWealth() + world.getPopulationManager().getPopulation().getPensionerGroup().getWealth() + world.getPopulationManager().getPopulation().getChildrenGroup().getWealth());
            }
            if ((!world.isLose()) && (!world.isWin()) && (!world.getEventManager().hasEvent())) {
                world.getTimeManager().update();
                world.getEventManager().getEvent(world, world.getTimeManager().getDate());

                if (world.getTimeManager().isFirstMonthDay()) {
                    world.getCashManager().gainFee(world.getPopulationManager());
                    world.getResourceManager().manage();
                    world.getPopulationManager().manage();
                    world.getBuildManager().manage();
                    world.getDeliveryManager().payForDeliveries(world.getCashManager());
                }
                world.getDeliveryManager().updateDeliveries(this.world, world.getTimeManager().getDate());
            }
            try {
                sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public TimeThread(World world) {
        this.flag = true;
        this.world = world;
        this.time = 1000;

    }

    public void setDone() {
        this.flag = false;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
