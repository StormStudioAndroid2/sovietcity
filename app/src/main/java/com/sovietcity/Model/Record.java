package com.sovietcity.Model;

public class Record implements Comparable<Record> {
    private int position;
    private String name;
    private Double money;
    private Integer happiness;

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public Double getMoney() {
        return money;
    }

    public Integer getHappiness() {
        return happiness;
    }

    public String getName() {
        return name;
    }

    public void setHappiness(Integer happiness) {
        this.happiness = happiness;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int compareTo(Record another) {
        double sc = ((another.getMoney() / this.getMoney()) + ((double) another.getHappiness() / this.getHappiness())) / 2;
        if (sc > 1) {
            return 1;
        }
        if (sc < 1) {
            return -1;
        }
        return 0;
    }
}

