package com.sovietcity.Model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Серёга on 11.06.2016.
 */
public class EventPutter {
    private static final String EVENT_COLUMN_ID = "_id";
    private static final String EVENT_COLUMN_TEXT = "Text";
    private static final String EVENT_COLUMN_DATE = "Date";
    private static final String EVENT_COLUMN_IS_EVERY_YEAR = "EveryYear";

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

    private ContentValues eventContentValues;
    private ContentValues answerContentValues;

    public EventPutter() {
        this.eventContentValues = new ContentValues();
        this.answerContentValues = new ContentValues();
    }

    public void putEventValues(SQLiteDatabase sqLiteDatabase, String dbName) {
        sqLiteDatabase.insert(dbName, null, eventContentValues);
    }

    public void putAnswerValues(SQLiteDatabase sqLiteDatabase, String dbName) {
        sqLiteDatabase.insert(dbName, null, answerContentValues);
    }

    public void putEvent(String text, String date, boolean isEveryYear) {
        eventContentValues.put(EVENT_COLUMN_TEXT, text);
        eventContentValues.put(EVENT_COLUMN_DATE, date);
        eventContentValues.put(EVENT_COLUMN_IS_EVERY_YEAR,(isEveryYear) ? 1 : 0 );
    }

    public void putAnswer(int id, String text, int money, int people, int authority) {
        answerContentValues.put(ANSWER_COLUMN_ID,id);
        answerContentValues.put(ANSWER_COLUMN_TEXT, text);
        answerContentValues.put(ANSWER_COLUMN_MONEY, money);
        answerContentValues.put(ANSWER_COLUMN_PEOPLE, people);

        answerContentValues.put(ANSWER_COLUMN_AUTHORITY, authority);
    }
}
