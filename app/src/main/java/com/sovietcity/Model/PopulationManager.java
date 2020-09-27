package com.sovietcity.Model;

//управление населением
import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

public class PopulationManager implements IPopulationIncrease, Serializable {
    private ArrayList<PopulationEventListener> listeners;
    private Population population;
    private TaskListener taskListener;

    public void setTaskListener(TaskListener taskListener) {
        this.taskListener = taskListener;
    }

    public PopulationManager(SpecialtyManager specialtyManager, int amount) {
        population = new Population(specialtyManager, amount);
        this.listeners = new ArrayList<>();
    }

    public PopulationManager(SpecialtyManager specialtyManager, Population population) {
        population.setSpecialtyManager(specialtyManager);
        this.listeners = new ArrayList<>();
        this.population = population;

    }

    public void increaseMaxPopulation(int populationIncrease) {
        population.increaseMaxPopulation(populationIncrease);
    }

    public void decreaseMaxPopulation(int populationDecrease) {
        population.decreaseMaxPopulation(populationDecrease);

    }

    public void manage() {
        population.manage();
        TaskEvent taskEvent = new TaskEvent(this);
        taskEvent.setPeople(getPopulation().getNowPopulation());
        this.taskListener.observeTask(taskEvent);
    }

    public Population getPopulation() {
        return this.population;
    }

    public void update(int populationIncrease) {
        this.increaseMaxPopulation(populationIncrease);
    }

    public void addPopulationEventListener(PopulationEventListener populationEventListener) {
        this.listeners.add(populationEventListener);
    }

    public void removePopulationEventListener(PopulationEventListener populationEventListener) {
        this.listeners.remove(populationEventListener);
    }

    public PopulationEventListener[] getPopulationEventListeners() {
        return listeners.toArray(new PopulationEventListener[listeners.size()]);
    }
    @Override
    public void increasePopulationEvent(PopulationEvent event) {
        for (PopulationEventListener listener : listeners) {
            listener.changePopulation(event, true);
        }
    }

    public void setPopulation(Population population) {
        this.population = population;
    }
}