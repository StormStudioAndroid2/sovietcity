package com.sovietcity.Model;

import com.sovietcity.Presenter.World;
import com.sovietcity.View.MainActivity;

import java.io.Serializable;

// функциональный компонент больницы
public class HospitalFunctionComponent extends FunctionComponent implements Serializable {
    private transient CashManager cashManager;
    private transient PopulationDataComponent populationDataComponent;
    private double factor;
    private int id;

    @Override
    public void function() {
        this.cashManager.wasteMoney(500);
    }

    @Override
    public void startFunction(BuildModel buildModel) {
        populationDataComponent.setMedicineK(populationDataComponent.getMedicineK() + factor);
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
        HospitalFunctionComponent hospitalFunctionComponent = new HospitalFunctionComponent();
        hospitalFunctionComponent.setCashManager(cashManager);
        hospitalFunctionComponent.setFactor(factor);
        hospitalFunctionComponent.setPopulationDataComponent(populationDataComponent);
        return hospitalFunctionComponent;
    }

    @Override
    public void setBlocks(int blocks) {

    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public void setPopulationDataComponent(PopulationDataComponent populationDataComponent) {
        this.populationDataComponent = populationDataComponent;
    }

    public void setCashManager(CashManager cashManager) {
        this.cashManager = cashManager;
    }

    @Override
    public String toString() {
        return "Увеличение уровня здоровья населения";
    }
}
