package com.sovietcity.Model;

import java.io.Serializable;

//    компонент, строящий кастомные по форме и длине дома
public class CustomBuildComponent extends BuildComponent implements Serializable {
    private int blocks;
    private int stages;
    private ConstructionFunctionComponent constructionFunctionComponent;
    private int nowBlocks;
    //    очень страшное и жуткое строительство
    private synchronized void build(MapManager mapManager, BuildManager buildManager, int y, int x, BuildModel buildModel) {
        if ((GameMap.getGameMapCell(y + 1, x) != null) && (GameMap.getGameMapCell(y + 1, x).isFocused())) {
            GameMap.getGameMapCell(y, x).setBuilding(3, buildModel);
            mapManager.setBuildModel(y, x);

        }
        if ((GameMap.getGameMapCell(y - 1, x) != null) && (GameMap.getGameMapCell(y - 1, x).isFocused())) {
            GameMap.getGameMapCell(y, x).setBuilding(2, buildModel);
            mapManager.setBuildModel(y, x);

        }
        if ((GameMap.getGameMapCell(y, x - 1) != null) && (GameMap.getGameMapCell(y, x - 1).isFocused())) {
            GameMap.getGameMapCell(y, x).setBuilding(1, buildModel);
            mapManager.setBuildModel(y, x);

        }
        if ((GameMap.getGameMapCell(y, x + 1) != null) && (GameMap.getGameMapCell(y, x + 1).isFocused())) {
            GameMap.getGameMapCell(y, x).setBuilding(0, buildModel);
            mapManager.setBuildModel(y, x);

        }
        if ((GameMap.getGameMapCell(y, x + 1) != null) && (GameMap.getGameMapCell(y, x + 1).isFocused()) && (GameMap.getGameMapCell(y, x - 1) != null) && (GameMap.getGameMapCell(y, x - 1).isFocused())) {
            GameMap.getGameMapCell(y, x).setBuilding(4, buildModel);

        }
        if ((GameMap.getGameMapCell(y + 1, x) != null) && (GameMap.getGameMapCell(y + 1, x).isFocused()) && (GameMap.getGameMapCell(y - 1, x) != null) && (GameMap.getGameMapCell(y - 1, x).isFocused())) {
            GameMap.getGameMapCell(y, x).setBuilding(5, buildModel);
            mapManager.setBuildModel(y, x);

        }
        if ((GameMap.getGameMapCell(y - 1, x) != null) && (GameMap.getGameMapCell(y - 1, x).isFocused()) && (GameMap.getGameMapCell(y, x + 1) != null) && (GameMap.getGameMapCell(y, x + 1).isFocused())) {
            GameMap.getGameMapCell(y, x).setBuilding(6, buildModel);
            mapManager.setBuildModel(y, x);

        }
        if ((GameMap.getGameMapCell(y - 1, x) != null) && (GameMap.getGameMapCell(y - 1, x).isFocused()) && (GameMap.getGameMapCell(y, x - 1) != null) && (GameMap.getGameMapCell(y, x - 1).isFocused())) {
            GameMap.getGameMapCell(y, x).setBuilding(7, buildModel);
            mapManager.setBuildModel(y, x);

        }
        if ((GameMap.getGameMapCell(y + 1, x) != null) && (GameMap.getGameMapCell(y + 1, x).isFocused()) && (GameMap.getGameMapCell(y, x - 1) != null) && (GameMap.getGameMapCell(y, x - 1).isFocused())) {
            GameMap.getGameMapCell(y, x).setBuilding(8, buildModel);
            mapManager.setBuildModel(y, x);

        }
        if ((GameMap.getGameMapCell(y + 1, x) != null) && (GameMap.getGameMapCell(y + 1, x).isFocused()) && (GameMap.getGameMapCell(y, x + 1) != null) && (GameMap.getGameMapCell(y, x + 1).isFocused())) {
            GameMap.getGameMapCell(y, x).setBuilding(9, buildModel);
            mapManager.setBuildModel(y, x);

        }
        if ((GameMap.getGameMapCell(y + 1, x) != null) && (GameMap.getGameMapCell(y + 1, x).isFocused()) && (GameMap.getGameMapCell(y, x + 1) != null) && (GameMap.getGameMapCell(y, x + 1).isFocused()) && (GameMap.getGameMapCell(y - 1, x) != null) && (GameMap.getGameMapCell(y - 1, x).isFocused())) {
            GameMap.getGameMapCell(y, x).setBuilding(10, buildModel);
            mapManager.setBuildModel(y, x);

        }

        if ((GameMap.getGameMapCell(y + 1, x) != null) && (GameMap.getGameMapCell(y + 1, x).isFocused()) && (GameMap.getGameMapCell(y, x - 1) != null) && (GameMap.getGameMapCell(y, x - 1).isFocused()) && (GameMap.getGameMapCell(y - 1, x) != null) && (GameMap.getGameMapCell(y - 1, x).isFocused())) {
            GameMap.getGameMapCell(y, x).setBuilding(11, buildModel);
            mapManager.setBuildModel(y, x);

        }
        if ((GameMap.getGameMapCell(y, x + 1) != null) && (GameMap.getGameMapCell(y, x + 1).isFocused()) && (GameMap.getGameMapCell(y, x - 1) != null) && (GameMap.getGameMapCell(y, x - 1).isFocused()) && (GameMap.getGameMapCell(y - 1, x) != null) && (GameMap.getGameMapCell(y - 1, x).isFocused())) {
            GameMap.getGameMapCell(y, x).setBuilding(12, buildModel);
            mapManager.setBuildModel(y, x);

        }
        if ((GameMap.getGameMapCell(y, x + 1) != null) && (GameMap.getGameMapCell(y, x + 1).isFocused()) && (GameMap.getGameMapCell(y, x - 1) != null) && (GameMap.getGameMapCell(y, x - 1).isFocused()) && (GameMap.getGameMapCell(y + 1, x) != null) && (GameMap.getGameMapCell(y + 1, x).isFocused())) {
            GameMap.getGameMapCell(y, x).setBuilding(13, buildModel);
            mapManager.setBuildModel(y, x);

        }
        if ((GameMap.getGameMapCell(y, x + 1) != null) && (GameMap.getGameMapCell(y, x + 1).isFocused()) && (GameMap.getGameMapCell(y, x - 1) != null) && (GameMap.getGameMapCell(y, x - 1).isFocused()) && (GameMap.getGameMapCell(y + 1, x) != null) && (GameMap.getGameMapCell(y + 1, x).isFocused()) && (GameMap.getGameMapCell(y - 1, x) != null) && (GameMap.getGameMapCell(y - 1, x).isFocused())) {
            GameMap.getGameMapCell(y, x).setBuilding(14, buildModel);
            mapManager.setBuildModel(y, x);
        }
        buildManager.finishBuilding();
    }

