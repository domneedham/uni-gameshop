package GameShop.java.models;

import GameShop.java.models.exceptions.ProductRentalException;

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

    public void rentItem() throws Exception {
        if (!isAvailable()) {
            throw new ProductRentalException(
                    String.format(
                            "%s.\n Current state:\n Currently Rented: %s \n In For Repair: %s",
                            getErrorMessage(), isCurrentlyRented(), isInForRepair()
                    )
            );
        } else {
            this.currentlyRented = true;
        }
    }

    abstract String getErrorMessage();

    public void returnItem() {
        this.currentlyRented = false;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
