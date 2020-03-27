package GameShop.java.models;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Order {
    private static final int MAX_RENTAL_GAMES = 3;

    protected LocalDate dateRented;
    protected LocalDate dateDue;
    protected Customer customer;
    protected Console console;
    protected ArrayList<Game> games = new ArrayList<>();

    protected Order() {}

    public Order(LocalDate dateRented, Customer customer, ArrayList<Game> games) {
        this.dateRented = dateRented;
        this.dateDue = dateRented.plusMonths(1);
        this.customer = customer;
        this.games = games;
    }

    public Order(LocalDate dateRented, Customer customer, ArrayList<Game> games, Console console) {
        this.dateRented = dateRented;
        this.dateDue = dateRented.plusMonths(1);
        this.customer = customer;
        this.games = games;
        this.console = console;
    }

    public static int getMaxGames() { return MAX_RENTAL_GAMES; }

    public double calculateCost() {
        double cost = 0;
        for(Game game : games) {
            cost += game.getCost();
        }
        if (console != null) {
            cost += console.getCost();
        }
        return cost;
    }

    public void addGame(Game game) {
        if (!games.contains(game) && games.size() < getMaxGames()) {
            games.add(game);
        }
    }

    public void removeGame(Game game) {
        games.remove(game);
    }

    public void removeGame(ArrayList<Game> games) {
        this.games.removeAll(games);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public LocalDate getDateRented() {
        return dateRented;
    }

    public void setDateRented(LocalDate dateRented) {
        this.dateRented = dateRented;
    }

    public LocalDate getDateDue() { return dateDue; }

    public void setDateDue(LocalDate due) { this.dateDue = due; }
}
