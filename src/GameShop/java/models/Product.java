package GameShop.java.models;

import java.util.UUID;

public abstract class Product {
    private final String id;
    private String name;
    private double cost;
    private boolean inForRepair;
    private boolean currentlyRented = false;

    public Product(String name, double cost, boolean inForRepair) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.cost = cost;
        this.inForRepair = inForRepair;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public boolean isInForRepair() {
        return inForRepair;
    }

    public boolean isCurrentlyRented() {
        return currentlyRented;
    }

    public boolean isAvailable() {
        return !inForRepair && !currentlyRented;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setInForRepair(boolean inForRepair) {
        this.inForRepair = inForRepair;
    }

    public void rentItem() throws Error {
        if (!isAvailable()) {
            throw new Error("Product can not be rented when it is not available");
        } else {
            this.currentlyRented = true;
        }
    }

    public void returnItem() {
        this.currentlyRented = false;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
