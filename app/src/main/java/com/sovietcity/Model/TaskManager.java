package com.sovietcity.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

//менеджер, управляющий заданиями
public class TaskManager implements Serializable {
    private transient TaskBDHelper taskBDHelper;
    private transient SQLiteDatabase sqLiteDatabase;
    private transient TasksDB tasksDB;
    private transient TaskWorker taskWorker;
    private transient TaskGetter taskGetter;
    private transient CashManager cashManager;

    public TaskManager(Context context, int year) {
        this.taskWorker = new TaskWorker();
        this.taskBDHelper = TaskBDHelper.getInstance(context);
        sqLiteDatabase = taskBDHelper.getWritableDatabase();
        tasksDB = new TasksDB();
        tasksDB.setSqLiteDatabase(sqLiteDatabase);
        tasksDB.putTasks(context);
        this.taskGetter = new TaskGetter();
        taskGetter.setNextTaskYear(year);
        if (taskGetter.needGetNewTask(year)) {
            Task task = taskGetter.getNewTask(tasksDB);
            taskWorker.setNowTask(task);
        }
    }
    public TaskManager(Context context) {
        this.taskWorker = new TaskWorker();
        this.taskBDHelper = TaskBDHelper.getInstance(context);
        sqLiteDatabase = taskBDHelper.getWritableDatabase();
        tasksDB = new TasksDB();
        tasksDB.setSqLiteDatabase(sqLiteDatabase);
        tasksDB.putTasks(context);
    }
    public synchronized boolean manage(int year) {
        if (taskGetter.needGetNewTask(year)) {
            Task task = taskGetter.getNewTask(tasksDB);
            if (!taskWorker.isComplete()) {
                return true;
            }
            taskWorker.setNowTask(task);
        }
        return false;
    }

    public TaskListener getTaskListener() {
        return this.taskWorker;

    }

    public void setCashManager(CashManager cashManager) {
        this.cashManager = cashManager;
    }

    public boolean isComplete() {
        return taskWorker.isComplete();
    }

    public Task getNowTask() {
        return taskWorker.getNowTask();
    }

    public void getReward() {
        taskWorker.payForTask(cashManager);

    }

    public TaskGetter getTaskGetter() {
        return taskGetter;
    }

    public TaskWorker getTaskWorker() {
        return taskWorker;
    }

    public void deserialize(TaskGetter taskGetter, TaskWorker taskWorker, Context context, int year) {
        this.taskGetter = taskGetter;
        this.taskBDHelper = TaskBDHelper.getInstance(context);
        sqLiteDatabase = taskBDHelper.getWritableDatabase();
        tasksDB = new TasksDB();
        tasksDB.setSqLiteDatabase(sqLiteDatabase);
        tasksDB.putTasks(context);
        this.taskWorker = taskWorker;
        taskWorker.setNowTask(this.taskGetter.deserialize(tasksDB, year));
    }

}
