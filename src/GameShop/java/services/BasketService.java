package GameShop.java.services;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.models.exceptions.ProductRentalException;
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
        repo.setCustomer(customer);
    }

    @Override
    public void requireConsole(Console console) throws Exception {
        if (!console.isAvailable()) {
            throw new ProductRentalException("Console is not available for rental");
        }
        repo.requireConsole(console);
    }

    @Override
    public void unrequireConsole() {
        repo.unrequireConsole();
    }

    @Override
    public void setDate(LocalDate date) {
        repo.setDate(date);
    }

    @Override
    public void addGame(Game game) {
        repo.addGame(game);
    }

    @Override
    public void removeGame(Game game) {
        repo.removeGame(game);
    }

    @Override
    public void removeGame(ArrayList<Game> games) {
        repo.removeGame(games);
    }

    @Override
    public void clearBasket() {
        repo.clearBasket();
    }

    @Override
    public boolean gameInBasket(Game game) {
        return repo.getBasket().gameInBasket(game);
    }

    @Override
    public void clearGames() {
        repo.clearGames();
    }

    @Override
    public boolean isMaxGamesInBasket() {
        return repo.getBasket().isMaxGamesInBasket();
    }

    @Override
    public void submitBasket() throws Exception {
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
