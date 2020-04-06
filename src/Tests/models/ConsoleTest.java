package Tests.models;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.models.Rental;
import Tests.TestData;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

class ConsoleTest {
    final TestData testData = new TestData();
    final Console console1 = testData.standardConsole1;
    final Console console2 = testData.standardConsole2;
    final Console console3 = testData.nonStandardConsole1;
    final Game game1 = testData.consoleOneGame1;

    @Test
    void idIsDifferentOnEachConsole() {
        Assertions.assertNotEquals(this.console1.getId(), this.console2.getId());
    }

    @Test
    void consoleIsNotAvailableIfInRepair() {
        Assertions.assertTrue(this.console3.isInForRepair());
        Assertions.assertFalse(this.console3.isAvailable());
    }

    @Test
    void consoleIsNotAvailableIfItIsRented() {
        try {
            ArrayList<Game> games = new ArrayList<>();
            games.add(this.game1);
            Customer customer = new Customer("Dom", "Needham", "email", "01");
            Rental rental = Rental.createWithConsole(LocalDate.now(), customer, games, this.console1);
        } catch (Error e) {
            // do not care about the error
        } finally {
            Assertions.assertTrue(this.console1.isCurrentlyRented());
            Assertions.assertFalse(this.console1.isAvailable());
        }
    }

    @Test
    void consoleCanNotBeRentedIfItIsInForRepair() {
        try {
            ArrayList<Game> games = new ArrayList<>();
            games.add(this.game1);
            Customer customer = new Customer("Dom", "Needham", "email", "01");
            Rental rental = Rental.createWithConsole(LocalDate.now(), customer, games, this.console3);
        } catch (Error e) {
            // do not care about the error
        } finally {
            Assertions.assertFalse(this.console3.isCurrentlyRented());
            Assertions.assertFalse(this.console3.isAvailable());
        }
    }
}