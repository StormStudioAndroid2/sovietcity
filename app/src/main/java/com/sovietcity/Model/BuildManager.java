package com.sovietcity.Model;

import android.content.Context;

import com.sovietcity.Presenter.World;

import java.io.Serializable;
import java.util.ArrayList;


//    менеджер строительства
public class BuildManager {
    private boolean build;
    private Observer observer;
    private Construction construction;
    private BuildModel nowBuildModel;
    private BuildModelPool buildModelPool;
    private MapManager mapManager;
    private FunctionComponent functionComponent;
    private FunctionComponentBuilder functionComponentBuilder;
    private TaskListener taskListener;
    private ArrayList<ConstructionFunctionComponent> constructionFunctionComponents;

    public BuildModelPool getBuildModelPool() {
        return this.buildModelPool;
    }

    public void setTaskListener(TaskListener taskListener) {
        this.taskListener = taskListener;
    }

    public BuildModel getBuildModel(String name) {
        if (name != null) {
            for (int i = 0; i < buildModelPool.getBuildModels().size(); i++) {
                if (name.equals(buildModelPool.getBuildModels().get(i).getName())) {
                    return buildModelPool.getBuildModels().get(i);
                }
            }
        }
        return null;
    }

    public BuildManager(World world, Context context) {
        this.functionComponentBuilder = new FunctionComponentBuilder(world.getResourceManager());
        this.construction = new Construction(context, world.getDrawerManager());
        this.constructionFunctionComponents = new ArrayList<>();
        this.buildModelPool = new BuildModelPool(world, context, this);

    }
    public void deleteConstruct(ConstructionFunctionComponent constructionFunctionComponent) {
        this.constructionFunctionComponents.remove(constructionFunctionComponent);
    }
    public void startBuildStock(World world, Context context) {

        final FunctionComponent stockFunctionComponent = buildModelPool.getStockBuildModel().getFunctionTranslator().translateStockFunctionComponent().clone();
        BuildModel buildModel = buildModelPool.getStockBuildModel();
        world.getResourceManager().addFunctionComponent(stockFunctionComponent);
        stockFunctionComponent.startFunction(buildModel);
        GameMap.getGameMapCell(GameMap.sizeY / 2, GameMap.sizeX / 2).setTerrainModel(world.getMapManager().getTerrainModelFabric().getBuildTerrain());
        GameMap.getGameMapCell(GameMap.sizeY / 2, GameMap.sizeX / 2).setBuilding(0, buildModel);
        GameMap.getGameMapCell(GameMap.sizeY / 2, GameMap.sizeX / 2).setChainDestination(stockFunctionComponent.getChainDestination());
        GameMap.getGameMapCell(GameMap.sizeY / 2, GameMap.sizeX / 2).setChainSender(stockFunctionComponent.getChainSender());
        GameMap.getGameMapCell(GameMap.sizeY / 2, GameMap.sizeX / 2).setFunctionTranslator(new FunctionTranslator() {
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
                return stockFunctionComponent;
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
        GameMap.getGameMapCell(GameMap.sizeY / 2, GameMap.sizeX / 2 + 1).setBuilding(1, buildModel);
        GameMap.getGameMapCell(GameMap.sizeY / 2, GameMap.sizeX / 2 + 1).setTerrainModel(world.getMapManager().getTerrainModelFabric().getBuildTerrain());
        GameMap.getGameMapCell(GameMap.sizeY / 2, GameMap.sizeX / 2 + 1).setChainDestination(stockFunctionComponent.getChainDestination());
        GameMap.getGameMapCell(GameMap.sizeY / 2, GameMap.sizeX / 2 + 1).setChainSender(stockFunctionComponent.getChainSender());
        GameMap.getGameMapCell(GameMap.sizeY / 2, GameMap.sizeX / 2 + 1).setFunctionTranslator(new FunctionTranslator() {
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
                return stockFunctionComponent;
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

    public synchronized boolean startBuild(BuildModel nowBuildModel, CashManager cashManager, int blocks, int stages) {
        boolean b;

        CustomBuildComponent customBuildComponent = (CustomBuildComponent) nowBuildModel.getBuildComponent();
        b = customBuildComponent.setBlocks(cashManager, blocks);
        if (b) b = customBuildComponent.setStages(stages);

        if (b) {

            this.nowBuildModel = nowBuildModel;
            this.build = b;
        }
        return b;
    }

    public synchronized boolean startBuild(BuildModel nowBuildModel, CashManager cashManager, int cells) {
        boolean b;

        ShemeBuildComponent shemeBuildComponent = (ShemeBuildComponent) nowBuildModel.getBuildComponent();
        b = shemeBuildComponent.setCells(cells);
        if (b) {
            this.nowBuildModel = nowBuildModel;
            this.build = b;
        }
        return b;
    }

    public synchronized boolean startBuild(BuildModel nowBuildModel, CashManager cashManager) {
        this.functionComponent = nowBuildModel.getFunctionComponent();
        SpecifiedBuildComponent specifiedBuildComponent = (SpecifiedBuildComponent) nowBuildModel.getBuildComponent();
        this.nowBuildModel = nowBuildModel;
        this.build = true;

        return true;
    }


    public synchronized boolean buildCell(World w, int y, int x, Context context) {
        boolean b = false;
        if ((build) && (GameMap.getGameMapCell(y, x).getTerrainModel().isBuildable())) {
            b = nowBuildModel.getBuildComponent().selectCell(mapManager, this, y, x, nowBuildModel);
            if (!build) {
                createTaskEvent(nowBuildModel);
                b = true;

            }
        }
        return b;
    }

    private void createTaskEvent(BuildModel buildModel) {
        TaskEvent taskEvent = new TaskEvent(this);
        taskEvent.setBuilding(buildModel.getName());
        taskListener.observeTask(taskEvent);
    }

    public void finishBuilding() {
        this.build = false;
    }

    public void setMapManager(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    public FunctionComponent getFunctionComponent() {
        return functionComponent;
    }

    public FunctionComponentBuilder getFunctionComponentBuilder() {
        return functionComponentBuilder;
    }

    public Construction getConstruction() {
        return construction;
    }

    public void addConstructorFunctionComponent(ConstructionFunctionComponent constructionFunctionComponent) {
        if (!constructionFunctionComponents.contains(constructionFunctionComponent)) {
            this.constructionFunctionComponents.add(constructionFunctionComponent);
        }
    }

    public void setConstructionFunctionTranslator(final ConstructionFunctionComponent constructionFunctionComponent, Cell cell) {
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

    public void manage() {
        if (constructionFunctionComponents.size() > 0) {
            for (int i = 0; i < constructionFunctionComponents.size(); i++) {
                constructionFunctionComponents.get(i).function();

                if (constructionFunctionComponents.get(i).isComplete()) {
                    delete(constructionFunctionComponents.get(i));
                    i--;
                    observer.needUpdate();
                }
            }
        }
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    private void delete(ConstructionFunctionComponent constructionFunctionComponent) {
        constructionFunctionComponents.remove(constructionFunctionComponent);
    }
}

