package com.sovietcity.Model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

//складывает задания в бд
public class TaskPutter implements Serializable {
    private ContentValues contentValues;

    public TaskPutter() {
        this.contentValues = new ContentValues();
    }
    public void putTask( String text, int year, int money, int needPeople) {
        contentValues.put("Text",text);
        contentValues.put("Year",year);
        contentValues.put("Reward",money);
        contentValues.put("People",needPeople);
        contentValues.putNull("Mat_name1");
        contentValues.putNull("Mat_name2");
        contentValues.putNull("Quantity1");
        contentValues.putNull("Quantity2");
        contentValues.putNull("Building");
    }

    public void putTask( String text, int year, int money, int needPeople, String needBuilding) {
        contentValues.put("Text",text);
        contentValues.put("Year",year);
        contentValues.put("Reward",money);
        contentValues.put("People",needPeople);
        contentValues.putNull("Mat_name1");
        contentValues.putNull("Mat_name2");
        contentValues.putNull("Quantity1");
        contentValues.putNull("Quantity2");
        contentValues.put("Building",needBuilding);
    }

    public void putTask( String text, int year, int money, int needPeople, String needBuilding, String material1, int quantity1) {
        contentValues.put("Text",text);

        contentValues.put("Year",year);
        contentValues.put("Reward",money);
        contentValues.put("People",needPeople);
        contentValues.put("Mat_name1",material1);
        contentValues.putNull("Mat_name2");
        contentValues.put("Quantity1",quantity1);
        contentValues.putNull("Quantity2");
        contentValues.put("Building",needBuilding);
    }

    public void putTask( String text, int year, int money, int needPeople, String needBuilding, String material1, int quantity1, String material2, int quantity2) {
        contentValues.put("Text",text);
        contentValues.put("Year",year);
        contentValues.put("Reward",money);
        contentValues.put("People",needPeople);
        contentValues.put("Mat_name1",material1);
        contentValues.put("Mat_name2",material2);
        contentValues.put("Quantity1",quantity1);
        contentValues.put("Quantity2",quantity2);
        contentValues.put("Building",needBuilding);
    }

    public void putTask(String text, int year, int money, String material1, int quantity1, String material2, int quantity2) {
        contentValues.put("Text",text);
        contentValues.put("Year",year);
        contentValues.put("Reward",money);
        contentValues.putNull("People");
        contentValues.put("Mat_name1",material1);
        contentValues.put("Mat_name2",material2);
        contentValues.put("Quantity1",quantity1);
        contentValues.put("Quantity2",quantity2);
        contentValues.putNull("Building");
    }

    public void putTask(String text, int year, int money, String material1, int quantity1) {
        contentValues.put("Text",text);
        contentValues.put("Year",year);
        contentValues.put("Reward",money);
        contentValues.putNull("People");
        contentValues.put("Mat_name1",material1);
        contentValues.putNull("Mat_name2");
        contentValues.put("Quantity1",quantity1);
        contentValues.putNull("Quantity2");
        contentValues.putNull("Building");
    }

    public void putTask(String text, int year, int money, String needBuilding) {
        contentValues.put("Text",text);
        contentValues.put("Year",year);
        contentValues.put("Reward",money);
        contentValues.putNull("People");
        contentValues.putNull("Mat_name1");
        contentValues.putNull("Mat_name2");
        contentValues.putNull("Quantity1");
        contentValues.putNull("Quantity2");
        contentValues.put("Building",needBuilding);
    }
    public void putValues(SQLiteDatabase sqLiteDatabase, String dbName) {
        sqLiteDatabase.insert(dbName,null,contentValues);
    }
}
