package com.sovietcity.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

//ресурс и инфа о нем
public class Resource implements Serializable {

    private String name;
    private transient Bitmap image;
    private int price;

    public Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return this.image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}