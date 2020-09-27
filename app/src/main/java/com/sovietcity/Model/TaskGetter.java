package com.sovietcity.Model;

import java.io.Serializable;

//получает задание и увеличивает время
public class TaskGetter implements Serializable {
    private int nextTaskYear;
    private TaskCreator taskCreator;

    public TaskGetter() {
        this.nextTaskYear = 1959;
        this.taskCreator = new TaskCreator();
    }
    public boolean needGetNewTask(int year) {
        if (year==nextTaskYear) {
            return true;
        }
        return false;
    }
    public Task getNewTask(TasksDB tasksDB) {
        Task task = tasksDB.getTask(taskCreator, nextTaskYear);
        if (nextTaskYear<1965) {
            nextTaskYear=1965;
        } else {
            nextTaskYear += 4;
        }
        return task;
    }
    public Task deserialize(TasksDB tasksDB,int year) {
        Task task = null;
        if (year<1965) {
            task = tasksDB.getTask(taskCreator, 1959);
        } else {
            task = tasksDB.getTask(taskCreator, nextTaskYear-4);

        }
        return task;
    }
    public void setNextTaskYear(int nextTaskYear) {
        this.nextTaskYear = nextTaskYear;
    }

    public int getNextTaskYear() {
        return nextTaskYear;
    }
    public Task getNowTask(TasksDB tasksDB) {
        Task task = null;
        if (nextTaskYear<=1965) {
             task = tasksDB.getTask(taskCreator,1959);

        } else {
             task = tasksDB.getTask(taskCreator, nextTaskYear - 4);
        }
        return task;
    }
}
