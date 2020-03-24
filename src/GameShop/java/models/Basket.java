package GameShop.java.models;

import GameShop.java.services.RentalService;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Basket {
    private int MAX_GAMES = Rental.getMaxGames();
    private Customer customer;
    private boolean consoleRequired = false;
    private Console console;
    private ArrayList<Game> games = new ArrayList<>();
    private LocalDate date;

    public boolean isBasketPopulated() {
        if (customer != null && console != null & date != null && (consoleRequired || !consoleRequired && games.size() > 0)) {
            return true;
        } else {
            return false;
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean isConsoleRequired() {
        return consoleRequired;
    }

    public Console getConsole() {
        return console;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public LocalDate getDate() { return date; }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setConsoleRequired(boolean required) {
        this.consoleRequired = required;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public void setDate(LocalDate date) { this.date = date; }

    public void addGame(Game game) {
        if (!games.contains(game) && games.size() < MAX_GAMES) {
            games.add(game);
        }
    }

    public void removeGame(Game game) {
        games.remove(game);
    }

    public void removeGame(ArrayList<Game> games) { this.games.removeAll(games); }

    public void clearBasket() {
        customer = null;
        console = null;
        games.clear();
        date = null;
        consoleRequired = false;
    }

    public boolean gameInBasket(Game game) { return games.contains(game); }

    public boolean maxGamesInBasket() { return games.size() >= MAX_GAMES; }

    public int numberOfGamesInBasket() { return games.size(); }

    public void clearGames() {
        games.clear();
    }

    public void submitBasket() {
        // create copy of games so that when basket is cleared
        // does not clear games in rental
        ArrayList<Game> gamesCopy = new ArrayList<>(games);
        if (consoleRequired) {
            RentalService.createRentalWithConsole(date, customer, console, gamesCopy);
        } else {
            RentalService.createRental(date, customer, gamesCopy);
        }
        clearBasket();
    }

    @Override
    public String toString() {
        return "Customer: " + customer +
                ", Console Required: " + consoleRequired +
                ", Console: " + console +
                ", Games: " + games +
                ", Date: " + date;
    }
}
