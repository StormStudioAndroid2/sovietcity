package com.sovietcity.Model;

import android.content.Context;

import com.sovietcity.Presenter.DrawerManager;

import java.io.Serializable;

//менеджер карты
public class MapManager implements Serializable {
    private MapGenerator mapGenerator;
    private TerrainModelFabric terrainModelFabric;
    private TerrainModelSetter terrainModelSetter;

    public MapManager(ResourceManager resourceManager, DrawerManager drawerManager, Context context) {
        this.terrainModelFabric = new TerrainModelFabric(context, drawerManager);
        this.mapGenerator = new MapGenerator(resourceManager, this.terrainModelFabric);
        this.terrainModelSetter = new TerrainModelSetter();
    }

    public void setBuildModel(int y, int x) {
        this.terrainModelSetter.setBuildTerrain(this.terrainModelFabric, GameMap.getGameMapCell(y, x));
    }

    public void addBuildingOnCell(Cell cell) {
        cell.setTerrainModel(terrainModelFabric.getBuildTerrain());
    }

    public MapManager(DrawerManager drawerManager, Context context) {
        this.terrainModelFabric = new TerrainModelFabric(context, drawerManager);
        this.terrainModelSetter = new TerrainModelSetter();

    }

    public TerrainModelFabric getTerrainModelFabric() {
        return terrainModelFabric;
    }
}
