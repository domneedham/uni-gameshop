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
    final TestData testData = new TestData();
    final BasketService basketService = new BasketService();

    final LocalDate date = testData.date;
    final Customer customer = testData.customer1;
    final Console console = testData.standardConsole1;
    final Game game1 = testData.consoleOneGame1;
    final Game game2 = testData.consoleOneGame2;
    final Game game3 = testData.consoleOneRepairGame1;

    @BeforeEach
    void setUp() {
        // ensure basket is empty before starting each test
        basketService.clearBasket();
        Assertions.assertFalse(basketService.isBasketPopulated());
    }

    @Test
    void basketIsPopulatedWithAllContent() throws Exception {
        populateBasket();

        Assertions.assertTrue(basketService.isBasketPopulated());
    }

    @Test
    void basketIsPopulatedWithConsoleNotRequired() {
        basketService.setCustomer(this.customer);
        basketService.setDate(this.date);
        basketService.addGame(this.game1);

        Assertions.assertTrue(basketService.isBasketPopulated());
    }

    @Test
    void basketIsPopulatedWithNoGamesIfConsoleIsRequired() throws Exception {
        basketService.requireConsole(this.console);
        basketService.setCustomer(this.customer);
        basketService.setDate(this.date);

        Assertions.assertTrue(basketService.isBasketPopulated());
    }

    @Test
    void basketIsNotPopulatedWithNoGamesAndConsoleIsNotRequired() {
        basketService.setCustomer(this.customer);
        basketService.setDate(this.date);

        Assertions.assertFalse(basketService.isBasketPopulated());
    }

    @Test
    void basketIsNotPopulatedWithNoDate() throws Exception {
        basketService.requireConsole(this.console);
        basketService.setCustomer(this.customer);
        basketService.addGame(this.game1);

        Assertions.assertFalse(basketService.isBasketPopulated());
    }

    @Test
    void basketIsNotPopulatedWithNoCustomer() throws Exception {
        basketService.requireConsole(this.console);
        basketService.setDate(this.date);
        basketService.addGame(this.game1);

        Assertions.assertFalse(basketService.isBasketPopulated());
    }

    @Test
    void correctlyFindsGameInBasket() {
        basketService.addGame(this.game1);

        Assertions.assertTrue(basketService.gameInBasket(this.game1));
    }

    @Test
    void correctlyDoesNotFindGameInBasket() {
        basketService.addGame(this.game1);

        Assertions.assertFalse(basketService.gameInBasket(this.game2));
    }

    @Test
    void correctlyRemovesGameFromBasket() {
        basketService.addGame(this.game1);

        // ensure game got added
        Assertions.assertTrue(basketService.gameInBasket(this.game1));

        basketService.removeGame(this.game1);
        Assertions.assertFalse(basketService.gameInBasket(this.game1));

    }

    @Test
    @Description("Max games is 3")
    void reachesMaxGamesInBasket() {
        basketService.addGame(this.game1);
        basketService.addGame(this.game2);
        basketService.addGame(this.game3);

        Assertions.assertTrue(basketService.isMaxGamesInBasket());
    }

    @Test
    @Description("Max games is 3")
    void underMaxGamesInBasket() {
        basketService.addGame(this.game1);
        basketService.addGame(this.game2);

        Assertions.assertFalse(basketService.isMaxGamesInBasket());
    }

    @Test
    @Description("Max games is 3")
    void notAllowedOverMaxGamesInBasket() {
        Game game4 = new Game("Test game 4", this.console, 15.0, false);
        basketService.addGame(this.game1);
        basketService.addGame(this.game2);
        basketService.addGame(this.game3);
        basketService.addGame(game4);

        Assertions.assertTrue(basketService.isMaxGamesInBasket());
    }

    @Test
    void clearGamesLeavesZeroGamesInArray() {
        basketService.addGame(this.game1);
        basketService.addGame(this.game2);

        // make sure games got added first
        Assertions.assertEquals(2, basketService.getGames().size());

        basketService.clearGames();
        Assertions.assertEquals(0, basketService.getGames().size());
    }

    @Test
    void clearingTheBasketEmptiesAllContent() throws Exception {
        populateBasket();

        // make sure basket is populated first
        Assertions.assertTrue(basketService.isBasketPopulated());

        basketService.clearBasket();
        Assertions.assertNull(basketService.getConsole());
        Assertions.assertNull(basketService.getCustomer());
        Assertions.assertNull(basketService.getDate());
        Assertions.assertEquals(0, basketService.getGames().size());
        Assertions.assertFalse(basketService.isConsoleRequired());
        Assertions.assertFalse(basketService.isBasketPopulated());
    }

    @Test
    void getCustomerReturnsCorrectCustomer() throws Exception {
        populateBasket();

        Assertions.assertEquals(this.customer, basketService.getCustomer());
    }

    @Test
    void isConsoleRequiredReturnsTrueIfConsoleIsRequired() throws Exception {
        basketService.requireConsole(this.console);

        Assertions.assertTrue(basketService.isConsoleRequired());
    }

    @Test
    void isConsoleRequiredReturnsFalseIfConsoleIsNotRequired() {
        basketService.unrequireConsole();

        Assertions.assertFalse(basketService.isConsoleRequired());
    }

    @Test
    void getConsoleReturnsTheCorrectConsole() throws Exception {
        basketService.requireConsole(this.console);
        Assertions.assertEquals(this.console, basketService.getConsole());
    }

    @Test
    void getConsoleReturnsNullIfConsoleIsNotRequired() {
        Assertions.assertNull(basketService.getConsole());
    }

    @Test
    void getGamesReturnsCorrectSize() {
        basketService.addGame(this.game1);
        basketService.addGame(this.game2);

        Assertions.assertEquals(2, basketService.getGames().size());
    }

    @Test
    void getGamesReturnsCorrectSizeIfSameGameAsAdded() {
        basketService.addGame(this.game1);
        basketService.addGame(this.game2);
        basketService.addGame(this.game2);

        Assertions.assertEquals(2, basketService.getGames().size());
    }

    @Test
    void getDateReturnsCorrectDate() {
        basketService.setDate(this.date);

        Assertions.assertEquals(this.date, basketService.getDate());
    }

    @Test
    void submitBasketClearsBasket() throws Exception {
        populateBasket();
        Assertions.assertTrue(basketService.isBasketPopulated());
        basketService.submitBasket();
        Assertions.assertFalse(basketService.isBasketPopulated());
    }

    void populateBasket() throws Exception {
        basketService.setCustomer(this.customer);
        basketService.setDate(this.date);
        basketService.addGame(this.game1);
        basketService.requireConsole(this.console);
    }

}