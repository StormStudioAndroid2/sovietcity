package com.sovietcity.Model;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

//управляет отраслями производства и распределением людишек в них
public class SpecialtyManager  implements Serializable {
    private SpecialtyWorker specialtyWorker;

    public Specialty getSpeciality(String name) {
        return specialtyWorker.getSpecialty(name);
    }

    public SpecialtyManager(Context context) {
        this.specialtyWorker = new SpecialtyWorker(context);
    }
    public SpecialtyManager(SpecialtyWorker specialtyWorker) {
        this.specialtyWorker = specialtyWorker;
    }
    public void distributeWorkers(int people) {
        for (int i = 1; i <= people; i++) {
            specialtyWorker.distributionWorker();
        }
    }

    public void deleteWorkers(int people) {
        for (int i = 1; i <= people; i++) {
            specialtyWorker.deleteWorker();
        }
    }

    public ArrayList<Specialty> getSpecialties() {
        return specialtyWorker.getSpecialties();
    }

    public int getAllWorkplaces() {
        int workplaces = 0;
        for (int i = 0; i < specialtyWorker.getSpecialties().size(); i++) {
            workplaces += specialtyWorker.getSpecialties().get(i).getAllWorkplaces();
        }
        return workplaces;
    }

    public int getBusyWorkplaces() {
        int workplaces = 0;
        for (int i = 0; i < specialtyWorker.getSpecialties().size(); i++) {
            workplaces += specialtyWorker.getSpecialties().get(i).getBusyWorkplaces();
        }
        return workplaces;
    }
    public int getIContainerPlaces() {
        int workplaces = 0;
        for (int i = 0; i < specialtyWorker.getSpecialties().size(); i++) {
            workplaces += specialtyWorker.getSpecialties().get(i).getiContainerWorkplaces();
        }
        return workplaces;
    }

    public SpecialtyWorker getSpecialityWorker() {
        return specialtyWorker;
    }
}
