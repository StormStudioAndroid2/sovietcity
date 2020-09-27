package com.sovietcity.Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Серёга on 11.06.2016.
 */
public class EventGame {
    private ArrayList<EventAnswer> eventAnswers;
    private String text;
    private String date;
    public EventGame() {
        this.eventAnswers = new ArrayList<>();
    }
    public void addAnswer(EventAnswer eventAnswer) {
        this.eventAnswers.add(eventAnswer);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<EventAnswer> getEventAnswers() {
        return eventAnswers;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }
}
