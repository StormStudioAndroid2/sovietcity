package com.sovietcity.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

//помощник, открывающий бд с заданиями
public class TaskBDHelper extends SQLiteOpenHelper implements Serializable {
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


    private static TaskBDHelper sqlHelper = null;

    private TaskBDHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        context.deleteDatabase(DATABASE_NAME);
    }

    public static TaskBDHelper getInstance(Context context) {
        if (sqlHelper == null) {
            sqlHelper = new TaskBDHelper(context);
            return sqlHelper;
        }
        return sqlHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TEXT + " TEXT NOT NULL, " +
                COLUMN_YEAR + " INTEGER NOT NULL, " +
                COLUMN_REWARD + " INTEGER NOT NULL, " +
                COLUMN_PEOPLE + " INTEGER," +
                COLUMN_BUILDING + " TEXT," +
                COLUMN_MAT_NAME1 + " TEXT," +
                COLUMN_QUANTITY1 + " INTEGER," +
                COLUMN_MAT_NAME2 + " TEXT," +
                COLUMN_QUANTITY2 + " INTEGER); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
