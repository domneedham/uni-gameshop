package GameShop.java.services;

import GameShop.java.models.Rental;

public class RentalFXMLTableService extends RentalService {
    public static String getCustomer(Rental rental) { return rental.getCustomer().toString(); }
    public static String getDateRented(Rental rental) { return rental.getDateRented().toString(); }
    public static String getDateDue(Rental rental) { return rental.getDateDue().toString(); }
    public static String getConsole(Rental rental) {
        if (rental.getConsole() != null) {
            return rental.getConsole().toString();
        }
        return "Console not rented";
    }
    public static String getGames(Rental rental) { return rental.getGames().toString(); }
}
