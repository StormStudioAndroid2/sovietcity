package com.sovietcity.Model;

import java.io.Serializable;

//генерация карты и ресурсов
public class MapGenerator  {


    private void generateTerrain(TerrainModelFabric terrainModelFabric, ResourceManager resourceManager) {
        for (int i = 0; i < GameMap.sizeY; i++) {
            for (int j = 0; j <  GameMap.sizeX; j++) {
                int rnd = (int) (Math.random() * 10 + 1);
                if (rnd < 6) {
                    GameMap.getGameMap()[i][j] = new Cell();

                    GameMap.getGameMapCell(i, j).setTerrainModel(terrainModelFabric.getGrassTerrain());
                } else {
                    GameMap.getGameMap()[i][j] = new Cell();
                    GameMap.getGameMapCell(i, j).setTerrainModel(terrainModelFabric.getTreeTerrain());

                }

                rnd = (int) (Math.random() * 100 + 1);
                if (rnd <= 5) {
                    rnd = (int) (Math.random() * 100 + 1);
                    if (rnd < 30) {
                        GameMap.getGameMapCell(i, j).setResource(resourceManager.getResource("Железная руда"));
                    } else {
                        GameMap.getGameMapCell(i, j).setResource(resourceManager.getResource("Песок"));

                    }
                }
                GameMap.getGameMap()[i][j].setX(j);
                GameMap.getGameMap()[i][j].setY(i);

            }
        }
    }

    public MapGenerator(ResourceManager resourceManager, TerrainModelFabric terrainModelFabric) {

        this.generateTerrain(terrainModelFabric, resourceManager);
    }

}
