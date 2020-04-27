package GameShop.java.services;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.repositories.BasketRepository;
import GameShop.java.services.interfaces.IBasketService;

import java.time.LocalDate;
import java.util.ArrayList;

public class BasketService implements IBasketService {
    private static final BasketRepository repo = new BasketRepository();
    private static final RentalService rentalService = new RentalService();

    @Override
    public boolean isBasketPopulated() {
        return repo.getBasket().isBasketPopulated();
    }

    @Override
    public Customer getCustomer() {
        return repo.getBasket().getCustomer();
    }

    @Override
    public boolean isConsoleRequired() {
        return repo.getBasket().isConsoleRequired();
    }

    @Override
    public Console getConsole() {
        return repo.getBasket().getConsole();
    }

    @Override
    public ArrayList<Game> getGames() {
        return repo.getBasket().getGames();
    }

    @Override
    public LocalDate getDate() {
        return repo.getBasket().getDateRented();
    }

    @Override
    public void setCustomer(Customer customer) {
        repo.getBasket().setCustomer(customer);
    }

    @Override
    public void requireConsole(Console console) throws Exception {
        if (!console.isAvailable()) {
            throw new Exception("Console is not available for rental");
        }
        repo.getBasket().setConsoleRequired(true);
        repo.getBasket().setConsole(console);
    }

    @Override
    public void unrequireConsole() {
        repo.getBasket().setConsoleRequired(false);
        // clear console from basket
        repo.getBasket().setConsole(null);
    }

    @Override
    public void setDate(LocalDate date) {
        repo.getBasket().setDateRented(date);
    }

    @Override
    public void addGame(Game game) {
        repo.getBasket().addGame(game);
    }

    @Override
    public void removeGame(Game game) {
        repo.getBasket().removeGame(game);
    }

    @Override
    public void removeGame(ArrayList<Game> games) {
        repo.getBasket().removeGame(games);
    }

    @Override
    public void clearBasket() {
        repo.getBasket().clearBasket();
    }

    @Override
    public boolean gameInBasket(Game game) {
        return repo.getBasket().gameInBasket(game);
    }

    @Override
    public void clearGames() {
        repo.getBasket().clearGames();
    }

    @Override
    public boolean isMaxGamesInBasket() {
        return repo.getBasket().isMaxGamesInBasket();
    }

    @Override
    public void submitBasket() throws Error {
        // create copy of games so that when basket is cleared
        // does not clear games in rental
        ArrayList<Game> gamesCopy = new ArrayList<>(getGames());
        if (isConsoleRequired()) {
            rentalService.createRentalWithConsole(getDate(), getCustomer(), gamesCopy, getConsole());
        } else {
            rentalService.createRental(getDate(), getCustomer(), gamesCopy);
        }
        clearBasket();
    }

    @Override
    public double calculateCost() {
        return repo.getBasket().calculateCost();
    }
}
