package GameShop.java.services;

import GameShop.java.models.*;
import GameShop.java.models.enums.ConsoleForm;

import java.time.LocalDate;
import java.util.ArrayList;

public class StateService {
    // Create ArrayLists to hold some fake data for application
    private final static ArrayList<Game> games = new ArrayList<>();
    private final static ArrayList<Console> consoles = new ArrayList<>();
    private final static ArrayList<Customer> customers = new ArrayList<>();
    private final static ArrayList<Rental> rentals = new ArrayList<>();
    private final static Basket basket = new Basket();

    // On initialisation, populate lists with dummy data
    // Create consoles first, so games can use consoles
    public StateService(){
        populateConsoles();
        populateGames();
        populateCustomers();
        populateRentals();
    }

    public ArrayList<Game> getGames(){
        return games;
    }

    public ArrayList<Console> getConsoles(){
        return consoles;
    }

    public ArrayList<Customer> getCustomers() { return customers; }

    public ArrayList<Rental> getRentals() { return rentals; }

    public Basket getBasket() { return basket; }

    // create dummy objects for consoles, like reading from a DB
    private void populateConsoles() {
        String[] names = {"Xbox", "Nintendo DS", "Playstation 1"};
        ConsoleForm[] forms = {ConsoleForm.STANDARD, ConsoleForm.HANDHELD, ConsoleForm.STANDARD};
        double[] costs = { 15, 10, 15};
        int[] bits = {256, 128, 128};
        boolean[] repair = {false, false, true};

        for(int i = 0; i < names.length; i++){
            Console con = new Console(names[i], forms[i], costs[i], bits[i], repair[i]);
            consoles.add(con);
        }
    }

    // create dummy objects for games, like reading from a DB
    private void populateGames() {
        String[] names = {"Just Dance", "Animal Crossing", "Italian Job"};
        double[] costs = {5, 2.5, 4};
        boolean[] repair = {false, false, true};

        for(int i = 0; i < names.length; i++){
            Game g = new Game(names[i], consoles.get(i), costs[i], repair[i]);
            games.add(g);
        }
        Game newGame = new Game("Fifa 98", consoles.get(0), 5, false);
        Game newGame2 = new Game("Fifa 99", consoles.get(0), 5, false);
        Game newGame3 = new Game("Fifa 00", consoles.get(0), 5, false);
        games.add(newGame);
        games.add(newGame2);
        games.add(newGame3);
    }

    // create dummy objects for customers, like reading from a DB
    private void populateCustomers() {
        String[] firstNames = {"Patrick", "Ferb", "Norville" };
        String[] surnames = {"Star", "Fletcher", "Rogers"};
        String[] emails = {"pat@nickelodeon.com", "ferb@disney.com", "shaggy@hanna-barbera.com"};
        String[] numbers = {"012345", "678901", "234567"};

        for (int i = 0; i < firstNames.length; i++) {
            Customer c = new Customer(firstNames[i], surnames[i], emails[i], numbers[i]);
            customers.add(c);
        }
    }

    // create dummy objects for rentals, like reading from a DB
    private void populateRentals() {
        try {
            ArrayList<Game> games1 = new ArrayList<>();
            games1.add(games.get(4));
            games1.add(games.get(5));
            Rental r1 = Rental.createWithConsole(LocalDate.of(2020, 3, 3), customers.get(0), games1, consoles.get(0));
            rentals.add(r1);

            // make games and console rented
            games.get(4).rentItem();
            games.get(5).rentItem();
            consoles.get(0).rentItem();
        } catch (Exception e) {
            System.out.println("Unable to make rentals");
            System.exit(0);
        }
    }

    @Override
    public String toString() {
        return "Games: " + games +
                "\nConsoles: " + consoles +
                "\nRentals: " + rentals +
                "\nBasket: " + basket;
    }

}

