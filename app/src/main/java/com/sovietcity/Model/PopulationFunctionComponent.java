package com.sovietcity.Model;

import com.sovietcity.Presenter.World;
import com.sovietcity.View.MainActivity;

import java.io.Serializable;

//функциональный компонент жилых зданий. Увеличивает максимальное население
class PopulationFunctionComponent extends FunctionComponent implements Serializable {
    private int blockPeople;
    private transient IPopulationIncrease iPopulationIncrease;
    private int id;
    private int blocks;

    @Override
    public void function() {

    }

    @Override
    public void startFunction(BuildModel buildModel) {
        PopulationEvent event = new PopulationEvent(iPopulationIncrease);
        int changePop;
        if (buildModel.isCustoming()) {
            changePop = blocks * blockPeople;
        } else {
            changePop = blockPeople;
        }
        event.setChangePopulation(changePop);
        iPopulationIncrease.increasePopulationEvent(event);
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
        this.iPopulationIncrease = world.getPopulationManager();
    }

    @Override
    public boolean cloneable() {
        return true;
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
        PopulationFunctionComponent populationFunctionComponent = new PopulationFunctionComponent();
        populationFunctionComponent.iPopulationIncrease = this.iPopulationIncrease;
        populationFunctionComponent.blockPeople = this.blockPeople;

        return populationFunctionComponent;
    }

    @Override
    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }


    public void setBlockPeople(int blockPeople) {
        this.blockPeople = blockPeople;
    }

    public void setiPopulationIncrease(IPopulationIncrease iPopulationIncrease) {
        this.iPopulationIncrease = iPopulationIncrease;
    }

    @Override
    public String toString() {
        return "Увеличение населения на "+Integer.toString(this.blockPeople)+" человек за блок";
    }
}
