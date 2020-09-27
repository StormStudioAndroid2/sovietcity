package com.sovietcity.Model;

import java.io.Serializable;

//компонент, отвечающий за заданную постройку здания
public class SpecifiedBuildComponent extends BuildComponent implements Serializable {
    private int blockOnX;   //отображает кол-во блоков на x
    private int blockOnY;
    private Resource needResource;

    private synchronized boolean build(MapManager mapManager, BuildManager buildManager, int y, int x, BuildModel buildModel) {
        int ind = 0;
        if (checkResource(y, x)) {
            ConstructionFunctionComponent constructionFunctionComponent = buildModel.cloneConstructionFunctionComponent();
            buildManager.addConstructorFunctionComponent(constructionFunctionComponent);
            for (int i = y; i < y + blockOnY; i++) {
                for (int j = x; j < x + blockOnX; j++) {
                    buildCell(mapManager, buildModel, GameMap.getGameMapCell(i, j), buildManager.getConstruction(), constructionFunctionComponent, ind);
                    ind++;
                }
            }
            buildManager.finishBuilding();
            constructionFunctionComponent.startFunction(buildModel);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean selectCell(MapManager mapManager, BuildManager buildManager, int y, int x, BuildModel buildModel) {
        boolean flag = true;
        for (int i = y; i < y + blockOnY; i++) {
            for (int j = x; j < x + blockOnX; j++) {
                if ((GameMap.getGameMapCell(i, j) != null) && (!GameMap.getGameMapCell(i, j).getTerrainModel().isBuildable())) {
                    flag = false;
                }
            }
        }
        if (flag) {
            this.build(mapManager, buildManager, y, x, buildModel);
        }
        return flag;
    }

    public synchronized boolean setBlocks(int y, int x) {
        this.blockOnY = y;
        this.blockOnX = x;
        return true;
    }

    @Override
    public synchronized boolean setBlockPrice(int blockPrice) {
        return super.setBlockPrice(blockPrice);
    }

    @Override
    public synchronized boolean payForBuilding(CashManager cashManager) {
        return cashManager.wasteMoney(this.getBlockPrice() * this.blockOnX * this.blockOnY);
    }

    public synchronized void setNeedResource(Resource needResource) {
        this.needResource = needResource;
    }

    private synchronized boolean checkResource(int y, int x) {
        if (needResource != null) {
            boolean flag = false;
            for (int i = y; i < y + blockOnY; i++) {
                for (int j = x; j < x + blockOnX; j++) {
                    if ((GameMap.getGameMapCell(i, j).hasResource()) && (GameMap.getGameMapCell(i, j).getResource().equals(this.needResource))) {
                        flag = true;
                    }
                }
            }
            return flag;
        } else return true;
    }

    private void buildCell(MapManager mapManager, BuildModel buildModel, Cell cell, Construction construction, final ConstructionFunctionComponent constructionFunctionComponent, int ind) {
        mapManager.addBuildingOnCell(cell);
        cell.setBuilding(ind, buildModel);
        cell.setBuildModel(buildModel);
        cell.setConstruction(construction);
        cell.setChainDestination(constructionFunctionComponent);

        constructionFunctionComponent.addCell(cell);
        cell.setFunctionTranslator(new FunctionTranslator() {
            @Override
            public FactoryFunctionComponent translateFactoryFunctionComponent() {
                return null;
            }

            @Override
            public PopulationFunctionComponent translatePopulationFunctionComponent() {
                return null;
            }

            @Override
            public RoadFunctionComponent translateRoadFunctionComponent() {
                return null;
            }

            @Override
            public StockFunctionComponent translateStockFunctionComponent() {
                return null;
            }

            @Override
            public TaskFunctionComponent translateTaskFunctionComponent() {
                return null;
            }

            @Override
            public ConstructionFunctionComponent translateConstructionFunctionComponent() {
                return constructionFunctionComponent;
            }

            @Override
            public FunctionComponent translateFunctionComponent() {
                return constructionFunctionComponent;
            }

            @Override
            public ShopFunctionComponent translateShopFunctionComponent() {
                return null;
            }

            @Override
            public SchoolFunctionComponent translateSchoolFunctionComponent() {
                return null;
            }

            @Override
            public HospitalFunctionComponent translateHospitalFunctionComponent() {
                return null;
            }
        });
    }
}
