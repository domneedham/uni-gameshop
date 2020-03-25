package GameShop.java.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Rental {
    private static final int MAX_RENTAL_GAMES = 3;

    private static int idSeed = 1000;
    private final int id;
    private LocalDate dateRented;
    private LocalDate dateDue;
    private Customer customer;
    private Console console;
    private ArrayList<Game> games;
    private boolean returned;

    public Rental(LocalDate dateRented, Customer customer, ArrayList<Game> games) throws Error {
        id = idSeed;
        this.dateRented = dateRented;
        this.dateDue = dateRented.plusMonths(1);
        this.customer = customer;
        this.games = games;
        idSeed++;

        markGamesAsUnavailable();
    }

    public Rental(LocalDate dateRented, Customer customer, Console console, ArrayList<Game> games) throws Error {
        id = idSeed;
        this.dateRented = dateRented;
        this.dateDue = dateRented.plusMonths(1);
        this.customer = customer;
        this.console = console;
        this.games = games;
        idSeed++;

        markGamesAsUnavailable();
        markConsoleAsUnavailable();
    }

    public static int getMaxGames() { return MAX_RENTAL_GAMES; }

    public int getId() {
        return id;
    }

    public LocalDate getDateRented() {
        return dateRented;
    }

    public void setDateRented(LocalDate dateRented) {
        this.dateRented = dateRented;
    }

    public LocalDate getDateDue() { return dateDue; }

    public void setDateDue(LocalDate due) { this.dateDue = due; }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Game> getGames() { return games; }

    public void addGameToRental(Game game) {
        if (games.size() < MAX_RENTAL_GAMES) {
            games.add(game);
        }
    }

    public void removeGameFromRental(Game game) {
        games.remove(game);
    }

    public Console getConsole() { return console; }

    public void setConsole(Console console) {
        this.console = console;
    }

    public boolean isReturned() { return returned; }

    public void returnRental() {
        this.returned = true;
        markGamesAsAvailable();
        if (console != null) {
            markConsoleAsAvailable();
        }
    }

    private void markGamesAsUnavailable() throws Error {
        for (Game g: this.games) {
            g.rentItem();
        }
    }

    private void markConsoleAsUnavailable() throws Error {
        this.console.rentItem();
    }

    private void markGamesAsAvailable() {
        for (Game g: this.games) {
            g.returnItem();
        }
    }

    private void markConsoleAsAvailable() {
        this.console.returnItem();
    }

    @Override
    public String toString() {
        return "Rented on: " + dateRented +
                ", Customer: " + customer +
                ", Console: " + console +
                ", Games: " + games +
                ", Returned: " + returned;
    }
}
