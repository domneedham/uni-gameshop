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

    public static Rental createRentalWithConsole(LocalDate date, Customer customer, ArrayList<Game> games, Console console ) throws Error {
        Rental rental = Rental.createWithConsole(date, customer, games, console);
        repo.addRental(rental);

        markGamesAsUnavailable(games);
        markConsoleAsUnavailable(console);

        return rental;
    }

    public static Rental createRental(LocalDate date, Customer customer, ArrayList<Game> games) throws Error {
        Rental rental = Rental.createWithoutConsole(date, customer, games);
        repo.addRental(rental);

        markGamesAsUnavailable(games);

        return rental;
    }

    public static ArrayList<Rental> getRentals() { return repo.getAllRentals(); }

    public static Rental getRentalById(String id) {
        return repo.getById(id);
    }

    public static ArrayList<Rental> getRentalsForCustomer(Customer customer) { return repo.getRentalsForCustomer(customer); }

    public static void returnRental(Rental rental) {
        repo.returnRental(rental);
        markGamesAsAvailable(rental.getGames());
        if (rental.getConsole() != null) {
            markConsoleAsAvailable(rental.getConsole());
        }
    }

    private static void markGamesAsUnavailable(ArrayList<Game> games) throws Error {
        for (Game game: games) {
            GameService.rentGame(game);
        }
    }

    private static void markConsoleAsUnavailable(Console console) throws Error {
        ConsoleService.rentConsole(console);
    }

    private static void markGamesAsAvailable(ArrayList<Game> games) {
        for (Game game: games) {
            GameService.returnGame(game);
        }
    }

    private static void markConsoleAsAvailable(Console console) {
        ConsoleService.returnConsole(console);
    }
}
