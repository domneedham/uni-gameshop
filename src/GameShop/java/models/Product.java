package GameShop.java.models;

public abstract class Product {
    private String name;
    private double cost;
    private boolean inForRepair;
    private boolean currentlyRented = false;

    public Product(String name, double cost, boolean inForRepair) {
        this.name = name;
        this.cost = cost;
        this.inForRepair = inForRepair;
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

    public void setIsCurrentlyRented(boolean status) {
        this.currentlyRented = status;
    }
}
