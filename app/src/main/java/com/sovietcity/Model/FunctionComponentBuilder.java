package com.sovietcity.Model;

import java.io.Serializable;
import java.util.ArrayList;

// строительство функциональных компонентов зданий
public class FunctionComponentBuilder implements Serializable {
    private ResourceManager resourceManager;
    private int id;

    public FunctionComponentBuilder(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        this.id = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public FunctionComponent setFunctionComponentOnCell(FunctionTranslator functionComponentOnCell, ArrayList<Cell> cells) {

        for (int i = 0; i < cells.size(); i++) {
            setFunctionComponentOnCell(functionComponentOnCell, functionComponentOnCell.translateFunctionComponent().getChainDestination(), functionComponentOnCell.translateFunctionComponent().getChainSender(), cells.get(i));

        }
        FunctionComponent functionComponent = functionComponentOnCell.translateFunctionComponent();
        if (functionComponent.cloneable()) {
            resourceManager.addFunctionComponent(functionComponent);
            functionComponent.setId(id);
            id++;
        }
        return functionComponent;
    }

    private FunctionComponent setFunctionComponentOnCell(FunctionTranslator functionTranslator, IChainDestination iChainDestination, IChainSender iChainSender, Cell cell) {
        cell.setChainDestination(iChainDestination);
        cell.setChainSender(iChainSender);
        cell.setFunctionTranslator(functionTranslator);

        return functionTranslator.translateFunctionComponent();
    }

}
