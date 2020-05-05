package GameShop.java.services;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.models.Rental;
import GameShop.java.repositories.RentalRepository;
import GameShop.java.services.interfaces.IRentalService;

import java.time.LocalDate;
import java.util.ArrayList;

public class RentalService implements IRentalService {
    protected static final RentalRepository repo = new RentalRepository();
    private static final GameService gameService = new GameService();
    private static final ConsoleService consoleService = new ConsoleService();

    @Override
    public Rental createRentalWithConsole(LocalDate date, Customer customer, ArrayList<Game> games, Console console) throws Exception {
        Rental rental = Rental.createWithConsole(date, customer, games, console);
        repo.addRental(rental);

        markGamesAsUnavailable(games);
        markConsoleAsUnavailable(console);

        return rental;
    }

    @Override
    public Rental createRental(LocalDate date, Customer customer, ArrayList<Game> games) throws Exception {
        Rental rental = Rental.createWithoutConsole(date, customer, games);
        repo.addRental(rental);

        markGamesAsUnavailable(games);

        return rental;
    }

    @Override
    public ArrayList<Rental> getRentals() { return repo.getAllRentals(); }

    @Override
    public Rental getRentalById(String id) {
        return repo.getById(id);
    }

    @Override
    public ArrayList<Rental> getRentalsForCustomer(Customer customer) { return repo.getRentalsForCustomer(customer); }

    @Override
    public void returnRental(Rental rental) {
        repo.returnRental(rental);
        markGamesAsAvailable(rental.getGames());
        if (rental.getConsole() != null) {
            markConsoleAsAvailable(rental.getConsole());
        }
    }

    private void markGamesAsUnavailable(ArrayList<Game> games) throws Exception {
        for (Game game: games) {
            gameService.rentGame(game);
        }
    }

    private void markConsoleAsUnavailable(Console console) throws Exception {
        consoleService.rentConsole(console);
    }

    private void markGamesAsAvailable(ArrayList<Game> games) {
        for (Game game: games) {
            gameService.returnGame(game);
        }
    }

    private void markConsoleAsAvailable(Console console) {
        consoleService.returnConsole(console);
    }
}
