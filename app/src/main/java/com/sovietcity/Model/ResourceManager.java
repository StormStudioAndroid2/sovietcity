package com.sovietcity.Model;

/**
 * Created by Sergey on 22.12.2015.
 */

import android.content.Context;
import android.graphics.BitmapFactory;

import com.sovietcity.Presenter.DrawerManager;
import com.sovietcity.R;

import java.io.Serializable;
import java.util.ArrayList;

public class ResourceManager implements Serializable {
    private TaskListener taskListener;
    private ArrayList<Resource> resources;
    private ArrayList<FunctionComponent> functionComponents;

    public ResourceManager(Context context, DrawerManager drawerManager) {
        ResourceCreator resourceCreator = new ResourceCreator();
        this.resources = new ArrayList<Resource>();
        this.functionComponents = new ArrayList<>();
        Resource militaryResources = new Resource("Военные припасы");
        this.resources.add(resourceCreator.createResource("ЖБИ", drawerManager.setSize(BitmapFactory.decodeResource(context.getResources(), R.drawable.icp_res_icon)), 100));
        this.resources.add(resourceCreator.createResource("Песок", drawerManager.setSize(BitmapFactory.decodeResource(context.getResources(), R.drawable.sand)), 10));
        this.resources.add(resourceCreator.createResource("Сталь", drawerManager.setSize(BitmapFactory.decodeResource(context.getResources(), R.drawable.iron_icon)), 250));
        this.resources.add(militaryResources);
        this.resources.add(resourceCreator.createResource("Цемент", drawerManager.setSize(BitmapFactory.decodeResource(context.getResources(), R.drawable.cement_icon)), 50));
        this.resources.add(resourceCreator.createResource("Кирпич", drawerManager.setSize(BitmapFactory.decodeResource(context.getResources(), R.drawable.brick_icon)), 50));
        this.resources.add(resourceCreator.createResource("Железная руда", drawerManager.setSize(BitmapFactory.decodeResource(context.getResources(), R.drawable.iron_ore)), 50));
        this.resources.add(resourceCreator.createResource("Мясо", drawerManager.setSize(BitmapFactory.decodeResource(context.getResources(), R.drawable.meat_icon)), 150));
        this.resources.add(resourceCreator.createResource("Еда", drawerManager.setSize(BitmapFactory.decodeResource(context.getResources(), R.drawable.food_icon)), 200));
        this.resources.add(resourceCreator.createResource("Пшеница", drawerManager.setSize(BitmapFactory.decodeResource(context.getResources(), R.drawable.wheat_icon)), 120));

    }

    public void setTaskListener(TaskListener taskListener) {
        this.taskListener = taskListener;
    }

    public void manage() {
        for (FunctionComponent f : functionComponents) {
            f.function();

        }
    }

    public Resource getResource(String nameResource) {
        if (nameResource != null) {
            for (Resource i : resources) {
                if (i.getName().equals(nameResource)) {
                    return i;
                }
            }
        }
        return null;
    }

    private void createEvent(FactoryFunctionComponent factoryFunctionComponent) {
        TaskEvent taskEvent = new TaskEvent(this);
        taskEvent.setResource(factoryFunctionComponent.getManufactureMaterial().getResource().getName());
        taskEvent.setQuantity((int) Math.round(factoryFunctionComponent.getManufacturedMaterial()));
        this.taskListener.observeTask(taskEvent);
    }


    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void addFunctionComponent(FunctionComponent functionComponent) {
        boolean flag = true;
        for (int i = 0; i < functionComponents.size(); i++) {
            if (functionComponents.get(i).equals(functionComponent)) {
                flag=false;
            }
        }
        if (flag) {
            this.functionComponents.add(functionComponent);
        }
    }
    public void deleteFunctionComponent(FunctionComponent functionComponent) {
        for (int i = 0; i < functionComponents.size(); i++) {
            functionComponents.remove(functionComponent);

        }
    }
}