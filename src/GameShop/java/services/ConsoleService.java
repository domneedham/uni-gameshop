package GameShop.java.services;

import GameShop.java.models.Console;
import GameShop.java.repositories.ConsoleRepository;
import GameShop.java.services.interfaces.IConsoleService;

import java.util.ArrayList;

public class ConsoleService implements IConsoleService {
    protected static final ConsoleRepository repo = new ConsoleRepository();

    @Override
    public ArrayList<Console> getAllConsoles() { return repo.getAllConsoles(); }

    @Override
    public ArrayList<Console> getAvailableConsoles() { return repo.getAvailableConsoles(); }

    @Override
    public Console getById(String id) { return repo.getById(id); }

    @Override
    public boolean idExists(String id) { return repo.getById(id) != null; }

    @Override
    public void addConsole(Console console) { repo.addConsole(console); }

    @Override
    public void removeConsole(Console console) { repo.removeConsole(console); }

    @Override
    public void modifyConsole(Console console, String newName, boolean inForRepair) {
        console.setName(newName);
        console.setInForRepair(inForRepair);
        repo.modifyConsole(console);
    }

    @Override
    public void rentConsole(Console console) throws Exception {
        repo.rentConsole(console);
    }

    @Override
    public void returnConsole(Console console) {
        repo.returnConsole(console);
    }
}
