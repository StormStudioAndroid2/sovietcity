package com.sovietcity.Model;

import com.sovietcity.Presenter.World;
import com.sovietcity.View.MainActivity;

import java.io.Serializable;
import java.util.ArrayList;

//функциональный компонент склада
public class StockFunctionComponent extends FunctionComponent implements IChainDestination, IChainSender, Serializable {
    private boolean isImport;
    private transient CashManager cashManager;
    private Material material;
    private transient ArrayList<Cell> startCells;
    private transient DeliveryCreator deliveryCreator;
    private Specialty specialty;
    private int workplaces;
    private int id;
    private int busyWorkplaces;
    private WorkplaceComponent workplaceComponent;

    @Override
    public void function() {

        if (this.workplaces > busyWorkplaces) {
            this.busyWorkplaces = workplaceComponent.getNewBusyWorkplaces(specialty, workplaces, busyWorkplaces);
        }
        if (workplaces <= busyWorkplaces) {
            if (isImport) {
                importMaterial();
            } else {
                exportMaterial();
            }
            if (!materialIsNull()) {
                this.deliveryCreator.startDelivery(startCells, material);
            }
        }
    }

    @Override
    public void startFunction(BuildModel buildModel) {
        specialty.setiContainerWorkplaces(specialty.getiContainerWorkplaces() + workplaces);
        this.workplaceComponent = new WorkplaceComponent();
    }

    @Override
    public void about(MainActivity mainActivity, int y, int x) {
        mainActivity.stockFragmentTransaction(this);
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
        if (!this.materialIsNull()) {
            this.material.setResource(world.getResourceManager().getResource(material.getResource().getName()));
        }
        this.setSpecialty(world.getSpecialtyManager().getSpeciality(this.specialty.getName()));
        this.startCells = new ArrayList<>();
        this.deliveryCreator = world.getDeliveryCreator();
        this.cashManager = world.getCashManager();
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
        StockFunctionComponent stockFunctionComponent = new StockFunctionComponent();
        stockFunctionComponent.setDeliveryCreator(deliveryCreator);
        stockFunctionComponent.setCashManager(cashManager);
        stockFunctionComponent.startCells = new ArrayList<>();
        stockFunctionComponent.workplaces = this.workplaces;
        stockFunctionComponent.specialty = this.specialty;
        stockFunctionComponent.workplaceComponent = this.workplaceComponent;
        return stockFunctionComponent;
    }

    @Override
    public void setBlocks(int blocks) {

    }


    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setMaterial(Resource resource) {
        this.material = new Material();
        this.material.setResource(resource);
        this.material.setWasteQuantity(4);
    }

    public void setImport(boolean anImport) {
        isImport = anImport;
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
        if ((!this.materialIsNull()) && (this.material.getResource().equals(delivery.getResource()))&&(!this.isImport())) {
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

    public void exportMaterial() {
        if (this.material != null) {
            this.cashManager.addCash(this.material.getResource().getPrice() * this.material.getWasteQuantity());
        }
    }

    public void importMaterial() {
        if (!this.materialIsNull()) {
            boolean b = this.cashManager.wasteMoney(this.material.getResource().getPrice() * this.material.getWasteQuantity());
            if (b) {
                this.material.addQuantity(this.material.getWasteQuantity());
            }
        }
    }

    private void addMaterial(Material material) {
        if ((!this.materialIsNull()) && (this.material.getResource().equals(material))) {
            this.material.addQuantity(material.getQuantity());
        }
    }

    public void setDeliveryCreator(DeliveryCreator deliveryCreator) {
        this.deliveryCreator = deliveryCreator;
    }

    public boolean materialIsNull() {
        if (this.material == null) {
            return true;
        }
        return false;
    }

    public Material getMaterial() {
        return material;
    }

    public boolean isImport() {
        return isImport;
    }


    public void setCashManager(CashManager cashManager) {
        this.cashManager = cashManager;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    private void addWorkplaces() {
        if (this.specialty.getAllWorkplaces() > this.specialty.getBusyWorkplaces()) {
            if (this.specialty.getAllWorkplaces() - this.specialty.getBusyWorkplaces() >= this.workplaces - this.busyWorkplaces) {
                this.busyWorkplaces = specialty.getAllWorkplaces() - specialty.getBusyWorkplaces();
                this.specialty.addBusyWorkplaces(this.workplaces - this.busyWorkplaces);
            } else {
                this.busyWorkplaces = specialty.getAllWorkplaces() - specialty.getBusyWorkplaces();
                specialty.setBusyWorkplaces(specialty.getAllWorkplaces());
            }
        }
        if (this.specialty.getAllWorkplaces() < this.specialty.getBusyWorkplaces()) {
            if (this.busyWorkplaces > specialty.getBusyWorkplaces() - specialty.getAllWorkplaces()) {
                this.busyWorkplaces -= specialty.getBusyWorkplaces() - specialty.getAllWorkplaces();
                specialty.setBusyWorkplaces(specialty.getAllWorkplaces());
            } else {
                specialty.setBusyWorkplaces(specialty.getBusyWorkplaces() - busyWorkplaces);
                this.busyWorkplaces = 0;
            }
        }
    }

    public int getWorkplaces() {
        return workplaces;
    }

    public void setBusyWorkplaces(int busyWorkplaces) {
        this.busyWorkplaces = busyWorkplaces;
    }

    public boolean isWorking() {
        if (this.workplaces <= this.busyWorkplaces) {
            return true;
        }
        return false;
    }

    public void setWorkplaces(int workplaces) {
        this.workplaces = workplaces;
    }

    @Override
    public String toString() {
        return "Импорт и экспорт материалов";
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
