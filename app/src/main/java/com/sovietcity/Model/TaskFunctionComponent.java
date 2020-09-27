package com.sovietcity.Model;

import com.sovietcity.Presenter.World;
import com.sovietcity.View.MainActivity;

import java.io.Serializable;

//функциональный компонент горкома
public class TaskFunctionComponent extends FunctionComponent implements Serializable {
    private int id;
    private PopulationDataComponent populationDataComponent;

    @Override
    public void function() {

    }

    @Override
    public void startFunction(BuildModel buildModel) {
        populationDataComponent.setTaxK(populationDataComponent.getTaxK() * 1.05);
    }

    @Override
    public void about(MainActivity mainActivity, int y, int x) {
    }

    @Override
    public IChainDestination getChainDestination() {
        return null;
    }

    @Override
    public IChainSender getChainSender() {
        return null;
    }

    @Override
    public void deserialization(World world) {

    }

    @Override
    public boolean cloneable() {
        return false;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public FunctionComponent clone() {
        return this;
    }

    @Override
    public void setBlocks(int blocks) {

    }

    @Override
    public String toString() {
        return "Увеличение дохода от налогов на 5%";
    }

    public void setPopulationDataComponent(PopulationDataComponent populationDataComponent) {
        this.populationDataComponent = populationDataComponent;
    }
}
