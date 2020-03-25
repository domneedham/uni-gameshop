package Tests;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.models.Rental;
import GameShop.java.models.enums.ConsoleForm;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

class GameTest {
    Console console;
    Game game1;
    Game game2;
    Game game3;

    @BeforeEach
    void setUp() {
        this.console = new Console("Test console", ConsoleForm.STANDARD, 15, 8, false);
        this.game1 = new Game("Test game", this.console, 10, false);
        this.game2 = new Game("Test game 2", this.console, 10, false);
        this.game3 = new Game("Test game 3", this.console, 10, true);
    }

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
            new AssertionError("Unable to make rental");
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