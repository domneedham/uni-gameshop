package GameShop.java.services.interfaces;

import GameShop.java.models.Console;

import java.util.ArrayList;

public interface IConsoleService extends IService {
    ArrayList<Console> getAllConsoles();

    ArrayList<Console> getAvailableConsoles();

    Console getById(String id);

    boolean idExists(String id);

    void addConsole(Console console);

    void removeConsole(Console console);

    void modifyConsole(Console console, String newName, boolean inForRepair);

    void rentConsole(Console console) throws Exception;

    void returnConsole(Console console);
}
