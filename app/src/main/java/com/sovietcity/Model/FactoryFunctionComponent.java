package com.sovietcity.Model;

import com.sovietcity.Presenter.World;
import com.sovietcity.View.MainActivity;

import java.io.Serializable;
import java.util.ArrayList;

//    функциональный компонент завода

public class FactoryFunctionComponent extends FunctionComponent implements IChainDestination, IChainSender, Serializable {
    private int workplaces;
    private int id;
    private int busyWorkplaces;
    private Material firstMaterial;
    private Material secondMaterial;
    private Material manufactureMaterial;
    private transient ArrayList<Cell> startCells;
    private double production;
    private Specialty factorySpecialty;
    private transient DeliveryCreator deliveryCreator;
    private WorkplaceComponent workplaceComponent;
    private transient PopulationDataComponent populationDataComponent;

    public FactoryFunctionComponent() {
        this.manufactureMaterial = new Material();
        manufactureMaterial.setQuantity(0);
        this.startCells = new ArrayList<>();
    }

    @Override
    public void function() {
        if (this.workplaces > this.busyWorkplaces) {
            this.busyWorkplaces = workplaceComponent.getNewBusyWorkplaces(factorySpecialty, workplaces, busyWorkplaces);
        }

        if (((firstMaterial == null) || ((firstMaterial.canWaste()) && (firstMaterial.wasteMaterial()))) && ((secondMaterial == null) || ((secondMaterial.canWaste()) && (secondMaterial.wasteMaterial())))) {
            manufactureMaterial.addQuantity((1.0 * busyWorkplaces / workplaces) * production * populationDataComponent.getPowerK());
            this.deliveryCreator.startDelivery(startCells, manufactureMaterial);
        }
    }

    @Override
    public void startFunction(BuildModel buildModel) {
        factorySpecialty.setiContainerWorkplaces(factorySpecialty.getiContainerWorkplaces() + workplaces);
        this.workplaceComponent = new WorkplaceComponent();
    }

    @Override
    public void about(MainActivity mainActivity, int y, int x) {
        mainActivity.factoryDescribeTransaction(GameMap.getGameMapCell(y, x).getBuildModel(), this);
    }

    @Override
    public IChainDestination getChainDestination() {
        return this;
    }

    @Override
    public IChainSender getChainSender() {
        return this;
    }

