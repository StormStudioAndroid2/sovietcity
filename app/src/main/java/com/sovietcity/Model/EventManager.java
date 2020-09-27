package com.sovietcity.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.sovietcity.Presenter.World;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Серёга on 11.06.2016.
 */
public class EventManager {
    private EventDB eventDB;
    private EventGame nowEventGame;
    private ArrayList<EventGame> eventGames;
    private EventDBHelper eventDBHelper;
    private SQLiteDatabase sqLiteDatabase;

    public EventManager(World world, Context context) {
        boolean checkDB = checkDataBase(context);
        this.eventDB = new EventDB();
        this.eventDBHelper = EventDBHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = eventDBHelper.getWritableDatabase();
        eventDB.setEventSqLiteDatabase(sqLiteDatabase);
        if (checkDB) {
            eventDB.putEvents();
        }
        this.eventGames = eventDB.getEveryYearEvents(world);
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public EventGame getEvent(World world, GregorianCalendar gregorianCalendar) {
        Date date = new Date(gregorianCalendar.getTimeInMillis());
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("MM-dd");
        String str = format1.format(date);
        String str1 = format2.format(date);
        Log.i("DATE", str);
        nowEventGame = eventDB.getEventGame(world, str);
        if (nowEventGame==null) {
            nowEventGame = this.getEveryYearEvent(str1);
        }
        return nowEventGame;
    }

    public boolean hasEvent() {
        if (nowEventGame != null) {
            return true;
        }
        return false;
    }

    public EventGame getNowEventGame() {
        return nowEventGame;
    }


    public void setNowEventGame(EventGame nowEventGame) {
        this.nowEventGame = nowEventGame;
    }

    public void closeDatabase() {
        sqLiteDatabase.close();
    }
    public EventGame getEveryYearEvent(String date) {
        for (int i = 0; i<eventGames.size(); i++) {
            if (date.equals(eventGames.get(i).getDate())) {
                return eventGames.get(i);
            }
        }
        return null;
    }
    private boolean checkDataBase(Context context) {
        SQLiteDatabase checkDB = null;
        String str = context.getFilesDir().getPath()+"data/com.sovietcity/databases/events.db";
        Log.i("PATH",str);
        try {
            checkDB = SQLiteDatabase.openDatabase("data/data/com.sovietcity/databases/events.db", null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
        }
        return checkDB == null;
    }
}
