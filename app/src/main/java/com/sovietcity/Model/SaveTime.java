package com.sovietcity.Model;

import java.io.Serializable;

/**
 * Created by Серёга on 24.05.2016.
 */
public class SaveTime implements Serializable {
    private int year;
    private int day;
    private int month;

    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
