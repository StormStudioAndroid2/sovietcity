package com.sovietcity.Model;

import android.util.Log;

import com.sovietcity.Presenter.World;
import com.sovietcity.View.MainActivity;

import java.io.Serializable;
import java.util.ArrayList;


//    функциональный компонент стройки
public class ConstructionFunctionComponent extends FunctionComponent implements IChainDestination, Serializable {
    private transient BuildModel buildModel;
    private String buildModelName;
    private transient ArrayList<Cell> cells;
    private int months;
    private Material firstMaterial;
    private Material secondMaterial;
    private int needQuantity1;
    private int needQuantity2;
    private boolean isComplete;
    private int blocks;
    private transient FunctionComponentBuilder functionComponentBuilder;
    private transient FunctionTranslator functionTranslator;

    @Override
    public void function() {
        if ((this.cells.size() != 0) && (months == 0) && (checkFirstMaterial() && (checkSecondMaterial())) && (!isComplete)) {
            FunctionComponent functionComponent = functionComponentBuilder.setFunctionComponentOnCell(functionTranslator, cells);
            deleteConstruction();
            functionComponent.startFunction(buildModel);

            isComplete = true;
        } else {
            if (months > 0) {
                months--;
            }
        }
    }

    @Override
    public void startFunction(BuildModel buildModel) {
        isComplete = false;
        this.buildModel = buildModel;
        this.buildModelName = buildModel.getName();
        final FunctionComponent functionComponent = buildModel.getBuildFunctionComponent();
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
                return functionComponent;
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

    public void setFunctionTranslator(FunctionTranslator functionTranslator) {

        this.functionTranslator = functionTranslator;
    }

    public void addCell(Cell cell) {
        if (cells == null) {
            this.cells = new ArrayList<>();
        }
        this.cells.add(cell);
    }

    @Override
    public void about(MainActivity mainActivity, int y, int x) {
        mainActivity.constructWindowFragmentTransaction(this, GameMap.getGameMapCell(y, x).getBuildModel());
    }

    @Override
    public IChainDestination getChainDestination() {
        return this;
    }

    @Override
    public IChainSender getChainSender() {
        return null;
    }

    @Override
    public void deserialization(final World world) {

        this.functionComponentBuilder = world.getBuildManager().getFunctionComponentBuilder();
        final FunctionComponent functionComponent = world.getBuildManager().getBuildModel(buildModelName).getBuildFunctionComponent();
        ;
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
                return functionComponent;
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
        if (!this.functionTranslator.translateFunctionComponent().cloneable()) {
            this.functionTranslator = world.getBuildManager().getBuildModel(buildModelName).getFunctionTranslator();
        } else {
            functionTranslator.translateFunctionComponent().deserialization(world);

        }

        if (firstMaterial != null) {
            firstMaterial.setResource(world.getResourceManager().getResource(firstMaterial.getResource().getName()));
        }
        if (secondMaterial != null) {
            secondMaterial.setResource(world.getResourceManager().getResource(secondMaterial.getResource().getName()));
        }
        this.buildModel = world.getBuildManager().getBuildModel(buildModelName);
        functionTranslator.translateFunctionComponent().setBlocks(this.blocks);
        world.getBuildManager().addConstructorFunctionComponent(this);
    }

    @Override
    public boolean cloneable() {
        return true;
    }

    @Override
    public void setId(int id) {

    }


    @Override
    public int getId() {
        return 0;
    }


    public boolean addMaterial(Material material) {
        if ((firstMaterial != null) && (!firstMaterial.addMaterial(material))) {
            if ((secondMaterial != null) && (!secondMaterial.addMaterial(material))) {
                return false;

            }
        }
        if ((checkFirstMaterial()) && (checkSecondMaterial()) && (months == 0) && (this.cells.size() != 0)) {
            function();
        }
        return true;
    }

    @Override
    public void addDelivery(Delivery delivery) {
        delivery.setFree(true);
        Material material = new Material();
        material.setResource(delivery.getResource());
        material.setQuantity(delivery.getQuantity());
        Log.i("INFO_ADD","adding");
        boolean b = this.addMaterial(material);

    }

    @Override
    public boolean canAddDelivery(Delivery delivery) {
        if (((this.firstMaterial != null) && (firstMaterial.getResource().equals(delivery.getResource()))) || ((this.secondMaterial != null) && (this.secondMaterial.getResource().equals(delivery.getResource())))) {
            Log.i("INFO_ADD","can");

            return true;
        }
        Log.i("INFO_ADD","cant");

        return false;
    }

    @Override
    public int getDayDuration() {
        return 3;
    }

    @Override
    public int getDeliveryPrice() {
        return 200;
    }

    public ConstructionFunctionComponent() {
        this.cells = new ArrayList<>();
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public void setNeedQuantity1(int needQuantity1) {
        this.needQuantity1 = needQuantity1;
    }

    public void setNeedQuantity2(int needQuantity2) {
        this.needQuantity2 = needQuantity2;
    }

    public void setSecondMaterial(Material secondMaterial) {
        this.secondMaterial = secondMaterial;
    }

    public void setFirstMaterial(Material firstMaterial) {
        this.firstMaterial = firstMaterial;
    }

    @Override
    public ConstructionFunctionComponent clone() {
        ConstructionFunctionComponent constructionFunctionComponent = new ConstructionFunctionComponent();
        constructionFunctionComponent.months = this.months;
        if (this.firstMaterial != null) {
            constructionFunctionComponent.firstMaterial = new Material(this.firstMaterial);
        }
        if (this.secondMaterial != null) {
            constructionFunctionComponent.secondMaterial = new Material(this.secondMaterial);
        }
        constructionFunctionComponent.needQuantity1 = this.needQuantity1;
        constructionFunctionComponent.needQuantity2 = this.needQuantity2;
        constructionFunctionComponent.functionComponentBuilder = this.functionComponentBuilder;
        constructionFunctionComponent.cells = new ArrayList<>();
        constructionFunctionComponent.isComplete = false;
        return constructionFunctionComponent;
    }

    @Override
    public void setBlocks(int blocks) {
        if (functionTranslator != null) {
            this.blocks = blocks;
            functionTranslator.translateFunctionComponent().setBlocks(blocks);
        }
    }

    public void setFunctionComponentBuilder(FunctionComponentBuilder functionComponentBuilder) {
        this.functionComponentBuilder = functionComponentBuilder;
    }

    public void deleteConstruction() {
        for (int i = 0; i < cells.size(); i++) {
            cells.get(i).setConstruction(null);
        }
    }

    public boolean isComplete() {
        return isComplete;
    }

    public boolean checkFirstMaterial() {
        if ((firstMaterial == null) || (firstMaterial.getQuantity() >= needQuantity1)) {
            return true;
        }
        return false;
    }

    public boolean checkSecondMaterial() {
        if ((secondMaterial == null) || (secondMaterial.getQuantity() >= needQuantity2)) {
            return true;
        }
        return false;
    }

    public int getMonths() {
        return months;
    }

    public int getNeedQuantity1() {
        return needQuantity1;
    }

    public int getNeedQuantity2() {
        return needQuantity2;
    }

    public Material getFirstMaterial() {
        return firstMaterial;
    }

    public Material getSecondMaterial() {
        return secondMaterial;
    }

}
