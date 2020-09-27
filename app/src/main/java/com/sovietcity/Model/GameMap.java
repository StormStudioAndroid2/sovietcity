package com.sovietcity.Model;

import com.sovietcity.Presenter.World;

import java.io.Serializable;

/**
 * Created by Серёга on 10.04.2016.
 */
public class GameMap implements Serializable {
    //карта игры. Синглтон
        public static final int sizeX = 100;
    public static final int sizeY = 100;
    private static Cell[][] gameMap;

    public static Cell getGameMapCell(int y, int x) {
        if ((y>-1)&&(y<sizeX)&&(x>-1)&&(x<sizeY)) {
            if (gameMap == null) {
                gameMap = getGameMap();

                return gameMap[y][x];
            } else {
                return gameMap[y][x];
            }
        }
        return null;
    }

    public static Cell[][] getGameMap() {
        if (gameMap == null) {
            gameMap = new Cell[sizeY][sizeX];
            return gameMap;
        }
        return gameMap;
    }

    public static void setGameMap(Cell[][] gameMap1) {
        gameMap = gameMap1;
    }

    public static void loadMap(World world, SaveCell[][] saveCells) {
        gameMap = new Cell[sizeY][sizeX];
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                gameMap[i][j] = new Cell(world, saveCells[i][j]);
            }
        }
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                gameMap[i][j].reloadInterfaces();
            }
        }
    }
}
