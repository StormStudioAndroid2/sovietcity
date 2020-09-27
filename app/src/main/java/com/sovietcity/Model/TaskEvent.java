package com.sovietcity.Model;

import java.io.Serializable;
import java.util.EventObject;

//ивент, сообщающий о прогрессе выполнения задания
public class TaskEvent extends EventObject implements Serializable {
    /**
     * Constructs a new instance of this class.
     *
     * @param source the object which fired the event.
     */
    private int quantity;
    private String resource;
    private String building;
    private int people;

    public TaskEvent(Object source) {
        super(source);
    }

    public int getPeople() {
        return people;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getBuilding() {
        return building;
    }

    public String getResource() {
        return resource;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
