package GameShop.java.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Rental extends Order {
    private static int idSeed = 1000;
    private final int id;
    private boolean returned;

    private Rental(LocalDate dateRented, Customer customer, ArrayList<Game> games) throws Error {
        super(dateRented, customer, games);
        id = idSeed;
        idSeed++;

        markGamesAsUnavailable();
    }

    private Rental(LocalDate dateRented, Customer customer, ArrayList<Game> games, Console console) throws Error {
        super(dateRented, customer, games, console);
        id = idSeed;
        idSeed++;

        markGamesAsUnavailable();
        markConsoleAsUnavailable();
    }

    public static Rental createWithConsole(LocalDate dateRented, Customer customer, ArrayList<Game> games, Console console) {
        return new Rental(dateRented, customer, games, console);
    }

    public static Rental createWithoutConsole(LocalDate dateRented, Customer customer, ArrayList<Game> games) {
        return new Rental(dateRented, customer, games);
    }

    public int getId() {
        return id;
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
