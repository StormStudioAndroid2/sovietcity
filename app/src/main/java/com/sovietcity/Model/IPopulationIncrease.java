package com.sovietcity.Model;

import java.io.Serializable;

// увеличение населения
public interface IPopulationIncrease extends Serializable {
    void increasePopulationEvent(PopulationEvent event);
}
