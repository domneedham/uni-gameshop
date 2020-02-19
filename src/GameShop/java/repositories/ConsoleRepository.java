package GameShop.java.repositories;

import GameShop.App;
import GameShop.java.models.Console;

import java.util.ArrayList;

public class ConsoleRepository {
    public ArrayList<Console> getAllConsoles() { return App.state.getConsoles(); };

    public ArrayList<Console> getAvailableConsoles() {
        ArrayList<Console> availableConsoles = new ArrayList<>();
        for (Console c: getAllConsoles()) {
            if (!c.isInForRepair()) {
                availableConsoles.add(c);
            }
        }
        return availableConsoles;
    }

    public Console getById(String id) {
        for (Console c: getAllConsoles()) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public void addConsole(Console console) {
        if (!getAllConsoles().contains(console)) {
            getAllConsoles().add(console);
        }
    }

    public void removeConsole(Console console) {
        if (getAllConsoles().contains(console)) {
            getAllConsoles().remove(console);
        }
    }

    public void modifyConsole(Console console) {
        for (Console c: getAllConsoles()) {
            if (c.getId().equals(console.getId())) {
                c.setName(console.getName());
                c.setInForRepair(console.isInForRepair());
            }
        }
    }

    public String getId(Console console) { return console.getId(); }
    public String getName(Console console) { return console.getName(); }
    public String getForm(Console console) { return console.getForm().toString(); }
    public int getBit(Console console) { return console.getBit(); }
    public boolean isInForRepair(Console console) { return console.isInForRepair(); }
    public double getCost(Console console) { return console.getCost(); }
}
