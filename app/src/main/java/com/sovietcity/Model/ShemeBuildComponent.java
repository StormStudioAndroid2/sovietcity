package com.sovietcity.Model;

import java.io.Serializable;

//компонент, строящий дороги
public class ShemeBuildComponent extends BuildComponent implements Serializable {
    private int cells;
    private int nowCells;
    @Override
    public synchronized boolean selectCell(MapManager mapManager, BuildManager buildManager, int y, int x, BuildModel buildModel) {
        if (this.nowCells == 0) {
            this.nowCells++;
            GameMap.getGameMapCell(y, x).setFocused(true);
            return true;
        }
        if ((GameMap.getGameMapCell(y, x).getTerrainModel().isBuildable() && (!GameMap.getGameMapCell(y, x).isFocused()) && ((isCorrect(GameMap.getGameMapCell(y + 1, x))) || (isCorrect(GameMap.getGameMapCell(y - 1, x))) || (isCorrect(GameMap.getGameMapCell(y, x + 1))) || (isCorrect(GameMap.getGameMapCell(y, x - 1)))))) {
            GameMap.getGameMapCell(y, x).setFocused(true);
            this.nowCells++;
            if (this.cells == this.nowCells) {
                this.nowCells = 0;
                for (int i = 0; i < 100; i++) {
                    for (int j = 0; j < 100; j++) {
                        if (isCorrectWithoutBuilding(GameMap.getGameMapCell(i,j))) {
                            this.build(mapManager,buildManager, i, j, buildModel);
                            GameMap.getGameMapCell(i,j).setiRoadFunction(buildModel.getiRoadFunction());

                            GameMap.getGameMapCell(i,j).setChainDestination(buildModel.getiRoadFunction());
                        }
                    }
                }
                for (int i = 0; i < 100; i++) {
                    for (int j = 0; j < 100; j++) {
                        if (isCorrectWithoutBuilding(GameMap.getGameMapCell(i,j))) {
                            this.build(mapManager,buildManager, i, j, buildModel);
                            GameMap.getGameMapCell(i,j).setiRoadFunction(buildModel.getiRoadFunction());
                            GameMap.getGameMapCell(i,j).setChainDestination(buildModel.getiRoadFunction());

                        }
                    }
                }
                for (int i = 0; i < 100; i++) {
                    for (int j = 0; j < 100; j++) {
                        GameMap.getGameMapCell(i, j).setFocused(false);

                    }
                }
            }
            return true;
        } else return false;
    }

