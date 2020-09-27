package com.sovietcity.Model;

/**
 * Created by Серёга on 11.06.2016.
 */
public class EventAnswerDataContainer {
    private String text;
    private int money;
    private int people;
    private int authority;

    public void setText(String text) {
        this.text = text;
    }


    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setPeople(int people) {
        this.people = people;
    }


    public int getMoney() {
        return money;
    }

    public int getAuthority() {
        return authority;
    }

    public int getPeople() {
        return people;
    }

    public String getText() {
        return text;
    }

}
