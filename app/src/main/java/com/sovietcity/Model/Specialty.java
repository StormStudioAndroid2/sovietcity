package com.sovietcity.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;

//Отрасль производства. Класс хранит всю инфу об отрасли
public class Specialty implements Serializable {
    private String name;
    private int busyWorkplaces;
    private int allWorkplaces;
    private int baseChance;
    private int chance;
    private transient Bitmap image;
    private int iContainerWorkplaces;

    public int getAllWorkplaces() {
        return allWorkplaces;
    }

    public int getBusyWorkplaces() {
        return busyWorkplaces;
    }

    public void addWorkplace() {

        this.allWorkplaces++;
    }

    public Specialty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getChance() {

        if (this.iContainerWorkplaces <= allWorkplaces) {
            return 0;
        }
        return chance;
    }

    public void setChance(int chance) {

        this.chance = chance;
    }

    public int getBaseChance() {
        if (this.iContainerWorkplaces <= allWorkplaces) {
            return 0;
        }
        return baseChance;
    }

    public void setBaseChance(int baseChance) {
        this.baseChance = baseChance;
    }

    public void addBusyWorkplaces(int busyWorkplaces) {
        this.busyWorkplaces += busyWorkplaces;
    }

    public void deleteWorkplace() {
        this.allWorkplaces--;
    }

    public void setBusyWorkplaces(int busyWorkplaces) {
        this.busyWorkplaces = busyWorkplaces;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setImage(Context context, int image) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), image);
        this.image = bitmap;
    }

    public int getiContainerWorkplaces() {
        return iContainerWorkplaces;
    }

    public void setiContainerWorkplaces(int iContainerWorkplaces) {
        this.iContainerWorkplaces = iContainerWorkplaces;
    }
}
