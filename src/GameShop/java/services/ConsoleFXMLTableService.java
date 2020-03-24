package GameShop.java.services;

import GameShop.java.models.Console;

public class ConsoleFXMLTableService extends ConsoleService {
    public static String getId(Console console) { return console.getId(); }
    public static String getName(Console console) { return console.getName(); }
    public static String getForm(Console console) {
        // Capitalises first word only instead of all caps like from enum
        String form = console.getForm().toString().toLowerCase();
        return form.substring(0, 1).toUpperCase() + form.substring(1);
    }
    public static String getBit(Console console) { return String.valueOf(console.getBit()); }
    public static String getAvailable(Console console) { return console.isAvailable() ? "Yes" : "No"; }
    public static String getFormattedCost(Console console) { return String.format("£%.2f", console.getCost()); }

}
