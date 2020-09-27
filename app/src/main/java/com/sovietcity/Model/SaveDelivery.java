package com.sovietcity.Model;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Created by Серёга on 23.05.2016.
 */
public class SaveDelivery implements Serializable {
    private String resource;
    private double quantity;
    private GregorianCalendar moveDate;
    private int x;
    private int y;
    private int lastX;
    private int lastY;
    private boolean free;

    public int getY() {
        return y;
    }

    public double getQuantity() {
        return quantity;
    }

    public int getX() {
        return x;
    }

    public GregorianCalendar getMoveDate() {
        return moveDate;
    }

    public String getResource() {
        return resource;
    }
    public SaveDelivery(Delivery delivery) {
        this.moveDate = delivery.getMoveDate();
        this.quantity = delivery.getQuantity();
        if (this.resource!=null) {
            this.resource = delivery.getResource().getName();
        } else {
            this.resource = "null";
        }
        this.x = delivery.getX();
        this.y = delivery.getY();
        if (delivery.getLastCell()!=null) {
            this.lastX = delivery.getLastCell().getX();
            this.lastY = delivery.getLastCell().getY();
        } else {
            this.lastX = -1;
            this.lastY = -1;
        }
        this.free = delivery.isFree();
    }

    public int getLastY() {
        return lastY;
    }

    public int getLastX() {
        return lastX;
    }

    public boolean isFree() {
        return free;
    }
}
