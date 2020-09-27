package com.sovietcity.Model;

import java.io.Serializable;
import java.util.EventObject;

//ивент, хранящий разницу населения
public class PopulationEvent extends EventObject implements Serializable {
    private int changePopulation;
    /**
     * Constructs a new instance of this class.
     *
     * @param source the object which fired the event.
     */
    public PopulationEvent(Object source) {
        super(source);

    }
    public void setChangePopulation(int changePopulation) {
        this.changePopulation = changePopulation;
    }
    public int getChangePopulation() {
        return this.changePopulation;
    }
}
