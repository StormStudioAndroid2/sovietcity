package com.sovietcity.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sovietcity.R;

import java.io.Serializable;

//работает с заданиями, доставая их из бд
public class TasksDB implements Serializable {
    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "TaskData";
    // Название столбцов
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TEXT = "Text";
    private static final String COLUMN_YEAR = "Year";
    private static final String COLUMN_REWARD = "Reward";
    private static final String COLUMN_PEOPLE = "People";
    private static final String COLUMN_MAT_NAME1 = "Mat_name1";
    private static final String COLUMN_MAT_NAME2 = "Mat_name2";
    private static final String COLUMN_QUANTITY1 = "Quantity1";
    private static final String COLUMN_QUANTITY2 = "Quantity2";
    private static final String COLUMN_BUILDING = "Building";


    // Номера столбцов
    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_TEXT = 1;
    private static final int NUM_COLUMN_YEAR = 2;
    private static final int NUM_COLUMN_REWARD = 3;
    private static final int NUM_COLUMN_PEOPLE = 4;
    private static final int NUM_COLUMN_BUILDING = 5;
    private static final int NUM_COLUMN_MAT_NAME1 = 6;
    private static final int NUM_COLUMN_QUANTITY1 = 7;
    private static final int NUM_COLUMN_MAT_NAME2 = 8;
    private static final int NUM_COLUMN_QUANTITY2 = 9;

    private SQLiteDatabase sqLiteDatabase;
    private TaskPutter taskPutter;

    public void insert() {
        taskPutter.putValues(sqLiteDatabase, TABLE_NAME);
    }

    public void setSqLiteDatabase(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public TasksDB() {
        this.taskPutter = new TaskPutter();
    }

    public void putTasks(Context context) {
        String[] tasks = context.getResources().getStringArray(R.array.tasks);
        taskPutter.putTask(tasks[0], 1959, 1000, 500);
        this.insert();
        taskPutter.putTask(tasks[1], 1965, 2000, 2000, null, "Еда", 180);
        this.insert();
        taskPutter.putTask(tasks[2], 1969, 5000, "ЖБИ", 40);
        this.insert();
        taskPutter.putTask(tasks[3], 1973, 10000, 10000, null);
        this.insert();
        taskPutter.putTask(tasks[4], 1977, 150000, 0, "Больница", "Еда", 300);
        this.insert();
        taskPutter.putTask(tasks[5], 1981, 20000, "Сталь", 100);
        this.insert();
        taskPutter.putTask(tasks[6], 1985, 5000, 0, "Школа", "Еда", 450);

        this.insert();
    }

    public Task getTask(TaskCreator taskCreator, int year) {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, COLUMN_YEAR + " = ?", new String[]{String.valueOf(year)}, null, null, null);
        cursor.moveToFirst();
        String text = cursor.getString(NUM_COLUMN_TEXT);
        int reward = cursor.getInt(NUM_COLUMN_REWARD);
        int people = cursor.getInt(NUM_COLUMN_PEOPLE);
        String building = cursor.getString(NUM_COLUMN_BUILDING);
        String material1 = cursor.getString(NUM_COLUMN_MAT_NAME1);
        int quantity1 = cursor.getInt(NUM_COLUMN_QUANTITY1);
        String material2 = cursor.getString(NUM_COLUMN_MAT_NAME2);
        int quantity2 = cursor.getInt(NUM_COLUMN_QUANTITY2);
        cursor.close();

        return taskCreator.createTask(text, year, reward, people, building, material1, quantity1, material2, quantity2);
    }


}
