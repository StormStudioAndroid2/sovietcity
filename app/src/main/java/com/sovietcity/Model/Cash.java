package com.sovietcity.Model;

import java.io.Serializable;

//    хранилище денюжек
public class Cash implements Serializable {
    private double amount;

    public Cash(double number) {
        amount = number;
    }

    public double getAmount() {
        return amount;
    }

    public void addCash(double number) {
        amount += number;
    }

    public boolean wasteCash(double number) {
        if (amount >= number) {
            amount -= number;
            return true;
        } else return false;
    }


}

