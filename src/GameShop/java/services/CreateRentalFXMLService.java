package GameShop.java.services;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;

public class CreateRentalFXMLService {
    public static String formatCustomer(Customer customer) {
        return String.format("%s %s", customer.getForename(), customer.getSurname());
    }

    public static String formatConsole(Console console) {
        return String.format("%s", console.getName());
    }

    public static String formatConsoleRequired(boolean required) {
        return required ? "Yes" : "No";
    }
}
