package GameShop.java.services;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.models.Rental;
import GameShop.java.repositories.RentalRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class RentalService {
    protected static final RentalRepository repo = new RentalRepository();

    public static void createRentalWithConsole(LocalDate date, Customer customer, Console console, ArrayList<Game> games) throws Error {
        Rental rental = Rental.withConsole(date, customer, games, console);
        repo.addRental(rental);
    }

    public static void createRental(LocalDate date, Customer customer, ArrayList<Game> games) throws Error {
        Rental rental = Rental.withoutConsole(date, customer, games);
        repo.addRental(rental);
    }

    public static ArrayList<Rental> getRentals() { return repo.getAllRentals(); }

    public static ArrayList<Rental> getRentalsForCustomer(Customer customer) { return repo.getRentalsForCustomer(customer); }
}
