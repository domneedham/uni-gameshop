package Tests.models;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.models.Rental;
import Tests.TestData;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

class GameTest {
    TestData testData = new TestData();

    Console console = testData.standardConsole1;
    Game game1 = testData.consoleOneGame1;
    Game game2 = testData.consoleOneGame2;
    Game game3 = testData.consoleOneRepairGame1;

    @Test
    void idIsDifferentOnEachGame() {
        Assertions.assertNotEquals(this.game1.getId(), this.game2.getId());
    }

    @Test
    void gameIsNotAvailableIfInRepair() {
        Assertions.assertTrue(this.game3.isInForRepair());
        Assertions.assertFalse(this.game3.isAvailable());
    }

    @Test
    void gameIsNotAvailableIfItIsRented() {
        try {
            ArrayList<Game> games = new ArrayList<>();
            games.add(this.game1);
            Customer customer = new Customer("Dom", "Needham", "email", "01");
            Rental rental = new Rental(LocalDate.now(), customer, this.console, games);

        } catch (Exception e) {
            // ignore
        } finally {
            Assertions.assertTrue(this.game1.isCurrentlyRented());
            Assertions.assertFalse(this.game1.isAvailable());
        }
    }

    @Test
    void gameCanNotBeRentedIfItIsInForRepair() {
        try {
            ArrayList<Game> games = new ArrayList<>();
            games.add(this.game3);
            Customer customer = new Customer("Dom", "Needham", "email", "01");
            Rental rental = new Rental(LocalDate.now(), customer, this.console, games);
        } catch (Error e) {
            // do not care about the error
        } finally {
            Assertions.assertFalse(this.game3.isCurrentlyRented());
            Assertions.assertFalse(this.game3.isAvailable());
        }
    }
}