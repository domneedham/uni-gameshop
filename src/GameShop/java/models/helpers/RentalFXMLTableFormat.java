package GameShop.java.models.helpers;

import GameShop.java.models.Console;

public class RentalFXMLTableFormat {
    public static String formatConsoleName(Console console) {
        if (console != null) {
            return console.getName();
        }
        return "Console Not Rented";
    }
}
