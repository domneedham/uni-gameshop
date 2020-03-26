package Tests.services;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.services.BasketService;
import Tests.TestData;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

class BasketServiceTest {
    TestData testData = new TestData();

    LocalDate date = testData.date;
    Customer customer = testData.customer1;
    Console console = testData.standardConsole1;
    Game game1 = testData.consoleOneGame1;
    Game game2 = testData.consoleOneGame2;
    Game game3 = testData.consoleOneRepairGame1;

    @BeforeEach
    void setUp() {
        // ensure basket is empty before starting each test
        BasketService.clearBasket();
        Assertions.assertFalse(BasketService.isBasketPopulated());
    }

    @Test
    void basketIsPopulatedWithAllContent() throws Exception {
        populateBasket();

        Assertions.assertTrue(BasketService.isBasketPopulated());
    }

    @Test
    void basketIsPopulatedWithConsoleNotRequired() {
        BasketService.setCustomer(this.customer);
        BasketService.setDate(this.date);
        BasketService.addGame(this.game1);

        Assertions.assertTrue(BasketService.isBasketPopulated());
    }

    @Test
    void basketIsPopulatedWithNoGamesIfConsoleIsRequired() throws Exception {
        BasketService.requireConsole(this.console);
        BasketService.setCustomer(this.customer);
        BasketService.setDate(this.date);

        Assertions.assertTrue(BasketService.isBasketPopulated());
    }

    @Test
    void basketIsNotPopulatedWithNoGamesAndConsoleIsNotRequired() {
        BasketService.setCustomer(this.customer);
        BasketService.setDate(this.date);

        Assertions.assertFalse(BasketService.isBasketPopulated());
    }

    @Test
    void basketIsNotPopulatedWithNoDate() throws Exception {
        BasketService.requireConsole(this.console);
        BasketService.setCustomer(this.customer);
        BasketService.addGame(this.game1);

        Assertions.assertFalse(BasketService.isBasketPopulated());
    }

    @Test
    void basketIsNotPopulatedWithNoCustomer() throws Exception {
        BasketService.requireConsole(this.console);
        BasketService.setDate(this.date);
        BasketService.addGame(this.game1);

        Assertions.assertFalse(BasketService.isBasketPopulated());
    }

    @Test
    void correctlyFindsGameInBasket() {
        BasketService.addGame(this.game1);

        Assertions.assertTrue(BasketService.gameInBasket(this.game1));
    }

    @Test
    void correctlyDoesNotFindGameInBasket() {
        BasketService.addGame(this.game1);

        Assertions.assertFalse(BasketService.gameInBasket(this.game2));
    }

    @Test
    void correctlyRemovesGameFromBasket() {
        BasketService.addGame(this.game1);

        // ensure game got added
        Assertions.assertTrue(BasketService.gameInBasket(this.game1));

        BasketService.removeGame(this.game1);
        Assertions.assertFalse(BasketService.gameInBasket(this.game1));

    }

    @Test
    @Description("Max games is 3")
    void reachesMaxGamesInBasket() {
        BasketService.addGame(this.game1);
        BasketService.addGame(this.game2);
        BasketService.addGame(this.game3);

        Assertions.assertTrue(BasketService.isMaxGamesInBasket());
    }

    @Test
    @Description("Max games is 3")
    void underMaxGamesInBasket() {
        BasketService.addGame(this.game1);
        BasketService.addGame(this.game2);

        Assertions.assertFalse(BasketService.isMaxGamesInBasket());
    }

    @Test
    @Description("Max games is 3")
    void notAllowedOverMaxGamesInBasket() {
        Game game4 = new Game("Test game 4", this.console, 15.0, false);
        BasketService.addGame(this.game1);
        BasketService.addGame(this.game2);
        BasketService.addGame(this.game3);
        BasketService.addGame(game4);

        Assertions.assertTrue(BasketService.isMaxGamesInBasket());
    }

    @Test
    void clearGamesLeavesZeroGamesInArray() {
        BasketService.addGame(this.game1);
        BasketService.addGame(this.game2);

        // make sure games got added first
        Assertions.assertEquals(2, BasketService.getGames().size());

        BasketService.clearGames();
        Assertions.assertEquals(0, BasketService.getGames().size());
    }

    @Test
    void clearingTheBasketEmptiesAllContent() throws Exception {
        populateBasket();

        // make sure basket is populated first
        Assertions.assertTrue(BasketService.isBasketPopulated());

        BasketService.clearBasket();
        Assertions.assertNull(BasketService.getConsole());
        Assertions.assertNull(BasketService.getCustomer());
        Assertions.assertNull(BasketService.getDate());
        Assertions.assertEquals(0, BasketService.getGames().size());
        Assertions.assertFalse(BasketService.isConsoleRequired());
        Assertions.assertFalse(BasketService.isBasketPopulated());
    }

    @Test
    void getCustomerReturnsCorrectCustomer() throws Exception {
        populateBasket();

        Assertions.assertEquals(this.customer, BasketService.getCustomer());
    }

    @Test
    void isConsoleRequiredReturnsTrueIfConsoleIsRequired() throws Exception {
        BasketService.requireConsole(this.console);

        Assertions.assertTrue(BasketService.isConsoleRequired());
    }

    @Test
    void isConsoleRequiredReturnsFalseIfConsoleIsNotRequired() throws Exception {
        BasketService.unrequireConsole();

        Assertions.assertFalse(BasketService.isConsoleRequired());
    }

    @Test
    void getConsoleReturnsTheCorrectConsole() throws Exception {
        BasketService.requireConsole(this.console);
        Assertions.assertEquals(this.console, BasketService.getConsole());
    }

    @Test
    void getConsoleReturnsNullIfConsoleIsNotRequired() {
        Assertions.assertNull(BasketService.getConsole());
    }

    @Test
    void getGamesReturnsCorrectSize() {
        BasketService.addGame(this.game1);
        BasketService.addGame(this.game2);

        Assertions.assertEquals(2, BasketService.getGames().size());
    }

    @Test
    void getGamesReturnsCorrectSizeIfSameGameAsAdded() {
        BasketService.addGame(this.game1);
        BasketService.addGame(this.game2);
        BasketService.addGame(this.game2);

        Assertions.assertEquals(2, BasketService.getGames().size());
    }

    @Test
    void getDateReturnsCorrectDate() {
        BasketService.setDate(this.date);

        Assertions.assertEquals(this.date, BasketService.getDate());
    }

    @Test
    void submitBasketClearsBasket() throws Exception {
        populateBasket();
        Assertions.assertTrue(BasketService.isBasketPopulated());
        BasketService.submitBasket();
        Assertions.assertFalse(BasketService.isBasketPopulated());
    }

    void populateBasket() throws Exception {
        BasketService.setCustomer(this.customer);
        BasketService.setDate(this.date);
        BasketService.addGame(this.game1);
        BasketService.requireConsole(this.console);
    }

}