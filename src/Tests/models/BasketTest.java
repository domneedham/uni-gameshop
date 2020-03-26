package Tests.models;

import GameShop.java.models.Basket;
import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import Tests.TestData;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

class BasketTest {
    final TestData testData = new TestData();

    private Basket basket;
    private final LocalDate date = testData.date;
    private final Customer customer = testData.customer1;
    private final Console console = testData.standardConsole1;
    private final Game game1 = testData.consoleOneGame1;
    private final Game game2 = testData.consoleOneGame2;
    private final Game game3 = testData.consoleOneRepairGame1;

    @BeforeEach
    void setUp() {
        this.basket = new Basket();
    }

    @Test
    void basketIsPopulatedWithAllContent() {
        populateBasket();

        Assertions.assertTrue(this.basket.isBasketPopulated());
    }

    @Test
    void basketIsPopulatedWithConsoleNotRequired() {
        this.basket.setConsole(this.console);
        this.basket.setCustomer(this.customer);
        this.basket.setDate(this.date);
        this.basket.addGame(this.game1);

        Assertions.assertTrue(this.basket.isBasketPopulated());
    }

    @Test
    void basketIsPopulatedWithNoGamesIfConsoleIsRequired() {
        this.basket.setConsole(this.console);
        this.basket.setCustomer(this.customer);
        this.basket.setDate(this.date);
        this.basket.setConsoleRequired(true);

        Assertions.assertTrue(this.basket.isBasketPopulated());
    }

    @Test
    void basketIsNotPopulatedWithNoGamesAndConsoleIsNotRequired() {
        this.basket.setConsole(this.console);
        this.basket.setCustomer(this.customer);
        this.basket.setDate(this.date);

        Assertions.assertFalse(this.basket.isBasketPopulated());
    }

    @Test
    void basketIsNotPopulatedWithNoDate() {
        this.basket.setConsole(this.console);
        this.basket.setCustomer(this.customer);
        this.basket.addGame(this.game1);

        Assertions.assertFalse(this.basket.isBasketPopulated());
    }

    @Test
    void basketIsNotPopulatedWithNoCustomer() {
        this.basket.setConsole(this.console);
        this.basket.setDate(this.date);
        this.basket.addGame(this.game1);

        Assertions.assertFalse(this.basket.isBasketPopulated());
    }

    @Test
    void correctlyFindsGameInBasket() {
        this.basket.addGame(this.game1);

        Assertions.assertTrue(this.basket.gameInBasket(this.game1));
    }

    @Test
    void correctlyDoesNotFindGameInBasket() {
        this.basket.addGame(this.game1);

        Assertions.assertFalse(this.basket.gameInBasket(this.game2));
    }

    @Test
    void correctlyRemovesGameFromBasket() {
        this.basket.addGame(this.game1);

        // ensure game got added
        Assertions.assertTrue(this.basket.gameInBasket(this.game1));

        this.basket.removeGame(this.game1);
        Assertions.assertFalse(this.basket.gameInBasket(this.game1));

    }

    @Test
    @Description("Max games is 3")
    void reachesMaxGamesInBasket() {
        this.basket.addGame(this.game1);
        this.basket.addGame(this.game2);
        this.basket.addGame(this.game3);

        Assertions.assertTrue(this.basket.isMaxGamesInBasket());
    }

    @Test
    @Description("Max games is 3")
    void underMaxGamesInBasket() {
        this.basket.addGame(this.game1);
        this.basket.addGame(this.game2);

        Assertions.assertFalse(this.basket.isMaxGamesInBasket());
    }

    @Test
    @Description("Max games is 3")
    void notAllowedOverMaxGamesInBasket() {
        Game game4 = new Game("Test game 4", this.console, 15.0, false);
        this.basket.addGame(this.game1);
        this.basket.addGame(this.game2);
        this.basket.addGame(this.game3);
        this.basket.addGame(game4);

        Assertions.assertTrue(this.basket.isMaxGamesInBasket());
    }

    @Test
    void clearGamesLeavesZeroGamesInArray() {
        this.basket.addGame(this.game1);
        this.basket.addGame(this.game2);

        // make sure games got added first
        Assertions.assertEquals(2, this.basket.numberOfGamesInBasket());

        this.basket.clearGames();
        Assertions.assertEquals(0, this.basket.numberOfGamesInBasket());
    }

    @Test
    void clearingTheBasketEmptiesAllContent() {
        populateBasket();

        // make sure basket is populated first
        Assertions.assertTrue(this.basket.isBasketPopulated());

        this.basket.clearBasket();
        Assertions.assertNull(this.basket.getConsole());
        Assertions.assertNull(this.basket.getCustomer());
        Assertions.assertNull(this.basket.getDate());
        Assertions.assertEquals(0, this.basket.numberOfGamesInBasket());
        Assertions.assertFalse(this.basket.isConsoleRequired());
        Assertions.assertFalse(this.basket.isBasketPopulated());
    }

    void populateBasket() {
        this.basket.setConsole(this.console);
        this.basket.setCustomer(this.customer);
        this.basket.setDate(this.date);
        this.basket.addGame(this.game1);
        this.basket.setConsoleRequired(true);
    }
}