package GameShop.java.models;

import GameShop.java.models.enums.ConsoleForm;

public class Console extends Product {
    private ConsoleForm form;
    private int bit;

    public Console(String name, ConsoleForm form, double cost, int bit, boolean inForRepair) {
        super(name, cost, inForRepair);
        this.form = form;
        this.bit = bit;
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
    String getErrorMessage() {
        return "Console can not be rented if it is not available";
    }
}
