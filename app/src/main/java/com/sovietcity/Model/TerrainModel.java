package com.sovietcity.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

//ландщафт клетки
public class TerrainModel implements Serializable {

    private  transient Bitmap texture;
    private boolean isBuildable;
    private String name;
    public TerrainModel(String name,Bitmap texture, boolean isBuildable) {
        this.texture = texture;
        this.name = name;
        this.isBuildable = isBuildable;
    }

    public TerrainModel(String name,boolean isBuildable) {
        this.isBuildable = isBuildable;
        this.name = name;
    }

    public Bitmap getTexture() {
        return this.texture;
    }

    public final boolean isBuildable() {
        return this.isBuildable;
    }

    public String getName() {
        return name;
    }
}
