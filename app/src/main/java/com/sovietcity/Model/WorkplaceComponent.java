package com.sovietcity.Model;

import java.io.Serializable;

//реализует логику получения раб.мест
public class WorkplaceComponent implements Serializable {
    public int getNewBusyWorkplaces(Specialty specialty, int workplaces, int busyWorkplaces) {
        if (specialty.getAllWorkplaces() > specialty.getBusyWorkplaces()) {
            if (specialty.getAllWorkplaces() - specialty.getBusyWorkplaces() >= workplaces - busyWorkplaces) {
                int busyWorkplaces1 = workplaces - busyWorkplaces;
                busyWorkplaces = workplaces;
                specialty.addBusyWorkplaces(busyWorkplaces1);
            } else {
                busyWorkplaces = busyWorkplaces + (specialty.getAllWorkplaces() - specialty.getBusyWorkplaces());
                specialty.setBusyWorkplaces(specialty.getAllWorkplaces());
            }
        }
        if (specialty.getAllWorkplaces() < specialty.getBusyWorkplaces()) {
            if (busyWorkplaces > specialty.getBusyWorkplaces() - specialty.getAllWorkplaces()) {
                busyWorkplaces -= (specialty.getBusyWorkplaces() - specialty.getAllWorkplaces());
                specialty.setBusyWorkplaces(specialty.getAllWorkplaces());
            } else {
                specialty.setBusyWorkplaces(specialty.getBusyWorkplaces() - busyWorkplaces);
                busyWorkplaces = 0;
            }
        }
        return busyWorkplaces;
    }
}
