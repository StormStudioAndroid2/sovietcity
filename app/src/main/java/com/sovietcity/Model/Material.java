package com.sovietcity.Model;

import java.io.Serializable;

//оболочка ресурса с определенным количеством и количеством, которое тратится на производство
public class Material implements Serializable {
    private double quantity;
    private Resource resource;
    private double wasteQuantity;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setWasteQuantity(double wasteQuantity) {
        this.wasteQuantity = wasteQuantity;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public double getWasteQuantity() {
        return this.wasteQuantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Resource getResource() {
        return resource;
    }

    public boolean wasteMaterial() {
        if (this.quantity >= wasteQuantity) {
            this.quantity -= wasteQuantity;
            return true;
        }
        return false;
    }

    public boolean canWaste() {
        if (this.quantity >= wasteQuantity) {
            return true;
        }
        return false;
    }

    public boolean equalMaterial(Material m) {
        if (m.resource.equals(this.resource)) {
            return true;
        }
        return false;
    }

    public boolean addMaterial(Material m) {
        if (this.equalMaterial(m)) {
            this.quantity += m.quantity;
            return true;
        }
        return false;
    }

    public void addQuantity(double quantity) {
        this.quantity += quantity;
    }

    public boolean decreaseQuantity(double quantity) {
        if (this.quantity - quantity > 0) {
            this.quantity -= quantity;
            return true;
        }
        return false;
    }

    public boolean hasResource(Resource resource) {
        if (resource.equals(this.resource)) {
            return true;
        }
        return false;
    }

    public boolean hasResource(String resourceName) {
        if (resourceName.equals(this.resource.getName())) {
            return true;
        }
        return false;
    }

    public Material(Material material) {
        this.resource = material.resource;
        this.wasteQuantity = material.wasteQuantity;
        this.quantity = material.quantity;
    }

    public Material() {

    }

    public Material(Resource resource) {
        this.resource = resource;
    }
}
