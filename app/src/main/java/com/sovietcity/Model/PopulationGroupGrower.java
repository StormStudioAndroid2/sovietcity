package com.sovietcity.Model;

import java.io.Serializable;

//управление ростом возрастной группы. Перегружается в классе Population у каждой из групп
public interface PopulationGroupGrower extends Serializable {
    public void grow();

}
