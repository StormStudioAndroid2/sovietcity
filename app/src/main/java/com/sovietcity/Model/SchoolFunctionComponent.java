package com.sovietcity.Model;

import com.sovietcity.Presenter.World;
import com.sovietcity.View.MainActivity;

import java.io.Serializable;

//функциональный компонент школы
public class SchoolFunctionComponent extends FunctionComponent implements Serializable {

    private transient PopulationDataComponent populationDataComponent;
    private transient CashManager cashManager;
    private double factor;
    private int id;

    @Override
    public void function() {
        cashManager.wasteMoney(500);
    }

    @Override
    public void startFunction(BuildModel buildModel) {
        this.populationDataComponent.setKnowledgeK(this.populationDataComponent.getKnowledgeK() + factor);
    }

    @Override
    public void about(MainActivity mainActivity, int y, int x) {

    }

    public void setCashManager(CashManager cashManager) {
        this.cashManager = cashManager;
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
        this.cashManager = world.getCashManager();
        this.populationDataComponent = world.getPopulationManager().getPopulation().getPopulationDataComponent();

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
        SchoolFunctionComponent schoolFunctionComponent = new SchoolFunctionComponent();
        schoolFunctionComponent.setPopulationDataComponent(populationDataComponent);
        schoolFunctionComponent.setFactor(factor);
        schoolFunctionComponent.setCashManager(cashManager);
        return schoolFunctionComponent;
    }

    @Override
    public void setBlocks(int blocks) {

    }

    public void setPopulationDataComponent(PopulationDataComponent populationDataComponent) {
        this.populationDataComponent = populationDataComponent;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    @Override
    public String toString() {
        return "Увеличение образованности населения";
    }
}
