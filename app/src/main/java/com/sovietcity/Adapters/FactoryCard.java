package com.sovietcity.Adapters;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;


//    логическая структура для карточки.
public class FactoryCard {
    private Bitmap image;
    private String firstText;
    private String secondText;

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getFirstText() {
        return firstText;
    }

    public String getSecondText() {
        return secondText;
    }

    public void setFirstText(String firstText) {
        this.firstText = firstText;
    }

    public void setSecondText(String secondText) {
        this.secondText = secondText;
    }

}
