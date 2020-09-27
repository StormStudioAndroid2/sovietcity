package com.sovietcity.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sovietcity.Presenter.DrawerManager;
import com.sovietcity.R;

import java.io.Serializable;

/**
 * Created by Серёга on 13.05.2016.
 */
//    что то вроде маркера, обозначающего стройку на клетке
public class Construction implements Serializable {
    private transient Bitmap image;

    public Construction(Context context, DrawerManager drawerManager) {
        this.image = drawerManager.setSize(BitmapFactory.decodeResource(context.getResources(), R.drawable.construct_texture));
    }

    public Bitmap getImage() {
        return image;
    }
}
