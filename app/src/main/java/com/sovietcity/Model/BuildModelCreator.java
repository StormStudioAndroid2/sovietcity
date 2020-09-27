package com.sovietcity.Model;

import android.content.Context;

import com.sovietcity.Presenter.DrawerManager;

import java.io.Serializable;

//    содержит методы, создающие здания с разными конфигурациями
public class BuildModelCreator {
    public BuildModel createBuildModel(String name, String describe, int iconId, Context context, int id, boolean customing, int blockPrice, DrawerManager drawerManager) {
        CustomBuildComponent customBuildComponent = new CustomBuildComponent();
        BuildModel buildModel = new BuildModel(context, id, customing, drawerManager);
        buildModel.setName(name);
        buildModel.setDescribe(describe);

        buildModel.setIcon(iconId);
        buildModel.setBuildComponent(customBuildComponent);
        buildModel.setBlockPrice(blockPrice);
        return buildModel;

    }

    public BuildModel createBuildModel(String name, String describe, int iconId, Context context, int id, boolean customing, int blockPrice, int numby, int numbx, DrawerManager drawerManager) {
        SpecifiedBuildComponent specifiedBuildComponend = new SpecifiedBuildComponent();
        specifiedBuildComponend.setBlocks(numby, numbx);
        BuildModel buildModel = new BuildModel(context, id, customing, drawerManager);
        buildModel.setName(name);
        buildModel.setDescribe(describe);
        buildModel.setIcon(iconId);

        buildModel.setBuildComponent(specifiedBuildComponend);
        buildModel.setBlockPrice(blockPrice);
        return buildModel;
    }

    public BuildModel createBuildModel(String name, String describe, int iconId, Context context, int id, boolean customing, int blockPrice, int numby, int numbx, Resource needResource, DrawerManager drawerManager) {
        SpecifiedBuildComponent specifiedBuildComponend = new SpecifiedBuildComponent();
        specifiedBuildComponend.setNeedResource(needResource);
        specifiedBuildComponend.setBlocks(numby, numbx);

        BuildModel buildModel = new BuildModel(context, id, customing, drawerManager);
        buildModel.setName(name);
        buildModel.setDescribe(describe);
        buildModel.setIcon(iconId);

        buildModel.setBuildComponent(specifiedBuildComponend);
        buildModel.setBlockPrice(blockPrice);
        return buildModel;
    }

    public BuildModel createRoadBuildModel(String name, String describe, int iconId, Context context, int id, int cellprice, int moveDay, int moveCost, DrawerManager drawerManager) {
        BuildModel buildModel = new BuildModel(context, id, true, drawerManager);
        buildModel.setName(name);
        buildModel.setDescribe(describe);
        buildModel.setIcon(iconId);

        RoadFunctionComponent roadFunctionComponent = new RoadFunctionComponent();
        roadFunctionComponent.setMoveCost(moveCost);
        roadFunctionComponent.setMoveDay(moveDay);
        buildModel.setRoadFunctionComponent(roadFunctionComponent);
        ShemeBuildComponent shemeBuildComponent = new ShemeBuildComponent();
        buildModel.setBuildComponent(shemeBuildComponent);
        buildModel.setBlockPrice(cellprice);
        buildModel.setIsRoad(true);
        return buildModel;
    }

    public BuildModel createStockBuildModel(String name, String describe, int iconId, Context context, int id, boolean customing, int blockPrice, int numby, int numbx, DrawerManager drawerManager) {
        BuildModel buildModel = new BuildModel(context, id, customing, drawerManager);
        buildModel.setName(name);
        buildModel.setDescribe(describe);
        buildModel.setIcon(iconId);
        SpecifiedBuildComponent specifiedBuildComponent = new SpecifiedBuildComponent();
        specifiedBuildComponent.setBlocks(numby, numbx);
        specifiedBuildComponent.setBlockPrice(blockPrice);
        buildModel.setBuildComponent(specifiedBuildComponent);
        return buildModel;
    }
}
