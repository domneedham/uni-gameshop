package GameShop.java.services;

import GameShop.java.models.Console;
import GameShop.java.services.ConsoleService;

public class ConsoleFXMLTableService extends ConsoleService {
    public static String getId(Console console) { return repo.getId(console); }
    public static String getName(Console console) { return repo.getName(console); }
    public static String getForm(Console console) {
        // Capitalises first word only instead of all caps like from enum
        var form = repo.getForm(console).toLowerCase();
        return form.substring(0, 1).toUpperCase() + form.substring(1);
    }
    public static String getBit(Console console) { return String.valueOf(repo.getBit(console)); }
    public static String getAvailable(Console console) { return repo.isInForRepair(console) ? "No" : "Yes"; }
    public static String getFormattedCost(Console console) { return String.format("£%.2f", repo.getCost(console)); }

}
