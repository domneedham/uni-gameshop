package GameShop.java.models;

import java.util.ArrayList;

public class Basket {
    private static int MAX_GAMES = 3;
    private static Customer customer;
    private static boolean consoleRequired = false;
    private static Console console;
    private static ArrayList<Game> games = new ArrayList<>();

    public static boolean isBasketPopulated() {
        if (customer != null && console != null) {
            return true;
        } else {
            return false;
        }
    }

    public static Customer getCustomer() {
        return customer;
    }

    public static boolean isConsoleRequired() {
        return consoleRequired;
    }

    public static Console getConsole() {
        return console;
    }

    public static ArrayList<Game> getGames() {
        return games;
    }

    public static void setCustomer(Customer customer) {
        Basket.customer = customer;
    }

    public static void setConsoleRequired(boolean required) {
        Basket.consoleRequired = required;
    }

    public static void setConsole(Console console) {
        Basket.console = console;
    }

    public static void addGame(Game game) {
        if (!Basket.games.contains(game) && Basket.games.size() < MAX_GAMES) {
            Basket.games.add(game);
        }
    }

    public static void removeGame(Game game) {
        Basket.games.remove(game);
    }

    public static void clearBasket() {
        Basket.customer = null;
        Basket.console = null;
        Basket.games.clear();
    }

    public static boolean gameInBasket(Game game) {
        return Basket.games.contains(game);
    }

    public static void clearGames() {
        Basket.games.clear();
    }

    public static String logBasket() {
        return console.toString() + "\n" + customer.toString() + "\n" + games.toString() + "\n" + consoleRequired;
    }
}
