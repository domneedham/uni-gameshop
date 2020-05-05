package GameShop.java.services.interfaces;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.models.Rental;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IRentalService extends IService {
    Rental createRentalWithConsole(LocalDate date, Customer customer, ArrayList<Game> games, Console console) throws Error, Exception;

    Rental createRental(LocalDate date, Customer customer, ArrayList<Game> games) throws Error, Exception;

    ArrayList<Rental> getRentals();

    Rental getRentalById(String id);

    ArrayList<Rental> getRentalsForCustomer(Customer customer);

    void returnRental(Rental rental);
}
