package com.sovietcity.Model;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

//управляет распределением людишек в отраслях
public class SpecialtyWorker implements Serializable {
    private int allChance;
    private transient Random random;
    private ArrayList<Specialty> specialties;

    public Specialty getSpecialty(String name) {
        for (int i = 0; i < specialties.size(); i++) {
            if (specialties.get(i).getName().equals(name)) {
                return specialties.get(i);
            }
        }
        return null;

    }

    public void distributionWorker() {
        updateChances();
        int chance = random.nextInt(allChance + 1);
        int index = -1;
        for (int i = 0; i < specialties.size(); i++) {
            int chance1 = specialties.get(i).getChance();
            if (i == specialties.size() - 1) {
                chance1 += specialties.get(i).getBaseChance();
            }
            if ((chance <= chance1) && (specialties.get(i).getiContainerWorkplaces() > specialties.get(i).getAllWorkplaces())) {
                index = i;
            }
        }
        if (index != -1) {
            specialties.get(index).addWorkplace();
        }

    }

    public void deleteWorker() {
        updateChances();
        int chance = random.nextInt(allChance + 1);
        int index = 0;

        for (int i = 0; i < specialties.size(); i++) {
            int chance1 = specialties.get(i).getChance();
            if (i == specialties.size() - 1) {
                chance1 += specialties.get(i).getBaseChance();
            }
            if ((chance <= chance1) && (specialties.get(i).getiContainerWorkplaces() > specialties.get(i).getAllWorkplaces())) {
                index = i;
            }
        }

        specialties.get(index).deleteWorkplace();
    }

    public SpecialtyWorker(Context context) {
        SpecialtyCreator specialtyCreator = new SpecialtyCreator();
        this.specialties = specialtyCreator.createSpecialities(context);
        updateChances();
        this.random = new Random();

    }

    public void updateChances() {
        int chances = 0;
        specialties.get(0).setChance(specialties.get(0).getBaseChance());
        for (int i = 1; i < specialties.size(); i++) {
            specialties.get(i).setChance(chances + specialties.get(i).getBaseChance());
            chances += specialties.get(i).getBaseChance();
        }
        allChance = chances;
    }

    public void setChance(int index, int chance) {
        specialties.get(index).setChance(chance);
        updateChances();
    }

    public void setChance(String name, int chance) {
        getSpecialty(name).setChance(chance);
        updateChances();
    }

    public ArrayList<Specialty> getSpecialties() {
        return specialties;
    }

    public SpecialtyWorker loadSpecialtyWorker(Context context) {
        this.random = new Random();
        SpecialtyCreator specialtyCreator = new SpecialtyCreator();
        specialtyCreator.createSpecialities(this, context);
        return this;
    }
}
