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

    // used for JAVA FXML tables to show formatted data
    public String getFXMLId(Console console) { return console.getId(); }
    public String getFXMLName(Console console) { return console.getName(); }
    public String getFXMLForm(Console console) {
        String name = console.getForm().toString();
        name = name.toLowerCase();
        return name;
    }
    public String getFXMLBit(Console console) { return String.valueOf(console.getBit()); }
    public String getFXMLAvailable(Console console) { return console.isInForRepair() ? "No" : "Yes" ; }
    public String getFXMLFormattedCost(Console console) { return String.format("£%.2f", console.getCost()); }
}
