package Tests.models;

import GameShop.java.models.*;
import GameShop.java.models.enums.ConsoleForm;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

class RentalTest {
    private Rental rental;
    private LocalDate date;
    private Customer customer;
    private ArrayList<Game> games = new ArrayList<>();
    private Console console;

    @BeforeEach
    void setUp() {
        this.date = LocalDate.now();
        this.customer = new Customer("Dom", "Needham", "dominic.needham@bt.com", "01773");
        this.console = new Console("Test Console", ConsoleForm.STANDARD, 15.0, 8, false);
        Game game1 = new Game("Test game 1", console, 10.0, false);
        Game game2 = new Game("Test game 2", console, 10.0, false);
        Game game3 = new Game("Test game 3", console, 10.0, false);
        this.games.add(game1);
        this.games.add(game2);
        this.games.add(game3);
    }

    @Test
    @Description("Max games is 3")
    void getMaxGames() {
        Assertions.assertEquals(3, Rental.getMaxGames());
    }

    @Test
    void creatingARentalWorks() {
        this.rental = new Rental(this.date, this.customer, this.console, this.games);

        Assertions.assertEquals(this.date, this.rental.getDateRented());
        Assertions.assertEquals(this.customer, this.rental.getCustomer());
        Assertions.assertEquals(this.console, this.rental.getConsole());
        Assertions.assertEquals(this.games, this.rental.getGames());
    }

    @Test
    void creatingARentalWithNoConsoleWorks() {
        this.rental = new Rental(this.date, this.customer, this.games);

        Assertions.assertEquals(this.date, this.rental.getDateRented());
        Assertions.assertEquals(this.customer, this.rental.getCustomer());
        Assertions.assertNull(this.rental.getConsole());
        Assertions.assertEquals(this.games, this.rental.getGames());
    }

    @Test
    void maxGamesReturnsThree() {
        Assertions.assertEquals(3, Rental.getMaxGames());
    }

    @Test
    void canNotAddMoreThanThreeGamesToARental() {
        this.rental = new Rental(this.date, this.customer, this.console, this.games);

        // check there are three games in test first
        Assertions.assertEquals(3, this.rental.getGames().size());

        Game game4 = new Game("Test game 4", console, 10.0, false);
        this.rental.addGameToRental(game4);

        Assertions.assertEquals(3, this.rental.getGames().size());
    }

    @Test
    void eachGameGetsMarkedAsUnavailableOnceRented() {
        // make sure each game is available before checking rental changes
        for (Game g: this.games) {
            Assertions.assertTrue(g.isAvailable());
        }

        this.rental = new Rental(this.date, this.customer, this.console, this.games);

        for (Game g: this.rental.getGames()) {
            Assertions.assertFalse(g.isAvailable());
        }
    }

    @Test
    void eachGameGetsMarkedAsAvailableOnReturn() {
        this.rental = new Rental(this.date, this.customer, this.console, this.games);
        // make sure each game is not available after rental
        for (Game g: this.rental.getGames()) {
            Assertions.assertFalse(g.isAvailable());
        }

        // return items
        this.rental.returnRental();

        for (Game g: this.rental.getGames()) {
            Assertions.assertTrue(g.isAvailable());
        }
    }

    @Test
    void consoleGetsMarkedAsUnavailableOnceRented() {
        // make sure console is available before checking rental changes
        Assertions.assertTrue(this.console.isAvailable());

        this.rental = new Rental(this.date, this.customer, this.console, this.games);

        Assertions.assertFalse(this.rental.getConsole().isAvailable());
    }

    @Test
    void consoleGetsMarkedAsAvailableOnReturn() {
        this.rental = new Rental(this.date, this.customer, this.console, this.games);
        // make sure console is not available after rental
        Assertions.assertFalse(this.console.isAvailable());

        // return items
        this.rental.returnRental();

        Assertions.assertTrue(this.rental.getConsole().isAvailable());
    }
}