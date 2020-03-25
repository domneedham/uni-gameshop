package GameShop.java.models;

import GameShop.java.models.enums.ConsoleForm;

public class Console extends Product {
    private static int idSeed = 1000;
    private final String id;
    private ConsoleForm form;
    private int bit;

    public Console(String name, ConsoleForm form, double cost, int bit, boolean inForRepair) {
        super(name, cost, inForRepair);
        String idPrefix = "CO";
        id = String.format("%s%d", idPrefix, idSeed);
        this.form = form;
        this.bit = bit;
        idSeed++;
    }

    public String getId() {
        return id;
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


}
