package GameShop.java.services;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.repositories.BasketRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class BasketService {
    private static BasketRepository repo = new BasketRepository();

    public static boolean isBasketPopulated() {
        if (repo.getCustomer() != null && repo.getConsole() != null) {
            return true;
        } else {
            return false;
        }
    }

    public static Customer getCustomer() {
        return repo.getCustomer();
    }

    public static boolean isConsoleRequired() {
        return repo.isConsoleRequired();
    }

    public static Console getConsole() {
        return repo.getConsole();
    }

    public static ArrayList<Game> getGames() {
        return repo.getGames();
    }

    public static LocalDate getDate() { return repo.getDate(); }

    public static void setCustomer(Customer customer) { repo.setCustomer(customer); }

    public static void setConsoleRequired(boolean required) {
        repo.setConsoleRequired(required);
    }

    public static void setConsole(Console console) {
        repo.setConsole(console);
    }

    public static void setDate(LocalDate date) { repo.setDate(date); }

    public static void addGame(Game game) { repo.addGame(game); }

    public static void removeGame(Game game) {
        repo.removeGame(game);
    }

    public static void removeGame(ArrayList<Game> games) { repo.removeGame(games);}

    public static void clearBasket() { repo.clearBasket(); }

    public static boolean gameInBasket(Game game) { return repo.gameInBasket(game); }

    public static void clearGames() { repo.clearGames(); }

    public static boolean maxGamesInBasket() { return repo.maxGamesInBasket(); }

    public static void submitBasket() {
        repo.submitBasket();
        clearBasket();
    }
}
