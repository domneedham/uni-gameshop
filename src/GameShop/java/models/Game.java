package GameShop.java.models;

import GameShop.java.models.interfaces.IProduct;

public class Game implements IProduct {
    private final String idPrefix = "GA";
    private static int idSeed = 1000;
    private String id;
    private String name;
    private Console console;
    private double cost;
    private boolean inForRepair;
    private boolean currentlyRented = false;

    public Game(String name, Console console, double cost, boolean inForRepair) {
        id = String.format("%s%d", idPrefix, idSeed);
        this.name = name;
        this.console = console;
        this.cost = cost;
        this.inForRepair = inForRepair;
        idSeed++;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean isInForRepair() {
        return inForRepair;
    }

    @Override
    public boolean isCurrentlyRented() { return currentlyRented; }

    @Override
    public boolean isAvailable() {
        if (inForRepair || currentlyRented) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void setInForRepair(boolean inForRepair) {
        this.inForRepair = inForRepair;
    }

    @Override
    public void setIsCurrentlyRented(boolean status) {
        this.currentlyRented = status;
    }

    public Console getConsole() {
        return console;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }
}
