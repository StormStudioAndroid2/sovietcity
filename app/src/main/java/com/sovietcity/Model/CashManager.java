package com.sovietcity.Model;


import java.io.Serializable;

//    управляет деньгами
public class CashManager {
    private Cash cash;

    public CashManager(double number) {
        cash = new Cash(number);
    }

    public double getCash() {
        return cash.getAmount();
    }

    public void gainFee(PopulationManager populationManager) {
        //TODO(!) Write there resource for population.getAmount!!!
        cash.addCash((populationManager.getPopulation().getNowPopulation() * populationManager.getPopulation().getPopulationDataComponent().getTaxK()));
    }
    public void addCash(double number) {
        this.cash.addCash(number);
    }
    public boolean wasteMoney(double number) {
        return cash.wasteCash(number);
    }
    public Cash getSaveCash() {
        return this.cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }
}
