package com.sovietcity.Model;

import java.io.Serializable;

//возрастная группа
public class PopulationGroup implements Serializable {
    private int population;
    private boolean canWork;
    private int wealth;
    private PopulationGroupGrower populationGroupGrower;

    public int getPopulation() {
        return this.population;
    }

    public int getWealth() {
        return this.wealth;
    }

    public PopulationGroup(boolean canWork) {
        this.canWork = canWork;
    }

    public void setPopulationGroupGrower(PopulationGroupGrower populationGroupGrower1) {
        this.populationGroupGrower = populationGroupGrower1;
    }

    public boolean setWealth(int wealth) {
        if ((wealth > 0) && (wealth < 100)) {
            this.wealth = wealth;
            return true;
        } else return false;
    }

    public void increasePopulation(int population) {
        this.population += population;
    }

    public void decreasePopulation(int population) {
        this.population -= population;
        if (this.population < 0) {
            this.population = 0;
        }
    }

    public boolean manage() {
        if (this.populationGroupGrower != null) {
            this.populationGroupGrower.grow();
            return true;
        }
        return false;
    }
}
