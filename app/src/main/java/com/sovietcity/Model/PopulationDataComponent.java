package com.sovietcity.Model;

import java.io.Serializable;

//компонент, хранящий подробную инфу о населении
public class PopulationDataComponent  implements Serializable {
    private double needFoodQuantity;
    private double nowFood;
    private double powerK;
    private double knowledgeK;
    private double medicineK;
    private double baseChanceBeget;
    private double taxK;


    public double getNowFood() {
        return nowFood;
    }

    public double getKnowledgeK() {
        return knowledgeK;
    }

    public double getNeedFoodQuantity() {
        return needFoodQuantity;
    }

    public double getPowerK() {
        return powerK;
    }

    public void setKnowledgeK(double knowledgeK) {
        this.knowledgeK = knowledgeK;
        this.powerK = this.knowledgeK * 2;
    }

    public void setNeedFoodQuantity(double needFoodQuantity) {
        this.needFoodQuantity = needFoodQuantity;
    }

    public void setNowFood(double nowFood) {
        this.nowFood = nowFood;
    }

    public void setPowerK(double powerK) {
        this.powerK = powerK;
    }

    public double getMedicineK() {
        return medicineK;
    }

    public void setMedicineK(double medicineK) {
        this.medicineK = medicineK;
    }

    public void setBaseChanceBeget(double baseChanceBeget) {
        this.baseChanceBeget = baseChanceBeget;
    }

    public double getBaseChanceBeget() {
        return baseChanceBeget;
    }

    public double getTaxK() {
        return taxK;
    }
    public void setTaxK(double taxK) {
        this.taxK = taxK;
    }


}
