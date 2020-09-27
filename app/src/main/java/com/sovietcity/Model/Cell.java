package com.sovietcity.Model;

import android.graphics.Bitmap;
import android.util.Log;

import com.sovietcity.Presenter.World;

import java.io.Serializable;

/**
 * Created by Серёга on 20.12.2015.
 */
//    класс, содержащий всю инфу о клетке
public class Cell {
    private int x;
    private int y;
    private boolean focused;
    private TerrainModel terrainModel;
    private BuildModel buildModel;
    private transient Bitmap texture;
    private Resource resource;
    private IChainDestination chainDestination;
    private IChainSender chainSender;
    private IRoadFunction iRoadFunction;
    private FunctionTranslator functionTranslator;
    private Construction construction;
    private int buildId;

    public TerrainModel getTerrainModel() {
        return this.terrainModel;
    }

    public BuildModel getBuildModel() {
        return this.buildModel;
    }

    public void setBuilding(int id, BuildModel buildModel) {
        this.buildModel = buildModel;
        this.texture = buildModel.getTexture(id);
        this.buildId = id;
    }

    public void setBuildModel(BuildModel buildModel) {
        this.buildModel = buildModel;
    }

    public boolean isFocused() {
        return this.focused;
    }

    public Bitmap draw() {
        if (construction != null) {
            return this.construction.getImage();
        }
        return this.texture;
    }

    public void setTerrainModel(TerrainModel terrainModel) {
        this.terrainModel = terrainModel;
        if (this.terrainModel.getTexture() != null) {
            this.texture = terrainModel.getTexture();
        }
    }

    public void setFocused(boolean b) {
        if ((terrainModel.isBuildable()) || (!b)) {
            this.focused = b;
        }
    }


    public boolean hasBuildModel() {
        if (this.buildModel != null) {
            return true;
        }
        return false;
    }


    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public boolean hasResource() {
        if (this.resource != null) {
            return true;
        }
        return false;
    }

    public boolean hasChainDestination() {
        if (this.chainDestination != null) {
            return true;
        }
        return false;
    }

    public void setChainDestination(IChainDestination chainDestination) {
        this.chainDestination = chainDestination;
        if (this.chainDestination != null) {
            int[] dy = new int[]{0, 0, -1, 1};
            int[] dx = new int[]{-1, 1, 0, 0};
            for (int i = 0; i < 4; i++) {
                if ((GameMap.getGameMapCell(y + dy[i], x + dx[i]).hasChainSender()) && (!chainDestination.equals(GameMap.getGameMapCell(y + dy[i], x + dx[i]).getChainSender()))) {
                    GameMap.getGameMapCell(y + dy[i], x + dx[i]).getChainSender().addCell(GameMap.getGameMapCell(y + dy[i], x + dx[i]));
                }
            }
        }
    }

    public IChainDestination getChainDestination() {
        return chainDestination;
    }

    public boolean hasChainSender() {
        if (this.chainSender != null) {
            return true;
        }
        return false;
    }

    public void setChainSender(IChainSender chainSender) {
        this.chainSender = chainSender;
        if (this.chainSender != null) {

            int[] dy = new int[]{0, 0, -1, 1};
            int[] dx = new int[]{-1, 1, 0, 0};
            for (int i = 0; i < 4; i++) {
                if ((GameMap.getGameMapCell(y + dy[i], x + dx[i]).hasChainDestination()) && (!chainSender.equals(GameMap.getGameMapCell(y + dy[i], x + dx[i]).getChainDestination()))) {
                    chainSender.addCell(GameMap.getGameMapCell(y + dy[i], x + dx[i]));
                }
            }
        }
    }

    public IChainSender getChainSender() {
        return chainSender;
    }

    public void setiRoadFunction(IRoadFunction iRoadFunction) {
        this.iRoadFunction = iRoadFunction;
    }
    public void deleteRoad(TerrainModelFabric terrainModelFabric) {
        this.chainDestination = null;
        this.chainSender = null;
        this.functionTranslator = null;
        this.iRoadFunction = null;
        this.buildModel = null;
        this.construction = null;
        this.terrainModel = terrainModelFabric.getTreeTerrain();
        this.texture = this.terrainModel.getTexture();
        this.buildId = -1;    }
    public IRoadFunction getiRoadFunction() {
        return iRoadFunction;
    }

