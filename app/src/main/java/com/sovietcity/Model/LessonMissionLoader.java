package com.sovietcity.Model;

import android.content.Context;

import com.sovietcity.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Серёга on 04.07.2016.
 */
public class LessonMissionLoader {
    private ArrayList<String> missions;
    private int currentId;

    public void loadResources(Context context) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.lesson_mission_texts)));
        this.missions = arrayList;
        this.currentId = 0;
    }

    public ArrayList<String> getMissions() {
        return missions;
    }

    public int getCurrentId() {
        return currentId;
    }

    public void increaseId() {
        this.currentId++;
    }
}
