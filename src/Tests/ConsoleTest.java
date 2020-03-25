package Tests;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.models.Rental;
import GameShop.java.models.enums.ConsoleForm;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

class ConsoleTest {
    Console console1;
    Console console2;
    Console console3;
    Game game1;

    @BeforeEach
    void setUp() {
        this.console1 = new Console("Test console 1", ConsoleForm.STANDARD, 15, 8, false);
        this.console2 = new Console("Test console 2", ConsoleForm.STANDARD, 15, 8, false);
        this.console3 = new Console("Test console 3", ConsoleForm.STANDARD, 15, 8, true);
        this.game1 = new Game("Test game", this.console1, 10, false);
    }

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
            Rental rental = new Rental(LocalDate.now(), customer, this.console1, games);

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
            Rental rental = new Rental(LocalDate.now(), customer, this.console3, games);
        } catch (Error e) {
            // do not care about the error
        } finally {
            Assertions.assertFalse(this.console3.isCurrentlyRented());
            Assertions.assertFalse(this.console3.isAvailable());
        }
    }
}