    public boolean hasiRoadFunction() {
        if ((this.functionTranslator != null) && (this.getFunctionTranslator().translateRoadFunctionComponent() != null)) {
            return true;
        }
        return false;
    }

    public void setFunctionTranslator(FunctionTranslator functionTranslator) {
        this.functionTranslator = functionTranslator;

    }

    public FunctionTranslator getFunctionTranslator() {
        return functionTranslator;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setConstruction(Construction construction) {
        this.construction = construction;
    }

    public Construction getConstruction() {
        return construction;
    }

    public int getBuildId() {
        return buildId;
    }

    public Cell(World world, final SaveCell saveCell) {
        this.x = saveCell.getX();
        this.y = saveCell.getY();

        if (saveCell.getConstructionFunctionComponent() != null) {
            saveCell.getConstructionFunctionComponent().addCell(this);
            this.chainDestination = (saveCell.getConstructionFunctionComponent().getChainDestination());
            this.chainSender = (saveCell.getConstructionFunctionComponent().getChainSender());

            this.functionTranslator = new FunctionTranslator() {
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
                    return saveCell.getConstructionFunctionComponent();
                }

                @Override
                public FunctionComponent translateFunctionComponent() {
                    return saveCell.getConstructionFunctionComponent();
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
            };
            this.functionTranslator.translateFunctionComponent().deserialization(world);
        } else {
            if (saveCell.getFunctionComponent() != null) {
                this.chainSender = (saveCell.getFunctionComponent().getChainSender());
                this.chainDestination = (saveCell.getFunctionComponent().getChainDestination());

                this.functionTranslator = new FunctionTranslator() {
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
                        return null;
                    }

                    @Override
                    public FunctionComponent translateFunctionComponent() {
                        return saveCell.getFunctionComponent();
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
                };
            }
        }



        switch (saveCell.getTerrainModel())

        {
            case "Строение":
                this.setTerrainModel(world.getMapManager().getTerrainModelFabric().getBuildTerrain());

                break;
            case "Трава":
                this.setTerrainModel(world.getMapManager().getTerrainModelFabric().getGrassTerrain());
                break;
            case "Дерево":
                this.setTerrainModel(world.getMapManager().getTerrainModelFabric().getTreeTerrain());
                break;
        }

        if (saveCell.getBuildModel() != null)

        {
            this.setBuildModel(world.getBuildManager().getBuildModel(saveCell.getBuildModel()));
            this.setBuilding(saveCell.getBuildId(), buildModel);
        }

        if (saveCell.isHasConstruction())

        {
            this.construction = world.getBuildManager().getConstruction();
        }
        if ((functionTranslator != null) && (!saveCell.isHasConstruction())) {
            this.chainDestination = functionTranslator.translateFunctionComponent().getChainDestination();
            this.chainSender = functionTranslator.translateFunctionComponent().getChainSender();
            if ((this.functionTranslator != null) && (this.functionTranslator.translateFunctionComponent().cloneable())) {
                world.getResourceManager().addFunctionComponent(functionTranslator.translateFunctionComponent());
                this.functionTranslator.translateFunctionComponent().deserialization(world);
            } else {
                Log.i("X", x + "");
                Log.i("Y", y + "");
                this.setFunctionTranslator(world.getBuildManager().getBuildModel(this.getBuildModel().getName()).getFunctionTranslator());
            }

        }
        this.setiRoadFunction(saveCell.getiRoadFunction());
        if (this.iRoadFunction != null) {
            this.chainDestination = this.iRoadFunction;

        }
    }

    public void reloadInterfaces() {
        this.setChainDestination(this.chainDestination);
        this.setChainSender(this.chainSender);
    }

    public Cell() {

    }

    public void deleteFunctionComponent(FunctionComponent functionComponent, TerrainModelFabric terrainModelFabric) {
        if ((this.functionTranslator != null) && (functionTranslator.translateFunctionComponent().equals(functionComponent))) {
            this.chainDestination = null;
            this.chainSender = null;
            this.functionTranslator = null;
            this.iRoadFunction = null;
            this.buildModel = null;
            this.construction = null;
            this.terrainModel = terrainModelFabric.getTreeTerrain();
            this.texture = this.terrainModel.getTexture();
            this.buildId = -1;
        }
    }
}
