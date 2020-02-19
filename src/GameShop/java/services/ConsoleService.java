package GameShop.java.services;

import GameShop.java.models.Console;
import GameShop.java.repositories.ConsoleRepository;

import java.util.ArrayList;

public class ConsoleService {
    private static ConsoleRepository repo = new ConsoleRepository();

    public static ArrayList<Console> getAllConsoles() { return repo.getAllConsoles(); }

    public static ArrayList<Console> getAvailableConsoles() { return repo.getAvailableConsoles(); }

    public static Console getById(String id) { return repo.getById(id); }

    public static boolean idExists(String id) { return repo.getById(id) != null; }

    public static void addConsole(Console console) { repo.addConsole(console); }

    public static void removeConsole(Console console) { repo.removeConsole(console); }

    public static void modifyConsole(Console console) { repo.modifyConsole(console); }

    public static String getId(Console console) { return repo.getFXMLId(console); }
    public static String getName(Console console) { return repo.getFXMLName(console); }
    public static String getForm(Console console) { return repo.getFXMLForm(console); }
    public static String getBit(Console console) { return repo.getFXMLBit(console); }
    public static String getAvailable(Console console) { return repo.getFXMLAvailable(console); }
    public static String getFormattedCost(Console console) { return repo.getFXMLFormattedCost(console); }
}
