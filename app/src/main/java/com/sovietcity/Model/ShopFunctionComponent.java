package com.sovietcity.Model;

import com.sovietcity.Presenter.World;
import com.sovietcity.View.MainActivity;

import java.io.Serializable;

//функциональный компонент магазина
public class ShopFunctionComponent extends FunctionComponent implements IChainDestination, Serializable {
    private transient PopulationManager populationManager;
    private int allWorkplaces;
    private int busyWorkplaces;
    private Specialty specialty;
    private Material food;
    private int id;
    private WorkplaceComponent workplaceComponent;

    @Override
    public void function() {
        if (this.allWorkplaces > this.busyWorkplaces) {
            workplaceComponent.getNewBusyWorkplaces(specialty, allWorkplaces, busyWorkplaces);
        }
        if (this.allWorkplaces == this.busyWorkplaces) {
            if (food.getQuantity() >= populationManager.getPopulation().getNeedFoodQuantity()) {
                populationManager.getPopulation().addFood(populationManager.getPopulation().getNeedFoodQuantity());
                this.food.decreaseQuantity(populationManager.getPopulation().getNeedFoodQuantity());
            } else {
                populationManager.getPopulation().addFood(food.getQuantity());
                this.food.setQuantity(0);

            }
        }

    }

    @Override
    public void startFunction(BuildModel buildModel) {
        specialty.setiContainerWorkplaces(specialty.getiContainerWorkplaces() + allWorkplaces);
        WorkplaceComponent workplaceComponent = new WorkplaceComponent();
        this.workplaceComponent = workplaceComponent;
    }

    @Override
    public void about(MainActivity mainActivity, int y, int x) {
        mainActivity.shopFragmentTransaction(this);
    }

    @Override
    public IChainDestination getChainDestination() {
        return this;
    }

    @Override
    public IChainSender getChainSender() {
        return null;
    }

    @Override
    public void deserialization(World world) {
        this.food.setResource(world.getResourceManager().getResource("Еда"));
    }

    @Override
    public boolean cloneable() {
        return true;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }



    @Override
    public int getId() {
        return id;
    }


    public void setPopulationManager(PopulationManager populationManager) {
        this.populationManager = populationManager;
    }

    @Override
    public void addDelivery(Delivery delivery) {
        Material material = new Material();
        material.setResource(delivery.getResource());
        material.setQuantity(delivery.getQuantity());
        if (material.getResource().equals(this.food.getResource())) {
            this.food.addQuantity(material.getQuantity());
        }
        delivery.setFree(true);
    }

    @Override
    public boolean canAddDelivery(Delivery delivery) {
        if (this.food.getResource().equals(delivery.getResource())) {
            return true;
        }
        return false;
    }

    @Override
    public int getDayDuration() {
        return 3;
    }

    @Override
    public int getDeliveryPrice() {
        return 200;
    }

    public ShopFunctionComponent clone() {
        ShopFunctionComponent shopFunctionComponent = new ShopFunctionComponent();
        shopFunctionComponent.populationManager = this.populationManager;
        shopFunctionComponent.food = new Material(this.food.getResource());
        shopFunctionComponent.specialty = this.specialty;
        shopFunctionComponent.workplaceComponent = this.workplaceComponent;
        shopFunctionComponent.allWorkplaces = this.allWorkplaces;
        shopFunctionComponent.busyWorkplaces = 0;
        return shopFunctionComponent;
    }

    @Override
    public void setBlocks(int blocks) {

    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public void setFood(Material food) {
        this.food = food;
    }

    public Material getFood() {
        return food;
    }

    public boolean isWorking() {
        if (busyWorkplaces >= allWorkplaces) {
            return true;
        }
        return false;
    }

    public void setAllWorkplaces(int allWorkplaces) {
        this.allWorkplaces = allWorkplaces;
    }

    public void setBusyWorkplaces(int busyWorkplaces) {
        this.busyWorkplaces = busyWorkplaces;
    }

    @Override
    public String toString() {
        return "Обеспечение населения едой";
    }
}
