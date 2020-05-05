package Tests.services;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.models.Rental;
import GameShop.java.services.ConsoleService;
import GameShop.java.services.GameService;
import GameShop.java.services.RentalService;
import Tests.TestData;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

class RentalServiceTest {
    final TestData testData = new TestData();

    private Rental rentalWithConsole;
    private Rental rentalWithoutConsole;
    private final LocalDate date = testData.date;
    private final Customer customer1 = testData.customer1;
    private final Customer customer2 = testData.customer2;
    private final ArrayList<Game> gamesForRental1 = testData.consoleOneGamesFullList;
    private final ArrayList<Game> gamesForRental2 = testData.consoleTwoGamesNotFullList;
    private final Console console1 = testData.standardConsole1;
    private Console console2 = testData.standardConsole2;

    @BeforeEach
    void setUp() {
        // clear anything that could be in repo already so data is not mistaken
        RentalService.getRentals().clear();
        // test clear worked on rentals before running other tests
        Assertions.assertEquals(0, RentalService.getRentals().size());
    }

    @Test
    void getRentalsReturnsCorrectSize() {
        Assertions.assertEquals(0, RentalService.getRentals().size());
        RentalService.getRentals().add(this.rentalWithConsole);

        Assertions.assertEquals(1, RentalService.getRentals().size());
    }

    @Test
    void getRentalsForCustomerReturnsCorrectSize() {
        this.rentalWithConsole = Rental.createWithConsole(this.date, this.customer1, this.gamesForRental1, this.console1);
        this.rentalWithoutConsole = Rental.createWithoutConsole(this.date, this.customer2, this.gamesForRental2);
        RentalService.getRentals().add(this.rentalWithConsole);
        RentalService.getRentals().add(this.rentalWithoutConsole);

        Assertions.assertEquals(1, RentalService.getRentalsForCustomer(this.customer1).size());
        Assertions.assertEquals(1, RentalService.getRentalsForCustomer(this.customer2).size());
    }

    @Test
    void createARentalWithConsoleWorks() throws Exception {
        Assertions.assertEquals(0, RentalService.getRentals().size());

        RentalService.createRentalWithConsole(this.date, this.customer1, this.gamesForRental1, this.console1);

        Assertions.assertEquals(1, RentalService.getRentals().size());
        // make sure rental has a console
        Assertions.assertNotNull(RentalService.getRentals().get(0).getConsole());
    }

    @Test
    void createARentalWithoutConsoleWorks() throws Exception {
        Assertions.assertEquals(0, RentalService.getRentals().size());

        RentalService.createRental(this.date, this.customer1, this.gamesForRental1);

        Assertions.assertEquals(1, RentalService.getRentals().size());
        // make sure rental does not have a console
        Assertions.assertNull(RentalService.getRentals().get(0).getConsole());
    }

    @Test
    void eachGameGetsMarkedAsUnavailableOnceRented() throws Exception {
        // add games to repository as games are checked when marking unavailable
        // make sure each game is available before checking rental changes
        for (Game g: this.gamesForRental1) {
            GameService.addGame(g);
            Assertions.assertTrue(g.isAvailable());
        }

        this.rentalWithConsole = RentalService.createRentalWithConsole(this.date, this.customer1, this.gamesForRental1, this.console1);

        for (Game g: this.rentalWithConsole.getGames()) {
            Assertions.assertFalse(g.isAvailable());
        }
    }

    @Test
    void eachGameGetsMarkedAsAvailableOnReturn() throws Exception {
        // add games to repository as games are checked when marking available
        for (Game g: this.gamesForRental2) {
            GameService.addGame(g);
        }

        this.rentalWithConsole = RentalService.createRentalWithConsole(this.date, this.customer1, this.gamesForRental2, this.console1);

        // make sure each game is not available after rental
        for (Game g: this.gamesForRental2) {
            Assertions.assertFalse(g.isAvailable());
        }

        // return items
        RentalService.returnRental(this.rentalWithConsole);

        for (Game g: this.rentalWithConsole.getGames()) {
            Assertions.assertTrue(g.isAvailable());
        }
    }

    @Test
    void consoleGetsMarkedAsUnavailableOnceRented() throws Exception {
        // add console to repository as games are checked when marking unavailable
        ConsoleService.addConsole(this.console1);

        // make sure console is available before checking rental changes
        Assertions.assertTrue(this.console1.isAvailable());

        this.rentalWithConsole = RentalService.createRentalWithConsole(this.date, this.customer1, this.gamesForRental1, this.console1);

        Assertions.assertFalse(this.rentalWithConsole.getConsole().isAvailable());
    }

    @Test
    void consoleGetsMarkedAsAvailableOnReturn() throws Exception {
        // add console to repository as games are checked when marking unavailable
        ConsoleService.addConsole(this.console1);

        this.rentalWithConsole = RentalService.createRentalWithConsole(this.date, this.customer1, this.gamesForRental1, this.console1);

        // make sure console is not available after rental
        Assertions.assertFalse(RentalService.getRentalById(rentalWithConsole.getId()).getConsole().isAvailable());

        // return items
        RentalService.returnRental(this.rentalWithConsole);

        Assertions.assertTrue(RentalService.getRentalById(rentalWithConsole.getId()).getConsole().isAvailable());
    }

}