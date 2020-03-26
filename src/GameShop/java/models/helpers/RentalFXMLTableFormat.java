package GameShop.java.models.helpers;

import GameShop.java.models.Rental;

public class RentalFXMLTableFormat extends FXMLTableFormat {
    public static String formatConsoleName(Rental rental) {
        if (rental.getConsole() != null) {
            return rental.getConsole().getName();
        }
        return "Console Not Rented";
    }
}
