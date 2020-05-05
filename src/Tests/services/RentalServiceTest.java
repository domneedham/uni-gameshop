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
    final RentalService rentalService = new RentalService();
    final GameService gameService = new GameService();
    final ConsoleService consoleService = new ConsoleService();

    Rental rentalWithConsole;
    Rental rentalWithoutConsole;
    final LocalDate date = testData.date;
    final Customer customer1 = testData.customer1;
    final Customer customer2 = testData.customer2;
    final ArrayList<Game> gamesForRental1 = testData.consoleOneGamesFullList;
    final ArrayList<Game> gamesForRental2 = testData.consoleTwoGamesNotFullList;
    final Console console1 = testData.standardConsole1;
    Console console2 = testData.standardConsole2;

    @BeforeEach
    void setUp() {
        // clear anything that could be in repo already so data is not mistaken
        rentalService.getRentals().clear();
        // test clear worked on rentals before running other tests
        Assertions.assertEquals(0, rentalService.getRentals().size());
    }

    @Test
    void getRentalsReturnsCorrectSize() {
        Assertions.assertEquals(0, rentalService.getRentals().size());
        rentalService.getRentals().add(this.rentalWithConsole);

        Assertions.assertEquals(1, rentalService.getRentals().size());
    }

    @Test
    void getRentalsForCustomerReturnsCorrectSize() {
        this.rentalWithConsole = Rental.createWithConsole(this.date, this.customer1, this.gamesForRental1, this.console1);
        this.rentalWithoutConsole = Rental.createWithoutConsole(this.date, this.customer2, this.gamesForRental2);
        rentalService.getRentals().add(this.rentalWithConsole);
        rentalService.getRentals().add(this.rentalWithoutConsole);

        Assertions.assertEquals(1, rentalService.getRentalsForCustomer(this.customer1).size());
        Assertions.assertEquals(1, rentalService.getRentalsForCustomer(this.customer2).size());
    }

    @Test
    void createARentalWithConsoleWorks() throws Exception {
        Assertions.assertEquals(0, rentalService.getRentals().size());

        rentalService.createRentalWithConsole(this.date, this.customer1, this.gamesForRental1, this.console1);

        Assertions.assertEquals(1, rentalService.getRentals().size());
        // make sure rental has a console
        Assertions.assertNotNull(rentalService.getRentals().get(0).getConsole());
    }

    @Test
    void createARentalWithoutConsoleWorks() throws Exception {
        Assertions.assertEquals(0, rentalService.getRentals().size());

        rentalService.createRental(this.date, this.customer1, this.gamesForRental1);

        Assertions.assertEquals(1, rentalService.getRentals().size());
        // make sure rental does not have a console
        Assertions.assertNull(rentalService.getRentals().get(0).getConsole());
    }

    @Test
    void eachGameGetsMarkedAsUnavailableOnceRented() throws Exception {
        // add games to repository as games are checked when marking unavailable
        // make sure each game is available before checking rental changes
        for (Game g: this.gamesForRental1) {
            gameService.addGame(g);
            Assertions.assertTrue(g.isAvailable());
        }

        this.rentalWithConsole = rentalService.createRentalWithConsole(this.date, this.customer1, this.gamesForRental1, this.console1);

        for (Game g: this.rentalWithConsole.getGames()) {
            Assertions.assertFalse(g.isAvailable());
        }
    }

    @Test
    void eachGameGetsMarkedAsAvailableOnReturn() throws Exception {
        // add games to repository as games are checked when marking available
        for (Game g: this.gamesForRental2) {
            gameService.addGame(g);
        }

        this.rentalWithConsole = rentalService.createRentalWithConsole(this.date, this.customer1, this.gamesForRental2, this.console1);

        // make sure each game is not available after rental
        for (Game g: this.gamesForRental2) {
            Assertions.assertFalse(g.isAvailable());
        }

        // return items
        rentalService.returnRental(this.rentalWithConsole);

        for (Game g: this.rentalWithConsole.getGames()) {
            Assertions.assertTrue(g.isAvailable());
        }
    }

    @Test
    void consoleGetsMarkedAsUnavailableOnceRented() throws Exception {
        // add console to repository as games are checked when marking unavailable
        consoleService.addConsole(this.console1);

        // make sure console is available before checking rental changes
        Assertions.assertTrue(this.console1.isAvailable());

        this.rentalWithConsole = rentalService.createRentalWithConsole(this.date, this.customer1, this.gamesForRental1, this.console1);

        Assertions.assertFalse(this.rentalWithConsole.getConsole().isAvailable());
    }

    @Test
    void consoleGetsMarkedAsAvailableOnReturn() throws Exception {
        // add console to repository as games are checked when marking unavailable
        consoleService.addConsole(this.console1);

        this.rentalWithConsole = rentalService.createRentalWithConsole(this.date, this.customer1, this.gamesForRental1, this.console1);

        // make sure console is not available after rental
        Assertions.assertFalse(rentalService.getRentalById(rentalWithConsole.getId()).getConsole().isAvailable());

        // return items
        rentalService.returnRental(this.rentalWithConsole);

        Assertions.assertTrue(rentalService.getRentalById(rentalWithConsole.getId()).getConsole().isAvailable());
    }

}