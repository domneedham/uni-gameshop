package Tests.repositories;

import GameShop.java.models.Console;
import GameShop.java.models.Customer;
import GameShop.java.models.Game;
import GameShop.java.repositories.BasketRepository;
import Tests.TestData;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

class BasketRepositoryTest {
    final BasketRepository repo = new BasketRepository();

    @Test
    void getBasketReturnsABasket() {
        Assertions.assertNotNull(repo.getBasket());
    }

    final TestData testData = new TestData();

    final LocalDate date = testData.date;
    final Customer customer = testData.customer1;
    final Console console = testData.standardConsole1;
    final Game game1 = testData.consoleOneGame1;
    final Game game2 = testData.consoleOneGame2;
    final Game game3 = testData.consoleOneRepairGame1;

    @BeforeEach
    void setUp() {
        // ensure basket is empty before starting each test
        repo.clearBasket();
        Assertions.assertFalse(repo.getBasket().isBasketPopulated());
    }

    @Test
    void correctlyRemovesGameFromBasket() {
        repo.addGame(this.game1);

        // ensure game got added
        Assertions.assertTrue(repo.getBasket().gameInBasket(this.game1));

        repo.removeGame(this.game1);
        Assertions.assertFalse(repo.getBasket().gameInBasket(this.game1));

    }

    @Test
    @Description("Max games is 3")
    void reachesMaxGamesInBasket() {
        repo.addGame(this.game1);
        repo.addGame(this.game2);
        repo.addGame(this.game3);

        Assertions.assertTrue(repo.getBasket().isMaxGamesInBasket());
    }

    @Test
    @Description("Max games is 3")
    void underMaxGamesInBasket() {
        repo.addGame(this.game1);
        repo.addGame(this.game2);

        Assertions.assertFalse(repo.getBasket().isMaxGamesInBasket());
    }

    @Test
    @Description("Max games is 3")
    void notAllowedOverMaxGamesInBasket() {
        Game game4 = new Game("Test game 4", this.console, 15.0, false);
        repo.addGame(this.game1);
        repo.addGame(this.game2);
        repo.addGame(this.game3);
        repo.addGame(game4);

        Assertions.assertTrue(repo.getBasket().isMaxGamesInBasket());
    }

    @Test
    void clearGamesLeavesZeroGamesInArray() {
        repo.addGame(this.game1);
        repo.addGame(this.game2);

        // make sure games got added first
        Assertions.assertEquals(2, repo.getBasket().getGames().size());

        repo.clearGames();
        Assertions.assertEquals(0, repo.getBasket().getGames().size());
    }

    @Test
    void clearingTheBasketEmptiesAllContent() throws Exception {
        populateBasket();

        // make sure basket is populated first
        Assertions.assertTrue(repo.getBasket().isBasketPopulated());

        repo.clearBasket();
        Assertions.assertNull(repo.getBasket().getConsole());
        Assertions.assertNull(repo.getBasket().getCustomer());
        Assertions.assertNull(repo.getBasket().getDateRented());
        Assertions.assertEquals(0, repo.getBasket().getGames().size());
        Assertions.assertFalse(repo.getBasket().isConsoleRequired());
        Assertions.assertFalse(repo.getBasket().isBasketPopulated());
    }

    void populateBasket() throws Exception {
        repo.setCustomer(this.customer);
        repo.setDate(this.date);
        repo.addGame(this.game1);
        repo.requireConsole(this.console);
    }
}