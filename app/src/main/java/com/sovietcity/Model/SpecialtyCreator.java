package com.sovietcity.Model;

import android.content.Context;

import com.sovietcity.R;

import java.io.Serializable;
import java.util.ArrayList;

//инициализация отраслей производства
public class SpecialtyCreator implements Serializable {
    public ArrayList<Specialty> createSpecialities(Context context) {
        ArrayList<Specialty> arrayList = new ArrayList<>();
        Specialty resSpecialty = new Specialty("Ресурсодобывающий комплекс");
        resSpecialty.setImage(context, R.drawable.resource_extracting);
        arrayList.add(resSpecialty);
        Specialty agrSpecialty = new Specialty("Агропромышленный комплекс");
        agrSpecialty.setImage(context, R.drawable.farm_icon);
        arrayList.add(agrSpecialty);
        Specialty serviceSpecialty = new Specialty("Обслуживающий комплекс");
        serviceSpecialty.setImage(context, R.drawable.service_icon);
        arrayList.add(serviceSpecialty);
        Specialty industrySpecialty = new Specialty("Промышленный комплекс");
        industrySpecialty.setImage(context, R.drawable.industry_icon);
        arrayList.add(industrySpecialty);
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).setBaseChance(20);
        }
        return arrayList;
    }

    public void createSpecialities(SpecialtyWorker specialtyWorker, Context context) {
        specialtyWorker.getSpecialty("Ресурсодобывающий комплекс").setImage(context, R.drawable.resource_extracting);
        specialtyWorker.getSpecialty("Агропромышленный комплекс").setImage(context, R.drawable.farm_icon);
        specialtyWorker.getSpecialty("Обслуживающий комплекс").setImage(context, R.drawable.service_icon);
        specialtyWorker.getSpecialty("Промышленный комплекс").setImage(context, R.drawable.industry_icon);

    }
}
