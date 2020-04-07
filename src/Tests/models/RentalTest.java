package Tests.models;

import GameShop.java.models.*;
import Tests.TestData;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

class RentalTest {
    final TestData testData = new TestData();

    private Rental rental;
    private final LocalDate date = testData.date;
    private final Customer customer = testData.customer1;
    private final ArrayList<Game> games = testData.consoleOneGamesFullList;
    private final Console console = testData.standardConsole1;

    @Test
    @Description("Max games is 3")
    void getMaxGames() {
        Assertions.assertEquals(3, Rental.getMaxGames());
    }

    @Test
    void creatingARentalWithAConsoleWorks() {
        this.rental = Rental.createWithConsole(this.date, this.customer, this.games, this.console);

        Assertions.assertEquals(this.date, this.rental.getDateRented());
        Assertions.assertEquals(this.customer, this.rental.getCustomer());
        Assertions.assertEquals(this.console, this.rental.getConsole());
        Assertions.assertEquals(this.games, this.rental.getGames());
    }

    @Test
    void creatingARentalWithNoConsoleWorks() {
        this.rental = Rental.createWithoutConsole(this.date, this.customer, this.games);

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
        this.rental = Rental.createWithConsole(this.date, this.customer, this.games, this.console);
        // check there are three games in test first
        Assertions.assertEquals(3, this.rental.getGames().size());

        Game game2 = testData.consoleTwoGame2;

        this.rental.addGame(game2);

        Assertions.assertEquals(3, this.rental.getGames().size());
    }
}