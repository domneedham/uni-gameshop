package GameShop.java.models;

import GameShop.java.services.RentalService;

import java.time.LocalDate;
import java.util.ArrayList;

public class Basket {
    private int MAX_GAMES = 3;
    private Customer customer;
    private boolean consoleRequired = false;
    private Console console;
    private ArrayList<Game> games = new ArrayList<>();
    private LocalDate date;

    public boolean isBasketPopulated() {
        if (customer != null && console != null) {
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
    }

    public boolean gameInBasket(Game game) { return games.contains(game); }

    public boolean maxGamesInBasket() { return games.size() >= MAX_GAMES; }

    public void clearGames() {
        games.clear();
    }

    public void submitBasket() {
        RentalService.createRental(date, customer, console, new ArrayList<>(games));
        clearBasket();
    }
}
