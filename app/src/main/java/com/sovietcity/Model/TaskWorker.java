package com.sovietcity.Model;

import java.io.Serializable;

//реализует логику заданий
public class TaskWorker implements TaskListener,Serializable {
    private int nowQuantity1;
    private int nowQuantity2;
    private int nowPeople;
    private boolean isBuildingComplete;
    private boolean isFirstMaterialComplete;
    private boolean isSecondMaterialComplete;
    private boolean isPeopleComplete;
    private transient Task nowTask;

    public boolean isComplete() {
        boolean b = ((isBuildingComplete) && (isFirstMaterialComplete) && (isSecondMaterialComplete) && (isPeopleComplete)||(nowTask==null));
        return b;
    }

    private void setDefaultValues() {
        this.nowQuantity1 = 0;
        this.nowQuantity2 = 0;
        this.nowPeople = 0;
        this.nowTask=null;
        isFirstMaterialComplete = false;
        isPeopleComplete = false;
        isSecondMaterialComplete = false;
        isBuildingComplete = false;
    }

    public void setNowTask(Task nowTask) {
        this.nowTask = nowTask;
        if (nowTask.getMaterial1() == null) {
            isFirstMaterialComplete = true;
        }
        if (nowTask.getMaterial2() == null) {
            isSecondMaterialComplete = true;
        }
        if (nowTask.getNeedPeople() == 0) {
            this.isPeopleComplete = true;
        }
        if (nowTask.getNeedBuilding() == null) {
            isBuildingComplete = true;
        }
    }

    @Override
    public void observeTask(TaskEvent taskEvent) {
        if (nowTask!=null) {
            if (!this.isPeopleComplete) {
                this.nowPeople = taskEvent.getPeople();
                if (nowPeople >= nowTask.getNeedPeople()) {
                    isPeopleComplete = true;
                }
            }
            if ((!this.isFirstMaterialComplete) && (taskEvent.getResource() != null) && (nowTask.getMaterial1().equals(taskEvent.getResource()))) {
                this.nowQuantity1 += taskEvent.getQuantity();
                if (this.nowQuantity1 == nowTask.getQuantity1()) {
                    isFirstMaterialComplete = true;
                }
            }
            if ((!this.isSecondMaterialComplete) && (taskEvent.getResource() != null) && (nowTask.getMaterial2().equals(taskEvent.getResource()))) {
                this.nowQuantity2 += taskEvent.getQuantity();
                if (this.nowQuantity2 == nowTask.getQuantity2()) {
                    isSecondMaterialComplete = true;
                }
            }
            if ((!this.isBuildingComplete) && (taskEvent.getBuilding() != null) && (nowTask.getNeedBuilding().equals(taskEvent.getBuilding()))) {
                this.isBuildingComplete = true;
            }
        }
    }

    public void payForTask(CashManager cashManager) {
        if (isComplete()) {
            cashManager.addCash(nowTask.getReward());
        }
        setDefaultValues();
    }

    public Task getNowTask() {
        return nowTask;
    }
}
