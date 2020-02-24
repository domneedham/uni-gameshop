package GameShop.java.models;

import GameShop.java.models.enums.ConsoleForm;
import GameShop.java.models.interfaces.IProduct;

public class Console implements IProduct {
    private final String idPrefix = "CO";
    private static int idSeed = 1000;
    private String id;
    private String name;
    private ConsoleForm form;
    private double cost;
    private int bit;
    private boolean inForRepair;

    public Console(String name, ConsoleForm form, double cost, int bit, boolean inForRepair) {
        id = String.format("%s%d", idPrefix, idSeed);
        this.name = name;
        this.form = form;
        this.cost = cost;
        this.bit = bit;
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
    public void setInForRepair(boolean inForRepair) {
        this.inForRepair = inForRepair;
    }

    public ConsoleForm getForm() { return form; }

    public void setForm(ConsoleForm form) {
        this.form = form;
    }

    public int getBit() {
        return bit;
    }

    public void setBit(int bit) {
        this.bit = bit;
    }

    @Override
    public String toString() {
        return "Console{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", form='" + form + '\'' +
                ", cost=" + cost +
                ", bit=" + bit +
                ", inForRepair=" + inForRepair +
                '}';
    }
}
