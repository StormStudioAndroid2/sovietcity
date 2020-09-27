package com.sovietcity.Model;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sovietcity.Presenter.DrawerManager;

import java.io.Serializable;

//    контейнер для хранения всей инфы о зданиях
public class BuildModel {
    private String name;
    private Bitmap texture[];
    private BuildComponent buildComponent;
    private int blockPrice;
    private boolean customing;
    private boolean isFactory;
    private boolean isRoad;
    private IRoadFunction iRoadFunction;
    private IChainSender iChainSender;
    private IChainDestination iChainDestination;
    private FunctionComponent functionComponent;
    private FunctionTranslator functionTranslator;
    private ConstructionFunctionComponent constructionFunctionComponent;
    private int icon;
    private String describe;
    private int months;
    private FunctionBuildHelper functionBuildHelper;

    public BuildModel(Context context, int id, boolean customing, DrawerManager drawerManager) {
        this.customing = customing;
        this.functionBuildHelper = new FunctionBuildHelper();
        TypedArray imgs = context.getResources().obtainTypedArray(id);
        texture = new Bitmap[imgs.length()];
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imgs.getResourceId(i, -1));
            bitmap = drawerManager.setSize(bitmap);
            texture[i] = bitmap;
        }

    }

    public Bitmap getTexture(int id) {
        return this.texture[id];
    }

    public boolean setBuildComponent(BuildComponent buildComponent) {
        this.buildComponent = buildComponent;
        return true;
    }

    public boolean isCustoming() {
        return this.customing;
    }

    public boolean setBlockPrice(int blockPrice) {
        this.buildComponent.setBlockPrice(blockPrice);
        return true;
    }

    public void setIsRoad(boolean isRoad) {
        this.isRoad = isRoad;
    }

    public boolean isRoad() {
        return isRoad;
    }

    public BuildComponent getBuildComponent() {
        return this.buildComponent;
    }

    //    содержит set'ы всех возможных функциональных компонентов. Позже будет перенесено в отдельный класс
    public void setPopulationFunctionComponent(int blockPeople, IPopulationIncrease iPopulationIncrease) {
        functionBuildHelper.setPopulationFunctionComponent(this, blockPeople, iPopulationIncrease);
    }

    public void setFactoryFunctionComponent(PopulationDataComponent populationDataComponent, Specialty specialty, DeliveryCreator deliveryCreator, int workspaces, int production, Resource resource, Resource material, int quantity, int wasteResource) {
        functionBuildHelper.setFactoryFunctionComponent(this, populationDataComponent, specialty, deliveryCreator, workspaces, production, resource, material, quantity, wasteResource);
    }

    public void setFactoryFunctionComponent(PopulationDataComponent populationDataComponent, Specialty specialty, DeliveryCreator deliveryCreator, int workspaces, int production, Resource resource, Resource material, int quantity, int wasteResource, Resource material2, int quantity2, int wasteResource2) {
        functionBuildHelper.setFactoryFunctionComponent(this, populationDataComponent, specialty, deliveryCreator, workspaces, production, resource, material, quantity, wasteResource, material2, quantity2, wasteResource2);

    }

    public void setFactoryFunctionComponent(PopulationDataComponent populationDataComponent, Specialty specialty, DeliveryCreator deliveryCreator, int workspaces, int production, Resource resource) {
        functionBuildHelper.setFactoryFunctionComponent(this, populationDataComponent, specialty, deliveryCreator, workspaces, production, resource);

    }

    public FunctionComponent getFunctionComponent() {
        return this.functionComponent;
    }

    public void setFunctionComponent(FunctionComponent functionComponent) {
        this.functionComponent = functionComponent;
    }

    public IChainSender getiChainSender() {
        return iChainSender;
    }

    public IChainDestination getiChainDestination() {
        return iChainDestination;
    }

    public boolean isFactory() {
        return this.isFactory;
    }

    public void setIsFactory(boolean isFactory) {
        this.isFactory = isFactory;
    }

    public void setRoadFunctionComponent(int moveCost, int moveDay) {
        functionBuildHelper.setRoadFunctionComponent(this, moveCost, moveDay);

    }

    public IRoadFunction getiRoadFunction() {
        return iRoadFunction;
    }

    public void setiRoadFunction(IRoadFunction iRoadFunction) {
        this.iRoadFunction = iRoadFunction;
    }

    public void setRoadFunctionComponent(final RoadFunctionComponent r) {
        functionBuildHelper.setRoadFunctionComponent(this, r);
    }

    public void setStockFunctionComponent(Specialty specialty, DeliveryCreator deliveryCreator, CashManager cashManager) {
        functionBuildHelper.setStockFunctionComponent(this, specialty, deliveryCreator, cashManager);
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setFunctionTranslator(FunctionTranslator functionTranslator) {
        this.functionTranslator = functionTranslator;
    }

    public FunctionTranslator getFunctionTranslator() {
        return functionTranslator;
    }

    public void setiChainDestination(IChainDestination iChainDestination) {
        this.iChainDestination = iChainDestination;
    }

    public void setFactory(boolean factory) {
        isFactory = factory;
    }

    public void setiChainSender(IChainSender iChainSender) {
        this.iChainSender = iChainSender;
    }

    public void setCustoming(boolean customing) {
        this.customing = customing;
    }

    public void setConstructionFunctionComponent(ConstructionFunctionComponent constructionFunctionComponent) {
        this.constructionFunctionComponent = constructionFunctionComponent;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public void setRoad(boolean road) {
        isRoad = road;
    }

    public void setTexture(Bitmap[] texture) {
        this.texture = texture;
    }

    public void setTaskFunctionComponent(PopulationDataComponent populationDataComponent) {
        functionBuildHelper.setTaskFunctionComponent(this,populationDataComponent);
    }

    public void setConstructionFunctionComponent(FunctionComponentBuilder functionComponentBuilder, int months, Material material1, int quantity1) {
        ConstructionFunctionComponent constructionFunctionComponent = new ConstructionFunctionComponent();
        constructionFunctionComponent.setFunctionComponentBuilder(functionComponentBuilder);
        constructionFunctionComponent.setFirstMaterial(material1);
        constructionFunctionComponent.setNeedQuantity1(quantity1);
        constructionFunctionComponent.setMonths(months);
        this.constructionFunctionComponent = constructionFunctionComponent;
    }

    public void setConstructionFunctionComponent(FunctionComponentBuilder functionComponentBuilder, int months, Material material1, int quantity1, Material material2, int quantity2) {
        ConstructionFunctionComponent constructionFunctionComponent = new ConstructionFunctionComponent();
        constructionFunctionComponent.setFunctionComponentBuilder(functionComponentBuilder);
        constructionFunctionComponent.setFirstMaterial(material1);
        constructionFunctionComponent.setNeedQuantity1(quantity1);
        constructionFunctionComponent.setMonths(months);
        constructionFunctionComponent.setSecondMaterial(material2);
        constructionFunctionComponent.setNeedQuantity2(quantity2);
        this.constructionFunctionComponent = constructionFunctionComponent;
    }

    public ConstructionFunctionComponent getConstructionFunctionComponent() {
        return constructionFunctionComponent;
    }

    public ConstructionFunctionComponent cloneConstructionFunctionComponent() {
        return functionBuildHelper.cloneConstructionFunctionComponent(this);
    }

    public FunctionComponent getBuildFunctionComponent() {
       return functionBuildHelper.getBuildFunctionComponent(this);
    }

    public void setShopFunctionComponent(Specialty specialty, PopulationManager populationManager, Material material) {
        functionBuildHelper.setShopFunctionComponent(this, specialty, populationManager, material);
    }

    public void setSchoolFunctionComponent(PopulationDataComponent populationDataComponent, CashManager cashManager, double factor) {
        functionBuildHelper.setSchoolFunctionComponent(this, populationDataComponent, cashManager, factor);
    }

    public void setHospitalFunctionComponent(PopulationDataComponent populationDataComponent, CashManager cashManager, double factor) {
        functionBuildHelper.setHospitalFunctionComponent(this, populationDataComponent, cashManager, factor);
    }
    public String getPrice() {
        return "Цена за клетку: "+this.buildComponent.getBlockPrice()+" р.";
    }
}
