package com.sovietcity.Model;

import java.io.Serializable;

/**
 * Created by Серёга on 06.05.2016.
 */
public class TaskCreator implements Serializable {
    public Task createTask(String text, int year, int reward, int people, String building, String material1, int quantity1, String material2, int quantity2) {
        Task task = new Task();
        task.setText(text);
        task.setYear(year);
        task.setReward(reward);
        task.setNeedPeople(people);
        task.setNeedBuilding(building);
        task.setMaterial1(material1);
        task.setQuantity1(quantity1);
        task.setMaterial2(material2);
        task.setQuantity2(quantity2);
        return task;
    }

}
