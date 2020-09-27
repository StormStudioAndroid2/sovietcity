package com.sovietcity.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Серёга on 11.06.2016.
 */
public class EventDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "events.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "EventData";
    // Название столбцов
    private static final String EVENT_COLUMN_ID = "_id";
    private static final String EVENT_COLUMN_TEXT = "Text";
    private static final String EVENT_COLUMN_DATE = "Date";
    private static final String EVENT_COLUMN_IS_EVERY_YEAR = "EveryYear";

    // Номера столбцов
    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_TEXT = 1;
    private static final int NUM_COLUMN_YEAR = 2;
    private static final int NUM_COLUMN_REWARD = 3;
    private static final int NUM_COLUMN_IS_EVERY_YEAR = 4;

    private static final String ANSWER_TABLE_NAME = "AnswerData";
    // Название столбцов
    private static final String ANSWER_COLUMN_ID = "_id";
    private static final String ANSWER_COLUMN_TEXT = "Text";
    private static final String ANSWER_COLUMN_MONEY = "Money";
    private static final String ANSWER_COLUMN_PEOPLE = "People";
    private static final String ANSWER_COLUMN_AUTHORITY = "Authority";


    // Номера столбцов
    private static final int ANSWER_NUM_COLUMN_ID = 0;
    private static final int ANSWER_NUM_COLUMN_TEXT = 1;
    private static final int ANSWER_NUM_COLUMN_MONEY = 2;
    private static final int ANSWER_NUM_COLUMN_PEOPLE = 3;
    private static final int ANSWER_NUM_COLUMN_AUTHORITY = 4;

    private static  EventDBHelper sqlHelper = null;
    private boolean needPut;
    public EventDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    private EventDBHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    public static EventDBHelper getInstance(Context context) {
        if (sqlHelper == null) {

            sqlHelper = new EventDBHelper(context);
            return sqlHelper;
        }
        return sqlHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        needPut = true;
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                EVENT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EVENT_COLUMN_TEXT + " TEXT NOT NULL, " +
                EVENT_COLUMN_DATE + " TEXT, " +
                EVENT_COLUMN_IS_EVERY_YEAR + " INTEGER NOT NULL); ";

        db.execSQL(query);
        query = "CREATE TABLE " + ANSWER_TABLE_NAME + " (" +
                ANSWER_COLUMN_ID + " INTEGER NOT NULL, " +
                ANSWER_COLUMN_TEXT + " TEXT NOT NULL, " +
                ANSWER_COLUMN_MONEY + " INTEGER NOT NULL, " +
                ANSWER_COLUMN_PEOPLE + " INTEGER," +
                ANSWER_COLUMN_AUTHORITY + " INTEGER); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean isNeedPut() {
        return needPut;
    }

}
