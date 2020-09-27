package com.sovietcity.Model;

import java.io.Serializable;

/**
 * Created by Серёга on 10.04.2016.
 */
public class TerrainModelSetter implements Serializable {
    public void setBuildTerrain(TerrainModelFabric terrain, Cell cell) {
        cell.setTerrainModel(terrain.getBuildTerrain());
    }
}
