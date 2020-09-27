package com.sovietcity.Model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

//менеджер, управляющий временем
public class TimeManager  implements Serializable {
    private GregorianCalendar date;

    public TimeManager(int day, int month, int year) {
        GregorianCalendar date = new GregorianCalendar();
        date.set(year,month,day);
        this.date = date;
    }

    public void update() {
        date.add(Calendar.DAY_OF_MONTH,1);

    }

    @Override
    public String toString() {

        String s = Integer.toString(date.get(Calendar.DAY_OF_MONTH));
        switch (date.get(Calendar.MONTH)) {
            case 0:
                s = s + " января";
                break;
            case 1:
                s = s + " февраля";
                break;
            case 2:
                s = s + " марта";
                break;
            case 3:
                s = s + " апреля";
                break;
            case 4:
                s = s + " мая";
                break;
            case 5:
                s = s + " июня";
                break;
            case 6:
                s = s + " июля";
                break;
            case 7:
                s = s + " августа";
                break;
            case 8:
                s = s + " сентября";
                break;
            case 9:
                s = s + " октября";
                break;
            case 10:
                s = s + " ноября";
                break;
            case 11:
                s = s + " декабря";
                break;
        }
        s = s + ", " + date.get(Calendar.YEAR) + " год";
        return s;
    }
    public boolean isFirstMonthDay() {
        if (date.get(Calendar.DAY_OF_MONTH)==1) {
            return true;
        }
        return false;

    }

    public GregorianCalendar getDate() {
        return date;
    }
}
