package com.sovietcity.Model;

import android.util.Log;

import com.sovietcity.Presenter.World;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

//    класс, который реализует логику одной Доставки материала от одного здания к другому
public class Delivery implements Comparable<Delivery>, Serializable {
    private Resource resource;
    private double quantity;
    private transient Cell lastCell;
    private GregorianCalendar moveDate;
    private double price;
    private int x;

    private boolean free;
    private int y;

    public Cell getLastCell() {
        return lastCell;
    }

    public void setLastCell(Cell lastCell) {
        this.lastCell = lastCell;
    }

    @Override
    public int compareTo(Delivery another) {

        if (this.moveDate.after(another.moveDate)) {
            return 1;
        }
        if (this.moveDate.before(another.moveDate)) {
            return -1;
        }
        return 0;
    }

    public GregorianCalendar getMoveDate() {
        return this.moveDate;
    }
    public void setResource(Resource r) {
        this.resource = r;
    }

    public void setQuantity(double quantity1) {
        this.quantity = quantity1;
    }


    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public double getQuantity() {
        return quantity;
    }

    public int getY() {
        return y;
    }

    public Resource getResource() {
        return resource;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFree(boolean free) {
        this.free = free;
        this.lastCell=null;
    }

    public Delivery(World world, SaveDelivery saveDelivery) {
        if (saveDelivery.getResource()!=null) {
            this.resource = world.getResourceManager().getResource(saveDelivery.getResource());
        } else {
            this.resource = null;
        }
        this.quantity = saveDelivery.getQuantity();
        this.moveDate = saveDelivery.getMoveDate();
        this.x = saveDelivery.getX();
        this.y = saveDelivery.getY();
        if (saveDelivery.getLastX()!=-1) {
            this.lastCell = GameMap.getGameMapCell(saveDelivery.getY(),saveDelivery.getLastX());
        }
        this.free = saveDelivery.isFree();
    }

    public Delivery() {
        this.moveDate = new GregorianCalendar();
    }

    public void setDate(int year, int month, int day) {
        if (this.moveDate == null) {
            this.moveDate = new GregorianCalendar(year, month, day);
        } else {
            this.moveDate.set(year, month, day);
        }

    }

    public boolean isFree() {
        return free;
    }
}

