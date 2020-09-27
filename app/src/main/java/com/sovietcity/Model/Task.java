package com.sovietcity.Model;

import java.io.Serializable;

//хранит инфу о задании в игре
public class Task implements Serializable {
    private String text;
    private int year;
    private int reward;
    private int needPeople;
    private String needBuilding;
    private String material1;
    private String material2;
    private int quantity1;
    private int quantity2;


    public String getNeedBuilding() {
        return needBuilding;
    }

    public int getNeedPeople() {
        return needPeople;
    }

    public int getQuantity1() {
        return quantity1;
    }

    public int getQuantity2() {
        return quantity2;
    }

    public int getReward() {
        return reward;
    }

    public int getYear() {
        return year;
    }

    public String getMaterial1() {
        return material1;
    }

    public String getMaterial2() {
        return material2;
    }

    public String getText() {
        return text;
    }

    public void setMaterial1(String material1) {
        this.material1 = material1;
    }

    public void setMaterial2(String material2) {
        this.material2 = material2;
    }

    public void setNeedBuilding(String needBuilding) {
        this.needBuilding = needBuilding;
    }

    public void setNeedPeople(int needPeople) {
        this.needPeople = needPeople;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public void setQuantity1(int quantity1) {
        this.quantity1 = quantity1;
    }

    public void setQuantity2(int quantity2) {
        this.quantity2 = quantity2;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