    @Override
    public synchronized boolean selectCell(MapManager mapManager, BuildManager buildManager, int y, int x, BuildModel buildModel) {
        if (this.nowBlocks == 0) {
            this.nowBlocks++;
            if (this.constructionFunctionComponent == null) {
                this.constructionFunctionComponent = buildModel.cloneConstructionFunctionComponent();
                this.constructionFunctionComponent.setMonths(this.blocks * this.constructionFunctionComponent.getMonths());
                this.constructionFunctionComponent.setNeedQuantity1(this.blocks * this.constructionFunctionComponent.getNeedQuantity1());
                this.constructionFunctionComponent.setNeedQuantity2(this.blocks * this.constructionFunctionComponent.getNeedQuantity2());
                this.constructionFunctionComponent.startFunction(buildModel);
                this.constructionFunctionComponent.setBlocks(blocks*stages);
                buildManager.addConstructorFunctionComponent(constructionFunctionComponent);
            }
            buildCell(buildManager, GameMap.getGameMapCell(y, x), constructionFunctionComponent);
            return true;
        }
        if ((GameMap.getGameMapCell(y, x).getTerrainModel().isBuildable() && (!GameMap.getGameMapCell(y, x).isFocused()) && ((GameMap.getGameMapCell(y + 1, x).isFocused()) || (GameMap.getGameMapCell(y - 1, x).isFocused()) || (GameMap.getGameMapCell(y, x + 1).isFocused()) || (GameMap.getGameMapCell(y, x - 1).isFocused())))) {
            GameMap.getGameMapCell(y, x).setFocused(true);
            buildCell(buildManager, GameMap.getGameMapCell(y, x), constructionFunctionComponent);
            this.nowBlocks++;
            if (this.blocks == this.nowBlocks) {
                this.nowBlocks = 0;
                for (int i = 0; i < GameMap.sizeY; i++) {
                    for (int j = 0; j < GameMap.sizeX; j++) {
                        if (GameMap.getGameMapCell(i, j).isFocused()) {
                            this.build(mapManager, buildManager, i, j, buildModel);

                        }

                    }
                }
                for (int i = 0; i < GameMap.sizeY; i++) {
                    for (int j = 0; j < GameMap.sizeX; j++) {
                        GameMap.getGameMapCell(i, j).setFocused(false);

                    }
                }
                constructionFunctionComponent = null;
            }
            return true;
        } else return true;
    }

    public synchronized boolean setBlocks(CashManager cashManager, int blocks) {
        if ((blocks > 1) && (blocks < 12)) {
            this.blocks = blocks;
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean setBlockPrice(int blockPrice) {
        return super.setBlockPrice(blockPrice);
    }

    @Override
    public synchronized boolean payForBuilding(CashManager cashManager) {
        return cashManager.wasteMoney(this.getBlockPrice() * this.blocks * this.stages);
    }

    public synchronized boolean setStages(int stages) {
        if ((stages > 0) && (stages < 12)) {
            this.stages = stages;
            return true;
        }
        return false;
    }

    public synchronized int getBlocks() {
        return this.blocks;
    }

    public synchronized int getStages() {
        return this.stages;
    }

    private synchronized void buildCell(BuildManager buildManager, Cell cell, ConstructionFunctionComponent constructionFunctionComponent) {
        cell.setFocused(true);
        cell.setConstruction(buildManager.getConstruction());
        cell.setChainDestination(constructionFunctionComponent);
        buildManager.setConstructionFunctionTranslator(constructionFunctionComponent, cell);
        constructionFunctionComponent.addCell(cell);
    }

    public void setConstructionFunctionComponentNull() {
        this.constructionFunctionComponent = null;
    }
}