    public boolean setCells(int cells) {
        if ((cells > 1) && (cells < 100)) {
            this.cells= cells;
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
        return cashManager.wasteMoney(this.getBlockPrice() * this.cells);
    }


    private synchronized void build(MapManager mapManager, BuildManager buildManager, int y, int x, final BuildModel buildModel) {
        final RoadFunctionComponent roadFunctionComponent = buildModel.getFunctionTranslator().translateRoadFunctionComponent();
        if (isCorrect(GameMap.getGameMapCell(y + 1, x))) {
            GameMap.getGameMapCell(y, x).setBuilding(3, buildModel);
            mapManager.setBuildModel(y, x);
        }
        if (isCorrect(GameMap.getGameMapCell(y - 1, x))) {
            GameMap.getGameMapCell(y, x).setBuilding(2, buildModel);
            mapManager.setBuildModel(y,x);

        }
        if (isCorrect(GameMap.getGameMapCell(y, x - 1))) {
            GameMap.getGameMapCell(y, x).setBuilding(1, buildModel);
            mapManager.setBuildModel(y,x);

        }
        if (isCorrect(GameMap.getGameMapCell(y, x + 1))) {
            GameMap.getGameMapCell(y, x).setBuilding(0, buildModel);
            mapManager.setBuildModel(y,x);

        }
        if ((isCorrect(GameMap.getGameMapCell(y, x + 1))) && (isCorrect(GameMap.getGameMapCell(y, x - 1)))) {
            GameMap.getGameMapCell(y, x).setBuilding(4, buildModel);

        }
        if ((isCorrect(GameMap.getGameMapCell(y + 1, x))) && (isCorrect(GameMap.getGameMapCell(y, x - 1)))) {
            GameMap.getGameMapCell(y, x).setBuilding(5, buildModel);
            mapManager.setBuildModel(y,x);

        }
        if (isCorrect(GameMap.getGameMapCell(y - 1, x)) && (isCorrect(GameMap.getGameMapCell(y, x + 1)))) {
            GameMap.getGameMapCell(y, x).setBuilding(6, buildModel);
            mapManager.setBuildModel(y,x);

        }
        if (isCorrect(GameMap.getGameMapCell(y - 1, x)) &&(isCorrect(GameMap.getGameMapCell(y, x - 1)))) {
            GameMap.getGameMapCell(y, x).setBuilding(7, buildModel);
            mapManager.setBuildModel(y,x);

        }
        if ((isCorrect(GameMap.getGameMapCell(y + 1, x))) && (isCorrect(GameMap.getGameMapCell(y, x - 1)))) {
            GameMap.getGameMapCell(y, x).setBuilding(8, buildModel);
            mapManager.setBuildModel(y,x);

        }
        if ((isCorrect(GameMap.getGameMapCell(y + 1, x))) && (isCorrect(GameMap.getGameMapCell(y, x + 1)))) {
            GameMap.getGameMapCell(y, x).setBuilding(9, buildModel);
            mapManager.setBuildModel(y,x);

        }
        if ((isCorrect(GameMap.getGameMapCell(y + 1, x))) && (isCorrect(GameMap.getGameMapCell(y, x +1)))&&(isCorrect(GameMap.getGameMapCell(y - 1, x)))) {
            GameMap.getGameMapCell(y, x).setBuilding(10, buildModel);
            mapManager.setBuildModel(y,x);

        }

        if ((isCorrect(GameMap.getGameMapCell(y + 1, x))) && (isCorrect(GameMap.getGameMapCell(y, x -1)))&&(isCorrect(GameMap.getGameMapCell(y - 1, x)))) {
            GameMap.getGameMapCell(y, x).setBuilding(11, buildModel);
            mapManager.setBuildModel(y,x);

        }
        if ((isCorrect(GameMap.getGameMapCell(y, x+1))) && (isCorrect(GameMap.getGameMapCell(y, x -1)))&&(isCorrect(GameMap.getGameMapCell(y-1, x)))) {
            GameMap.getGameMapCell(y, x).setBuilding(12, buildModel);
            mapManager.setBuildModel(y,x);

        }
        if ((isCorrect(GameMap.getGameMapCell(y, x+1))) && (isCorrect(GameMap.getGameMapCell(y, x -1)))&&(isCorrect(GameMap.getGameMapCell(y + 1, x)))) {
            GameMap.getGameMapCell(y, x).setBuilding(13, buildModel);
            mapManager.setBuildModel(y,x);

        }
        if ((isCorrect(GameMap.getGameMapCell(y, x+1))) && (isCorrect(GameMap.getGameMapCell(y, x -1)))&&(isCorrect(GameMap.getGameMapCell(y+1, x)))&&(isCorrect(GameMap.getGameMapCell(y-1,x)))) {
            GameMap.getGameMapCell(y, x).setBuilding(14, buildModel);
            mapManager.setBuildModel(y,x);

        }
        GameMap.getGameMapCell(y,x).setFunctionTranslator(new FunctionTranslator() {
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
                return roadFunctionComponent;
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
                return null;
            }

            @Override
            public FunctionComponent translateFunctionComponent() {
                return roadFunctionComponent;
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
        buildManager.finishBuilding();

    }
    private boolean hasRoad(Cell cell) {
        if ((cell.hasBuildModel())||(cell.isFocused())) {
            return true;
        }
        return false;
    }
    private boolean hasRoadWithoutBuilding(Cell cell) {
        if ((cell.getFunctionTranslator()!=null)&&(cell.getFunctionTranslator().translateRoadFunctionComponent()!=null)||(cell.isFocused())) {
            return true;
        }
        return false;
    }
    private synchronized boolean isCorrect(Cell cell) {
        if ((cell!=null) && (hasRoad(cell))) {
            return true;
        }
        return false;
    }
    private synchronized boolean isCorrectWithoutBuilding(Cell cell) {
        if ((cell!=null) && (hasRoadWithoutBuilding(cell))) {
            return true;
        }
        return false;
    }
}
