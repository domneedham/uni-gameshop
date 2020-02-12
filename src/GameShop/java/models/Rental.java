package GameShop.java.models;

import java.util.ArrayList;
import java.util.Date;

public class Rental {
    private final int MAX_RENTAL_GAMES = 3;
    private final int MAX_RENTAL_CONSOLES = 1;
    private int consolesRented = 0;
    private int gamesRented = 0;

    private static int idSeed = 1000;
    private int id;
    private Date dateRented;
    private Customer customer;
    private ArrayList<String> rentalItems = new ArrayList();

    public Rental(Date dateRented, Customer customer) {
        id = idSeed;
        this.dateRented = dateRented;
        this.customer = customer;
        idSeed++;
    }

    public int getId() {
        return id;
    }

    public Date getDateRented() {
        return dateRented;
    }

    public void setDateRented(Date dateRented) {
        this.dateRented = dateRented;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int[] getRentalItems() {
        return new int[rentalItems.size()];
    }

    public void addGameToRental(Game game) {
        if (gamesRented < MAX_RENTAL_GAMES && !rentalItems.contains(game.getId())) {
            rentalItems.add(game.getId());
            gamesRented++;
        }
    }

    public void addConsoleToRental(Console console) {
        if (consolesRented < MAX_RENTAL_CONSOLES && !rentalItems.contains(console.getId())) {
            rentalItems.add(console.getId());
            consolesRented++;
        }
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", dateRented=" + dateRented +
                ", customer=" + customer +
                ", rentalItems=" + rentalItems +
                ", consolesRented=" + consolesRented +
                ", gamesRented=" + gamesRented +
                '}';
    }
}
