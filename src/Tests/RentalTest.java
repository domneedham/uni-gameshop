package Tests;

import GameShop.java.models.*;
import GameShop.java.models.enums.ConsoleForm;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

class RentalTest {
    private Rental rental;
    private LocalDate date;
    private Customer customer;
    private ArrayList<Game> games = new ArrayList<>();
    private Console console;
    private Game game1;
    private Game game2;
    private Game game3;

    @BeforeEach
    void setUp() {
        this.date = LocalDate.now();
        this.customer = new Customer("Dom", "Needham", "dominic.needham@bt.com", "01773");
        this.console = new Console("Test Console", ConsoleForm.STANDARD, 15.0, 8, false);
        this.game1 = new Game("Test game 1", console, 10.0, false);
        this.game2 = new Game("Test game 2", console, 10.0, false);
        this.game3 = new Game("Test game 3", console, 10.0, false);
        this.games.add(this.game1);
        this.games.add(this.game2);
        this.games.add(this.game3);
    }

    @Test
    @Description("Max games is 3")
    void getMaxGames() {
        Assertions.assertEquals(3, Rental.getMaxGames());
    }

    @Test
    void creatingARentalWorks() {
        this.rental = new Rental(this.date, this.customer, this.console, this.games);

        Assertions.assertEquals(this.date, this.rental.getDateRented());
        Assertions.assertEquals(this.customer, this.rental.getCustomer());
        Assertions.assertEquals(this.console, this.rental.getConsole());
        Assertions.assertEquals(this.games, this.rental.getGames());
    }

    @Test
    void creatingARentalWithNoConsoleWorks() {
        this.rental = new Rental(this.date, this.customer, this.games);

        Assertions.assertEquals(this.date, this.rental.getDateRented());
        Assertions.assertEquals(this.customer, this.rental.getCustomer());
        Assertions.assertNull(this.rental.getConsole());
        Assertions.assertEquals(this.games, this.rental.getGames());
    }
}