package com.sovietcity.Model;

import android.util.Log;

import java.io.Serializable;
import java.util.Random;

//население
public class Population implements Serializable {
    private int maxPopulation;
    private PopulationGroup childrenGroup;
    private PopulationGroup adultGroup;
    private PopulationGroup pensionerGroup;
    private transient SpecialtyManager specialtyManager;
    private PopulationDataComponent populationDataComponent;

    public Population(final SpecialtyManager specialtyManager, int startPopulation) {
        this.populationDataComponent = new PopulationDataComponent();
        populationDataComponent.setTaxK(10.0);
        populationDataComponent.setKnowledgeK(0.4);
        populationDataComponent.setBaseChanceBeget(0.01);
        this.childrenGroup = new PopulationGroup(false); //реализация трех возр. групп и их параметров
        this.adultGroup = new PopulationGroup(true);
        this.pensionerGroup = new PopulationGroup(false);
        this.childrenGroup.setWealth(50);
        this.adultGroup.setWealth(50);
        this.pensionerGroup.setWealth(50);
        this.specialtyManager = specialtyManager;
        specialtyManager.distributeWorkers(startPopulation);
        setAdultGroupListener(adultGroup);
        setChildrenGroupListener(childrenGroup);
        setPensioneerGroupListener(pensionerGroup);
        this.adultGroup.increasePopulation(startPopulation);
        setNeedFoodQuantity();
        this.maxPopulation = startPopulation;
    }
    public Population() {

    }
    public void setSpecialtyManager(SpecialtyManager specialtyManager) {
        this.specialtyManager = specialtyManager;
    }

    public int getNowPopulation() {
        return this.childrenGroup.getPopulation() + this.adultGroup.getPopulation() + this.pensionerGroup.getPopulation();
    }

    public PopulationGroup getChildrenGroup() {
        return this.childrenGroup;
    }

    public PopulationGroup getAdultGroup() {
        return this.adultGroup;
    }

    public PopulationGroup getPensionerGroup() {
        return this.pensionerGroup;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public void increaseMaxPopulation(int number) {
        maxPopulation += number;
    }

    public void decreaseMaxPopulation(int number) {
        if (maxPopulation - number > 0) maxPopulation -= number;
        else maxPopulation = 0;
    }

    public double getNeedFoodQuantity() {
        return populationDataComponent.getNeedFoodQuantity();
    }

    public void manage() {
        boolean b = checkFood();
        childrenGroup.manage();
        adultGroup.manage();
        pensionerGroup.manage();
        setNeedFoodQuantity();
        if (b) {
            if (adultGroup.getWealth() < 50) {
                adultGroup.setWealth(adultGroup.getWealth() + 5);
                if (adultGroup.getWealth() > 50) adultGroup.setWealth(50);
            }
            if (pensionerGroup.getWealth() < 50) {
                pensionerGroup.setWealth(pensionerGroup.getWealth() + 5);
                if (pensionerGroup.getWealth() > 50) pensionerGroup.setWealth(50);
            }
        }

    }

    public void addFood(double food) {
        this.populationDataComponent.setNowFood(populationDataComponent.getNowFood() + food);
        ;
    }

    private boolean checkFood() {
        if (this.populationDataComponent.getNowFood() >= this.populationDataComponent.getNeedFoodQuantity()) {
            this.populationDataComponent.setNowFood(populationDataComponent.getNowFood() - populationDataComponent.getNeedFoodQuantity());
            ;
            return true;

        } else {
            this.adultGroup.setWealth((int) Math.round((populationDataComponent.getNowFood() + 1 / populationDataComponent.getNeedFoodQuantity()) * adultGroup.getWealth()));
            this.pensionerGroup.setWealth((int) Math.round((populationDataComponent.getNowFood() + 1 / populationDataComponent.getNeedFoodQuantity()) * pensionerGroup.getWealth()));
            populationDataComponent.setNowFood(0);

        }
        return false;
    }

    private void setNeedFoodQuantity() {
        this.populationDataComponent.setNeedFoodQuantity(Math.round(getNowPopulation() * 0.02));
    }

    private void setChildrenGroupListener(final PopulationGroup childrenGroup) {
        this.childrenGroup.setPopulationGroupGrower(new PopulationGroupGrower() {
            @Override
            public void grow() {
                if (maxPopulation > getNowPopulation()) {
                    Random random = new Random();

                    double r = random.nextDouble();
                    int incPeople = 0;
                    double chance = (populationDataComponent.getBaseChanceBeget() * adultGroup.getPopulation() / 400.0);
                    if (r < chance) {
                        incPeople = (int) ((0.51 * adultGroup.getPopulation() * populationDataComponent.getBaseChanceBeget() * 0.8) / 12.0 - r) * getNowPopulation();  //вероятность рождения ребенка
                        Log.i("BEGET", " " + chance);
                    }
                    childrenGroup.increasePopulation(incPeople);
                }
            }
        });
    }

    private void setAdultGroupListener(final PopulationGroup adultGroup) {
        adultGroup.setPopulationGroupGrower(new PopulationGroupGrower() {
            @Override
            public void grow() {
                int allPeople = 0;
                int peopleMigrate = 0;
                int peopleBirthday = 0;
                if (maxPopulation > getNowPopulation()) {
                    peopleMigrate = migration();
                    adultGroup.increasePopulation(peopleMigrate);
                }
                peopleBirthday = growChildren();
                adultGroup.increasePopulation(peopleBirthday);

                allPeople = peopleBirthday + peopleMigrate;
                specialtyManager.distributeWorkers(allPeople);
            }

            private int migration() {
                if ((maxPopulation > getNowPopulation()) && (specialtyManager.getIContainerPlaces() > adultGroup.getPopulation())) {
                    Random random = new Random();
                    double rand = random.nextDouble();
                    double chance = (double) ((specialtyManager.getIContainerPlaces() - specialtyManager.getBusyWorkplaces()+1)*1.0 / adultGroup.getPopulation());
                    Log.i("MIGRATION", " " + chance);
                    if (rand <= chance) {
                        int incpopulation = random.nextInt((maxPopulation - adultGroup.getPopulation()) / 10 + 10);
                        return incpopulation;
                    }
                }
                return 0;
            }

            private int growChildren() {
                int c = childrenGroup.getPopulation() / 12 / 18;
                childrenGroup.decreasePopulation(c);
                return c;
            }
        });
    }

    private void setPensioneerGroupListener(final PopulationGroup pensionerGroup) {
        pensionerGroup.setPopulationGroupGrower(new PopulationGroupGrower() {
            @Override
            public void grow() {
                pensionerGroup.decreasePopulation(death());
                int people = (int) (adultGroup.getPopulation() * 0.001 * populationDataComponent.getMedicineK());
                pensionerGroup.increasePopulation(people);
                adultGroup.decreasePopulation(people);

            }

            public int death() {
                return (int) (pensionerGroup.getPopulation() / 120 * populationDataComponent.getMedicineK());
            }

        });
    }

    public double getNowFood() {
        return populationDataComponent.getNowFood();
    }

    public PopulationDataComponent getPopulationDataComponent() {
        return this.populationDataComponent;
    }
}