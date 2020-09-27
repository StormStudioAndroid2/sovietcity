package com.sovietcity.Model;

import android.graphics.Bitmap;

import java.io.Serializable;


public class SaveCell implements Serializable {
    //    private boolean focused;
    private int x;
    private int y;
    private String terrainModel;
    private String buildModel;
    private String resource;
    private IRoadFunction iRoadFunction;
    private FunctionComponent functionComponent;
    private ConstructionFunctionComponent constructionFunctionComponent;
    private boolean hasConstruction;
    private int buildId;

    public SaveCell(Cell cell) {
        this.x = cell.getX();
        this.y = cell.getY();
        this.terrainModel = cell.getTerrainModel().getName();
        if (cell.getBuildModel() != null) {
            this.buildModel = cell.getBuildModel().getName();
        }

        if (cell.getConstruction() != null) {
            this.hasConstruction = true;
        }
        if (cell.hasResource()) {
            this.resource = cell.getResource().getName();
        }

        this.iRoadFunction = cell.getiRoadFunction();
        if (cell.getFunctionTranslator() != null) {
            this.functionComponent = cell.getFunctionTranslator().translateFunctionComponent();
            this.constructionFunctionComponent = cell.getFunctionTranslator().translateConstructionFunctionComponent();
            this.buildId = cell.getBuildId();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int getBuildId() {
        return buildId;
    }

    public String getBuildModel() {
        return buildModel;
    }

    public String getResource() {
        return resource;
    }

    public boolean isHasConstruction() {
        return hasConstruction;
    }

    public String getTerrainModel() {
        return terrainModel;
    }

    public FunctionComponent getFunctionComponent() {
        return functionComponent;
    }

    public ConstructionFunctionComponent getConstructionFunctionComponent() {
        return constructionFunctionComponent;
    }

    public IRoadFunction getiRoadFunction() {
        return iRoadFunction;
    }


}
