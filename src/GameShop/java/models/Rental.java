package GameShop.java.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Rental extends Order {
    private final String id;
    private boolean returned;

    private Rental(LocalDate dateRented, Customer customer, ArrayList<Game> games) throws Error {
        super(dateRented, customer, games);
        id = UUID.randomUUID().toString();
    }

    private Rental(LocalDate dateRented, Customer customer, ArrayList<Game> games, Console console) throws Error {
        super(dateRented, customer, games, console);
        id = UUID.randomUUID().toString();
    }

    public static Rental createWithConsole(LocalDate dateRented, Customer customer, ArrayList<Game> games, Console console) {
        return new Rental(dateRented, customer, games, console);
    }

    public static Rental createWithoutConsole(LocalDate dateRented, Customer customer, ArrayList<Game> games) {
        return new Rental(dateRented, customer, games);
    }

    public String getId() {
        return id;
    }

    public boolean isReturned() { return returned; }

    public void setIsReturned(boolean status) {
        returned = status;
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