    @Override
    public void deserialization(World world) {
        this.populationDataComponent = world.getPopulationManager().getPopulation().getPopulationDataComponent();
        this.deliveryCreator = world.getDeliveryCreator();
        this.factorySpecialty = world.getSpecialtyManager().getSpeciality(factorySpecialty.getName());
        if (firstMaterial != null) {
            firstMaterial.setResource(world.getResourceManager().getResource(firstMaterial.getResource().getName()));
        }
        if (secondMaterial != null) {
            secondMaterial.setResource(world.getResourceManager().getResource(secondMaterial.getResource().getName()));
        }
        if (manufactureMaterial != null) {
            manufactureMaterial.setResource(world.getResourceManager().getResource(manufactureMaterial.getResource().getName()));
        }
        this.startCells = new ArrayList<>();
        this.workplaceComponent = new WorkplaceComponent();

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

    @Override
    public FunctionComponent clone() {
        FactoryFunctionComponent factoryFunctionComponent = new FactoryFunctionComponent();
        factoryFunctionComponent.workplaces = this.workplaces;
        factoryFunctionComponent.busyWorkplaces = this.busyWorkplaces;
        if (this.firstMaterial != null) {
            factoryFunctionComponent.firstMaterial = new Material(this.firstMaterial);

        }
        if (this.secondMaterial != null) {
            factoryFunctionComponent.secondMaterial = new Material(this.getSecondMaterial());
        }
        if (this.manufactureMaterial != null) {
            factoryFunctionComponent.manufactureMaterial = new Material(this.getManufactureMaterial());
        }
        factoryFunctionComponent.startCells = new ArrayList<Cell>();
        factoryFunctionComponent.production = this.production;
        factoryFunctionComponent.populationDataComponent = this.populationDataComponent;
        factoryFunctionComponent.deliveryCreator = this.deliveryCreator;
        factoryFunctionComponent.factorySpecialty = this.factorySpecialty;
        factoryFunctionComponent.workplaceComponent = this.workplaceComponent;
        return factoryFunctionComponent;
    }

    @Override
    public void setBlocks(int blocks) {

    }


    public double getManufacturedMaterial() {
        return ((busyWorkplaces) / (workplaces)) * production * 2;
    }

    public void setProduction(double production) {
        this.production = production;
    }

    public void setWorkplaces(int workplaces1) {
        this.workplaces = workplaces1;
    }

    public int getWorkplaces() {
        return this.workplaces;
    }


    public int getBusyWorkplaces() {
        return this.busyWorkplaces;
    }

    public boolean addMaterial(Material material) {
        if ((firstMaterial != null) && (!firstMaterial.addMaterial(material))) {
            if ((secondMaterial != null) && (!secondMaterial.addMaterial(material))) {
                if ((manufactureMaterial != null) && (!manufactureMaterial.addMaterial(material))) {
                    return false;
                }
            }
        }
        return true;
    }

    public double getResource(String resourceName) {
        double result = 0;
        if (manufactureMaterial.hasResource(resourceName)) {
            result += manufactureMaterial.getQuantity();
        }
        if ((firstMaterial != null) && firstMaterial.hasResource(resourceName)) {
            result += firstMaterial.getQuantity();
        }
        if ((secondMaterial != null) && (secondMaterial.hasResource(resourceName))) {
            result += secondMaterial.getQuantity();
        }
        return result;
    }

    public void setManufactureMaterial(Material manufactureMaterial) {
        this.manufactureMaterial = manufactureMaterial;
    }

    public void setFirstMaterial(Material firstMaterial) {
        this.firstMaterial = firstMaterial;
    }

    public void setSecondMaterial(Material secondMaterial) {
        this.secondMaterial = secondMaterial;
    }

    public void setDeliveryCreator(DeliveryCreator deliveryCreator) {
        this.deliveryCreator = deliveryCreator;
    }


    @Override
    public void addDelivery(Delivery delivery) {
        Material material = new Material();
        material.setResource(delivery.getResource());
        material.setQuantity(delivery.getQuantity());
        this.addMaterial(material);
        delivery.setFree(true);
    }

    @Override
    public boolean canAddDelivery(Delivery delivery) {
        if (((this.firstMaterial != null) && (firstMaterial.getResource().equals(delivery.getResource()))) || ((this.secondMaterial != null) && (this.secondMaterial.getResource().equals(delivery.getResource())))) {
            return true;
        }
        return false;
    }

    @Override
    public int getDayDuration() {
        return 2;
    }

    @Override
    public int getDeliveryPrice() {
        return 200;
    }

    public Material getFirstMaterial() {
        return firstMaterial;
    }

    public Material getManufactureMaterial() {
        return manufactureMaterial;
    }

    public Material getSecondMaterial() {

        return secondMaterial;

    }

    public FactoryFunctionComponent(FactoryFunctionComponent factoryFunctionComponent) {
        this.workplaces = factoryFunctionComponent.workplaces;
        this.busyWorkplaces = factoryFunctionComponent.busyWorkplaces;
        if (factoryFunctionComponent.firstMaterial != null) {
            this.firstMaterial = new Material(factoryFunctionComponent.firstMaterial);

        }
        if (factoryFunctionComponent.secondMaterial != null) {
            this.secondMaterial = new Material(factoryFunctionComponent.getSecondMaterial());
        }
        if (factoryFunctionComponent.manufactureMaterial != null) {
            this.manufactureMaterial = new Material(factoryFunctionComponent.getManufactureMaterial());
        }
        this.startCells = new ArrayList<Cell>();
        this.production = factoryFunctionComponent.production;
        this.deliveryCreator = factoryFunctionComponent.deliveryCreator;
        this.factorySpecialty = factoryFunctionComponent.factorySpecialty;
    }

    public Specialty getFactorySpecialty() {
        return factorySpecialty;
    }

    public void setFactorySpecialty(Specialty factorySpecialty) {
        this.factorySpecialty = factorySpecialty;
    }

    public void setPopulationDataComponent(PopulationDataComponent populationDataComponent) {
        this.populationDataComponent = populationDataComponent;
    }

    @Override
    public String toString() {
        return "Производство материала: " + manufactureMaterial.getResource().getName();
    }

    @Override
    public void addCell(Cell cell) {
        if (this.startCells == null) {
            this.startCells = new ArrayList<>();
        }
        this.startCells.add(cell);
    }

    @Override
    public void deleteCell(Cell cell) {
        if (this.startCells == null) {
            this.startCells = new ArrayList<>();
        }
        this.startCells.remove(cell);

    }
}
