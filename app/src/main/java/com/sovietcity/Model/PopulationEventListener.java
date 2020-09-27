package com.sovietcity.Model;

import java.io.Serializable;

//приемщик ивента
public interface PopulationEventListener extends Serializable {
    public void changePopulation(PopulationEvent e,boolean isIncrease);
}
