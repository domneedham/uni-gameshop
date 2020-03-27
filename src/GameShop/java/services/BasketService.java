package GameShop.java.services;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.repositories.BasketRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class BasketService {
    private static final BasketRepository repo = new BasketRepository();

    public static boolean isBasketPopulated() {
        return repo.getBasket().isBasketPopulated();
    }

    public static Customer getCustomer() {
        return repo.getBasket().getCustomer();
    }

    public static boolean isConsoleRequired() {
        return repo.getBasket().isConsoleRequired();
    }

    public static Console getConsole() {
        return repo.getBasket().getConsole();
    }

    public static ArrayList<Game> getGames() {
        return repo.getBasket().getGames();
    }

    public static LocalDate getDate() {
        return repo.getBasket().getDate();
    }

    public static void setCustomer(Customer customer) {
        repo.getBasket().setCustomer(customer);
    }

    public static void requireConsole(Console console) throws Exception {
        if (!console.isAvailable()) {
            throw new Exception("Console is not available for rental");
        }
        repo.getBasket().setConsoleRequired(true);
        repo.getBasket().setConsole(console);
    }

    public static void unrequireConsole() {
        repo.getBasket().setConsoleRequired(false);
        // clear console from basket
        repo.getBasket().setConsole(null);
    }

    public static void setDate(LocalDate date) {
        repo.getBasket().setDate(date);
    }

    public static void addGame(Game game) {
        repo.getBasket().addGame(game);
    }

    public static void removeGame(Game game) {
        repo.getBasket().removeGame(game);
    }

    public static void removeGame(ArrayList<Game> games) {
        repo.getBasket().removeGame(games);
    }

    public static void clearBasket() {
        repo.getBasket().clearBasket();
    }

    public static boolean gameInBasket(Game game) {
        return repo.getBasket().gameInBasket(game);
    }

    public static void clearGames() {
        repo.getBasket().clearGames();
    }

    public static boolean isMaxGamesInBasket() {
        return repo.getBasket().isMaxGamesInBasket();
    }

    public static void submitBasket() throws Error {
        repo.getBasket().submitBasket();
    }

    public static double calculateCost() {
        double cost = 0;
        for(Game game : getGames()) {
            cost += game.getCost();
        }
        if (isConsoleRequired()) {
            cost += getConsole().getCost();
        }
        return cost;
    }
}
