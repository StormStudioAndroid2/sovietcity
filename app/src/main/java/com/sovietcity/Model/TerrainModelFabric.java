package com.sovietcity.Model;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.sovietcity.Presenter.DrawerManager;
import com.sovietcity.R;

import java.io.Serializable;

//создает виды ландшафта
public class TerrainModelFabric implements Serializable {
    private TerrainModel grassTerrain;
    private TerrainModel treeTerrain;
    private TerrainModel buildTerrain;

    public TerrainModelFabric(Context context, DrawerManager drawerManager) {
        this.buildTerrain = new TerrainModel("Строение", false);
        this.grassTerrain = new TerrainModel("Трава", drawerManager.setSize(BitmapFactory.decodeResource(context.getResources(), R.drawable.grass)), true);
        this.treeTerrain = new TerrainModel("Дерево", drawerManager.setSize(BitmapFactory.decodeResource(context.getResources(), R.drawable.tree)), true);
    }

    public TerrainModel getGrassTerrain() {
        return this.grassTerrain;
    }

    public TerrainModel getBuildTerrain() {
        return buildTerrain;
    }

    public TerrainModel getTreeTerrain() {
        return treeTerrain;
    }
}
